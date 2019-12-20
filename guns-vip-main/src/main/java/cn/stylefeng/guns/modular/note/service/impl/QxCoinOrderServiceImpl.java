package cn.stylefeng.guns.modular.note.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.core.CommonUtils;
import cn.stylefeng.guns.core.constant.ProjectConstants.COIN_ORDER_PAY_TYPE;
import cn.stylefeng.guns.core.constant.ProjectConstants.COIN_ORDER_STATUS;
import cn.stylefeng.guns.core.constant.ProjectConstants.USER_PAY_LOG_TYPE;
import cn.stylefeng.guns.modular.note.entity.QxCoinOrder;
import cn.stylefeng.guns.modular.note.entity.QxPackage;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.mapper.QxCoinOrderMapper;
import cn.stylefeng.guns.modular.note.mapper.QxPackageMapper;
import cn.stylefeng.guns.modular.note.mapper.QxUserMapper;
import cn.stylefeng.guns.modular.note.model.params.QxCoinOrderParam;
import cn.stylefeng.guns.modular.note.model.result.QxCoinOrderResult;
import  cn.stylefeng.guns.modular.note.service.QxCoinOrderService;
import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 用户金币订单表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-22
 */
@Service
public class QxCoinOrderServiceImpl extends ServiceImpl<QxCoinOrderMapper, QxCoinOrder> implements QxCoinOrderService {

	@Resource
	private QxPackageMapper qxPackageMapper;
	
	@Resource
	private QxUserMapper qxUserMapper;
	
	@Resource
	private QxPayLogHelper qxPayLogHelper;
	
    @Override
    public void add(QxCoinOrderParam param){
        QxCoinOrder entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxCoinOrderParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxCoinOrderParam param){
        QxCoinOrder oldEntity = getOldEntity(param);
        QxCoinOrder newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxCoinOrderResult findBySpec(QxCoinOrderParam param){
        return null;
    }

    @Override
    public List<QxCoinOrderResult> findListBySpec(QxCoinOrderParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxCoinOrderParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxCoinOrderParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxCoinOrder getOldEntity(QxCoinOrderParam param) {
        return this.getById(getKey(param));
    }

    private QxCoinOrder getEntity(QxCoinOrderParam param) {
        QxCoinOrder entity = new QxCoinOrder();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

	@Override
	public QxCoinOrder createOrder(Long userId, Long packageId, String type) {
		QxPackage qxPackage = qxPackageMapper.selectById(packageId);
		QxCoinOrder entity = new QxCoinOrder();
		entity.setPackageId(qxPackage.getId());
		entity.setSn(CommonUtils.getSerialNumber());
		entity.setAmount(qxPackage.getAmount());
		entity.setStatus(COIN_ORDER_STATUS.WAIT_PAY);
		entity.setUserId(userId);
		entity.setType(type);
		this.baseMapper.insert(entity);
		return entity;
	}

	@Override
	public void chargeUser(Long userId, Long packageId) {
		QxCoinOrder order = createOrder(userId, packageId, COIN_ORDER_PAY_TYPE.PLATFORM);
		order.setStatus(COIN_ORDER_STATUS.PAID);
		QxPackage qxPackage = qxPackageMapper.selectById(packageId);
		QxUser qxUser = qxUserMapper.selectById(userId);
		qxUser.setFreeze(qxUser.getFreeze()+qxPackage.getCoins());
		qxUserMapper.updateById(qxUser);
		// 用户流水
		qxPayLogHelper.createPayLog(order.getUserId(), qxPackage.getCoins(), USER_PAY_LOG_TYPE.PLATFORM_BUY_COIN_OUT);
	}

}
