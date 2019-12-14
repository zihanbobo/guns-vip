package cn.stylefeng.guns.modular.note.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.config.ConfigEntity;
import cn.stylefeng.guns.core.CommonUtils;
import cn.stylefeng.guns.core.constant.ProjectConstants.SOCIAL_TYPE;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.entity.QxUserSocial;
import cn.stylefeng.guns.modular.note.mapper.QxUserMapper;
import cn.stylefeng.guns.modular.note.mapper.QxUserSocialMapper;
import cn.stylefeng.guns.modular.note.model.params.QxUserParam;
import cn.stylefeng.guns.modular.note.model.result.QxUserResult;
import cn.stylefeng.guns.modular.note.service.QxUserService;
import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author
 * @since 2019-11-14
 */
@Service
public class QxUserServiceImpl extends ServiceImpl<QxUserMapper, QxUser> implements QxUserService {

	@Resource
	private ConfigEntity configEntity;
	
	@Resource
	private QxUserSocialMapper qxUserSocialMapper;

	@Override
	public void add(QxUserParam param) {
		QxUser entity = getEntity(param);
		this.save(entity);
	}

	@Override
	public void delete(QxUserParam param) {
		QxUser entity = getEntity(param);
		entity.setDeleted(true);
		this.updateById(entity);
	}

	@Override
	public void update(QxUserParam param) {
		QxUser oldEntity = getOldEntity(param);
		QxUser newEntity = getEntity(param);
		ToolUtil.copyProperties(newEntity, oldEntity);
		this.updateById(newEntity);
	}

	@Override
	public QxUserResult findBySpec(QxUserParam param) {
		return null;
	}

	@Override
	public List<QxUserResult> findListBySpec(QxUserParam param) {
		return null;
	}

	@Override
	public LayuiPageInfo findPageBySpec(QxUserParam param) {
		Page pageContext = getPageContext();
		IPage page = this.baseMapper.customPageList(pageContext, param);
		return LayuiPageFactory.createPageInfo(page);
	}

	private Serializable getKey(QxUserParam param) {
		return param.getId();
	}

	private Page getPageContext() {
		return LayuiPageFactory.defaultPage();
	}

	private QxUser getOldEntity(QxUserParam param) {
		return this.getById(getKey(param));
	}

	private QxUser getEntity(QxUserParam param) {
		QxUser entity = new QxUser();
		ToolUtil.copyProperties(param, entity);
		return entity;
	}

	@Override
	public QxUser getUserByAccount(String account) {
		return this.baseMapper.getByAccount(account);
	}

	@Override
	public List<QxUser> getProductUsers(Page page, Long id) {
		return this.baseMapper.getProductUsers(page, id);
	}

	@Override
	public QxUser getUserByUnionId(String appId, String unionId) {
		return this.baseMapper.getUserByUnionId(appId, unionId);
	}

	@Override
	public QxUser performRegister(String mobile) {
		QxUser user = new QxUser();
		user.setMobile(mobile);
		user.setInviteCode(generateInviteCode());
		this.baseMapper.insert(user);
		return user;
	}

	public String generateInviteCode() {
		while (true) {
			String inviteCode = CommonUtils.getRandomNumber(configEntity.getInviteCodeLength());
			if (isValidCode(inviteCode)) {
				return inviteCode;
			}
		}
	}
	
	/**
	 * 检查code是否有效
	 * @param code
	 * @return
	 */
	public boolean isValidCode(String code) {
		QueryWrapper<QxUser> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("invite_code", code);
		int count = this.count(queryWrapper);
		return count == 0;
	}

	@Override
	public QxUser wxBindUser(String mobile, String appId, String openId, String unionId) {
		QxUser user = getUserByAccount(mobile);
		if (user == null) {
			user = performRegister(mobile);
		}
		QxUserSocial qxUserSocial = new QxUserSocial();
		qxUserSocial.setUserId(user.getId());
		qxUserSocial.setAppId(appId);
		qxUserSocial.setOpenId(openId);
		qxUserSocial.setUnionId(unionId);
		qxUserSocial.setType(SOCIAL_TYPE.WECHAT);
		qxUserSocialMapper.insert(qxUserSocial);
		return user;
	}

	@Override
	public QxUserSocial getUserSocialByAppId(Long userId, String appId) {
		QueryWrapper<QxUserSocial> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId).eq("app_id", appId);
		return qxUserSocialMapper.selectOne(queryWrapper);
	}

	@Override
	public QxUser getUserByInviteCode(String parentInviteCode) {
		QueryWrapper<QxUser> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("invite_code", parentInviteCode);
		return this.getOne(queryWrapper);
	}

	@Override
	public QxUser getUserByOpenId(String appId, String openId) {
		return this.baseMapper.getUserByOpenId(appId, openId);
	}
}
