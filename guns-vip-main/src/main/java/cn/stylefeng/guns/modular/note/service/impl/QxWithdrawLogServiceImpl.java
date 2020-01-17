package cn.stylefeng.guns.modular.note.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.core.CommonUtils;
import cn.stylefeng.guns.core.constant.ProjectConstants.COST_RATE_TYPE;
import cn.stylefeng.guns.core.constant.ProjectConstants.SOCIAL_TYPE;
import cn.stylefeng.guns.core.constant.ProjectConstants.USER_PAY_LOG_TYPE;
import cn.stylefeng.guns.core.constant.ProjectConstants.WITHDRAW_PAY_WAY;
import cn.stylefeng.guns.core.constant.ProjectConstants.WITHDRAW_STATUS;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.entity.QxUserSocial;
import cn.stylefeng.guns.modular.note.entity.QxWithdrawLog;
import cn.stylefeng.guns.modular.note.mapper.QxUserMapper;
import cn.stylefeng.guns.modular.note.mapper.QxWithdrawLogMapper;
import cn.stylefeng.guns.modular.note.model.params.QxWithdrawLogParam;
import cn.stylefeng.guns.modular.note.model.result.QxWithdrawLogResult;
import cn.stylefeng.guns.modular.note.service.QxWithdrawLogService;
import cn.stylefeng.roses.core.util.ToolUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 平台提现表 服务实现类
 * </p>
 *
 * @author
 * @since 2019-11-18
 */
@Slf4j
@Service
public class QxWithdrawLogServiceImpl extends ServiceImpl<QxWithdrawLogMapper, QxWithdrawLog>
		implements QxWithdrawLogService {

	@Resource
	private QxUserMapper qxUserMapper;
	
	@Resource
	private QxPayLogHelper qxPayLogHelper;
	
	@Resource
	private QxCoinHelper qxCoinHelper;
	
	@Override
	public void add(QxWithdrawLogParam param) {
		QxWithdrawLog entity = getEntity(param);
		this.save(entity);
	}

	@Override
	public void delete(QxWithdrawLogParam param) {
		this.removeById(getKey(param));
	}

	@Override
	public void update(QxWithdrawLogParam param) {
		QxWithdrawLog oldEntity = getOldEntity(param);
		QxWithdrawLog newEntity = getEntity(param);
		ToolUtil.copyProperties(newEntity, oldEntity);
		this.updateById(newEntity);
	}

	@Override
	public QxWithdrawLogResult findBySpec(QxWithdrawLogParam param) {
		return null;
	}

	@Override
	public List<QxWithdrawLogResult> findListBySpec(QxWithdrawLogParam param) {
		return null;
	}

	@Override
	public LayuiPageInfo findPageBySpec(QxWithdrawLogParam param) {
		Page pageContext = getPageContext();
		IPage page = this.baseMapper.customPageList(pageContext, param);
		return LayuiPageFactory.createPageInfo(page);
	}

	private Serializable getKey(QxWithdrawLogParam param) {
		return param.getId();
	}

	private Page getPageContext() {
		return LayuiPageFactory.defaultPage();
	}

	private QxWithdrawLog getOldEntity(QxWithdrawLogParam param) {
		return this.getById(getKey(param));
	}

	private QxWithdrawLog getEntity(QxWithdrawLogParam param) {
		QxWithdrawLog entity = new QxWithdrawLog();
		ToolUtil.copyProperties(param, entity);
		return entity;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public QxWithdrawLog createWithdrawLog(QxUser user, QxUserSocial userSocial, BigDecimal amount) {
		BigDecimal coinRate = qxCoinHelper.getRateByType(COST_RATE_TYPE.COIN_RATE); // 汇率
		BigDecimal withdrawAmount = qxCoinHelper.getWithdrawAmount(amount); // 获取可提现金额，整数
		Integer withdrawCoinCount = CommonUtils.divide(amount, coinRate).setScale(0, BigDecimal.ROUND_DOWN).intValue();
		QxWithdrawLog entity = new QxWithdrawLog();
		entity.setSn(CommonUtils.getSerialNumber());
		entity.setUserId(userSocial.getUserId());
		entity.setAmount(withdrawAmount);
		entity.setCoinCount(withdrawCoinCount);
		entity.setPayWay(getWithdrawPayWay(userSocial.getType()));
		entity.setPayeeAccount(userSocial.getOpenId());
		entity.setStatus(WITHDRAW_STATUS.WAIT_OUT);
		this.baseMapper.insert(entity);
		// 冻结用户金额
		qxCoinHelper.freezeCoin(user.getId(), withdrawCoinCount);
		return entity;
	}

	public String getWithdrawPayWay(String type) {
		if (SOCIAL_TYPE.WECHAT.equals(type)) {
			return WITHDRAW_PAY_WAY.WECHAT;
		} else if (SOCIAL_TYPE.ALIPAY.equals(type)) {
			return WITHDRAW_PAY_WAY.ALIPAY;
		} else {
			log.error("不支持的提现方式, type=" + type);
			throw new ServiceException("不支持的提现方式");
		}
	}

	@Override
	public void updateWithdrawSuccess(QxWithdrawLog withdrawLog, QxUser user, int coinCount) {
		// 更新提现状态
		withdrawLog.setStatus(WITHDRAW_STATUS.OUT);
		this.updateById(withdrawLog);
		// 更新用户金币余额
		user.setFreeze(user.getFreeze() - coinCount);
		qxUserMapper.updateById(user);
		// 更新用户流水
		qxPayLogHelper.createPayLog(user.getId(), coinCount, USER_PAY_LOG_TYPE.WITHDRAW_COIN_IN);
	}
}
