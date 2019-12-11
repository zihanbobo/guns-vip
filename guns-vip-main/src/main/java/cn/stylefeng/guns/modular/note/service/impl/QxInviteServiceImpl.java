package cn.stylefeng.guns.modular.note.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.config.ConfigEntity;
import cn.stylefeng.guns.core.CommonUtils;
import cn.stylefeng.guns.core.constant.ProjectConstants.ALERT_STATUS;
import cn.stylefeng.guns.core.constant.ProjectConstants.INVITE_APPLY_STATUS;
import cn.stylefeng.guns.core.constant.ProjectConstants.INVITE_OPERATE_TYPE;
import cn.stylefeng.guns.core.constant.ProjectConstants.INVITE_STATUS;
import cn.stylefeng.guns.core.constant.ProjectConstants.INVITE_TYPE;
import cn.stylefeng.guns.core.constant.ProjectConstants.SMS_CODE;
import cn.stylefeng.guns.core.constant.ProjectConstants.USER_PAY_LOG_TYPE;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.core.util.NoticeHelper;
import cn.stylefeng.guns.modular.note.dto.QxInviteCommentTo;
import cn.stylefeng.guns.modular.note.dto.QxInviteQueryTo;
import cn.stylefeng.guns.modular.note.dto.QxInviteTo;
import cn.stylefeng.guns.modular.note.dto.QxPayResult;
import cn.stylefeng.guns.modular.note.entity.QxAlert;
import cn.stylefeng.guns.modular.note.entity.QxComplaint;
import cn.stylefeng.guns.modular.note.entity.QxInvite;
import cn.stylefeng.guns.modular.note.entity.QxInviteApply;
import cn.stylefeng.guns.modular.note.entity.QxInviteComment;
import cn.stylefeng.guns.modular.note.entity.QxInviteOperate;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.mapper.QxInviteApplyMapper;
import cn.stylefeng.guns.modular.note.mapper.QxInviteMapper;
import cn.stylefeng.guns.modular.note.mapper.QxInviteOperateMapper;
import cn.stylefeng.guns.modular.note.mapper.QxUserMapper;
import cn.stylefeng.guns.modular.note.model.params.QxInviteParam;
import cn.stylefeng.guns.modular.note.model.result.QxInviteResult;
import cn.stylefeng.guns.modular.note.pojo.QxInviteSearchPojo;
import cn.stylefeng.guns.modular.note.pojo.QxInviteUserPojo;
import cn.stylefeng.guns.modular.note.service.QxAlertService;
import cn.stylefeng.guns.modular.note.service.QxComplaintService;
import cn.stylefeng.guns.modular.note.service.QxInviteCommentService;
import cn.stylefeng.guns.modular.note.service.QxInviteService;
import cn.stylefeng.roses.core.util.ToolUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 约单表 服务实现类
 * </p>
 *
 * @author
 * @since 2019-11-18
 */
@Slf4j
@Service
public class QxInviteServiceImpl extends ServiceImpl<QxInviteMapper, QxInvite> implements QxInviteService {

	@Resource
	private ConfigEntity configEntity;

	@Resource
	private QxUserMapper qxUserMapper;

	@Resource
	private QxInviteMapper qxInviteMapper;

	@Resource
	private QxInviteApplyMapper qxInviteApplyMapper;

	@Resource
	private QxInviteOperateMapper qxInviteOperateMapper;

	@Resource
	private QxComplaintService qxComplaintService;

	@Resource
	private QxInviteCommentService qxInviteCommentSerivce;

	@Resource
	private QxAlertService qxAlertService;

	@Resource
	private NoticeHelper noticeHelper;

	@Resource
	private QxCoinHelper qxCoinHelper;

	@Resource
	private QxPayLogHelper qxPayLogHelper;

	@Override
	public void add(QxInviteParam param) {
		QxInvite entity = getEntity(param);
		this.save(entity);
	}

	@Override
	public void delete(QxInviteParam param) {
		QxInvite entity = getEntity(param);
		entity.setDeleted(true);
		this.updateById(entity);
	}

	@Override
	public void update(QxInviteParam param) {
		QxInvite oldEntity = getOldEntity(param);
		QxInvite newEntity = getEntity(param);
		ToolUtil.copyProperties(newEntity, oldEntity);
		this.updateById(newEntity);
	}

	@Override
	public QxInviteResult findBySpec(QxInviteParam param) {
		return null;
	}

	@Override
	public List<QxInviteResult> findListBySpec(QxInviteParam param) {
		return null;
	}

	@Override
	public LayuiPageInfo findPageBySpec(QxInviteParam param) {
		Page pageContext = getPageContext();
		IPage page = this.baseMapper.customPageList(pageContext, param);
		return LayuiPageFactory.createPageInfo(page);
	}

	private Serializable getKey(QxInviteParam param) {
		return param.getId();
	}

	private Page getPageContext() {
		return LayuiPageFactory.defaultPage();
	}

	private QxInvite getOldEntity(QxInviteParam param) {
		return this.getById(getKey(param));
	}

	private QxInvite getEntity(QxInviteParam param) {
		QxInvite entity = new QxInvite();
		ToolUtil.copyProperties(param, entity);
		return entity;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addInvite(Long requestUserId, QxInviteTo inviteTo) {
		QxInvite invite = new QxInvite();
		BeanUtils.copyProperties(inviteTo, invite);
		invite.setInviter(requestUserId);
		invite.setSn(CommonUtils.getSerialNumber());
		invite.setStatus(INVITE_STATUS.WAIT_MATCH);
		this.baseMapper.insert(invite);
	}

	@Override
	public void apply(Long currentUserId, Long inviteId) {
		QxInviteApply inviteApply = new QxInviteApply();
		inviteApply.setUserId(currentUserId);
		inviteApply.setInviteId(inviteId);
		inviteApply.setStatus(INVITE_APPLY_STATUS.UN_SURE);
		qxInviteApplyMapper.insert(inviteApply);
	}

	@Override
	public List<QxInviteUserPojo> applicants(Long inviteId) {
		return this.baseMapper.getInviteUsers(inviteId);
	}

	@Override
	public void choose(Long inviteId, Long userId) {
		chooseApply(inviteId, userId);
		updateInviteStatus(inviteId, userId, INVITE_STATUS.MATCHED);
		notifyInvitee(inviteId);
	}

	public void chooseApply(Long inviteId, Long userId) {
		// 更新选中的报名状态
		UpdateWrapper<QxInviteApply> chooseUpdateWrapper = new UpdateWrapper<>();
		chooseUpdateWrapper.eq("invite_id", inviteId).eq("user_id", userId);
		QxInviteApply model = new QxInviteApply();
		model.setStatus(INVITE_APPLY_STATUS.AGREE);
		qxInviteApplyMapper.update(model, chooseUpdateWrapper);
		// 更新未选中的报名状态
		UpdateWrapper<QxInviteApply> rejectUpdateWrapper = new UpdateWrapper<>();
		rejectUpdateWrapper.eq("invite_id", inviteId).ne("user_id", userId);
		QxInviteApply rejectModel = new QxInviteApply();
		rejectModel.setStatus(INVITE_APPLY_STATUS.REJECT);
		qxInviteApplyMapper.update(rejectModel, rejectUpdateWrapper);
	}

	public void updateInviteStatus(Long invitedId, Long invitee, String status) {
		UpdateWrapper<QxInvite> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", invitedId);
		QxInvite model = new QxInvite();
		if (invitee != null) {
			model.setInvitee(invitee);
		}
		model.setStatus(status);
		this.baseMapper.update(model, updateWrapper);
	}

	private void notifyInvitee(Long inviteId) {
		List<QxInviteUserPojo> list = this.baseMapper.getInviteUsers(inviteId);
		for (QxInviteUserPojo inviteUser : list) {
			int tag = 0;
			String account = inviteUser.getMobile();
			Map<String, String> pairs = new HashMap<>();

			if (INVITE_STATUS.MATCHED.equals(inviteUser.getStatus())) {
				// 发送选中消息
				tag = SMS_CODE.INVITE_SUCCESS;
				log.info("User " + inviteUser.getMobile() + "被选中");
			} else {
				// 发送落选消息
				tag = SMS_CODE.INVITE_FAIL;
				log.info("User " + inviteUser.getMobile() + "未被选中");
			}
			noticeHelper.push(account, tag, pairs);
		}
	}

	@Override
	public void agree(Long inviteId) {
		QxInvite invite = this.baseMapper.selectById(inviteId);
		Long invitee = invite.getInvitee();
		chooseApply(inviteId, invitee);
		updateInviteStatus(inviteId, null, INVITE_STATUS.MATCHED);
		notifyInvitee(inviteId);
	}

	@Override
	public void reject(Long inviteId) {
		updateInviteStatus(inviteId, null, INVITE_STATUS.CANCEl);
		notifyInvitee(inviteId);
	}

	@Override
	public List<QxInvite> getCurrentInvites(Page page, Long userId) {
		return this.baseMapper.getCurrentInvites(page, userId);
	}

	@Override
	public void start(Long inviteId, Long requestUserId) {
		createInviteOperate(inviteId, requestUserId, INVITE_OPERATE_TYPE.CONFIRM_START);
		if (checkOtherSideOperate(inviteId, requestUserId, INVITE_OPERATE_TYPE.CONFIRM_START)) {
			changeQxInviteStatus(inviteId, INVITE_STATUS.DATING);
		}
	}

	public void createInviteOperate(Long inviteId, Long userId, String type) {
		QxInviteOperate inviteOperate = new QxInviteOperate();
		inviteOperate.setInviteId(inviteId);
		inviteOperate.setUserId(userId);
		inviteOperate.setType(type);
		qxInviteOperateMapper.insert(inviteOperate);
	}

	public Boolean checkOtherSideOperate(Long inviteId, Long userId, String type) {
		QueryWrapper<QxInviteOperate> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("invite_id", inviteId).eq("type", type).ne("user_id", userId);
		int count = qxInviteOperateMapper.selectCount(queryWrapper);
		return count > 0;
	}

	public void changeQxInviteStatus(Long inviteId, String status) {
		UpdateWrapper<QxInvite> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", inviteId);
		QxInvite model = new QxInvite();
		model.setStatus(status);
		this.baseMapper.update(model, updateWrapper);
	}

	@Override
	public void finish(Long inviteId, Long requestUserId) {
		createInviteOperate(inviteId, requestUserId, INVITE_OPERATE_TYPE.CONFIRM_FINISH);
		if (Boolean.TRUE.equals(checkOtherSideOperate(inviteId, requestUserId, INVITE_OPERATE_TYPE.CONFIRM_FINISH))) {
			changeQxInviteStatus(inviteId, INVITE_STATUS.FINISH);
			payCoin(inviteId);
		}
	}

	public void payCoin(Long inviteId) {
		QxInvite invite = this.getById(inviteId);
		Long payerId;
		Long payeeId;
		if (invite.getInviteType().equals(INVITE_TYPE.ACTIVE)) {
			payerId = invite.getInviter();
			payeeId = invite.getInvitee();
		} else {
			payerId = invite.getInvitee();
			payeeId = invite.getInviter();
		}
		QxPayResult payResult = qxCoinHelper.payCoin(payerId, payeeId, invite.getGiftId());
		qxPayLogHelper.createPayLog(payResult.getPayerId(), payResult.getPrice(), USER_PAY_LOG_TYPE.INVITE_OUT);
		qxPayLogHelper.createPayLog(payResult.getPayeeId(), payResult.getPrice(), USER_PAY_LOG_TYPE.INVITE_IN);
	}

	@Override
	public void complaint(Long inviteId, Long userId, String reason) {
		QxComplaint complaint = new QxComplaint();
		complaint.setInviteId(inviteId);
		complaint.setUserId(userId);
		complaint.setReason(reason);
		qxComplaintService.save(complaint);
		UpdateWrapper<QxInvite> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", inviteId).set("status", INVITE_STATUS.COMPLAINT);
		this.baseMapper.update(null, updateWrapper);
	}

	@Override
	public void comment(Long userId, QxInviteCommentTo commentTo) {
		QxInvite invite = this.getById(commentTo.getInviteId());
		if (!INVITE_STATUS.FINISH.equals(invite.getStatus())) {
			throw new ServiceException("约单未结束，不能评价哦");
		}
		if (!(userId.equals(invite.getInviter()) || userId.equals(invite.getInvitee()))) {
			throw new ServiceException("只能评价自己的约单");
		}
		QxInviteComment inviteComment = new QxInviteComment();
		BeanUtils.copyProperties(commentTo, inviteComment);
		inviteComment.setCommenterId(userId);
		if (userId.equals(invite.getInviter())) {
			inviteComment.setCommenteeId(invite.getInvitee());
		} else {
			inviteComment.setCommenteeId(invite.getInviter());
		}
		qxInviteCommentSerivce.save(inviteComment);
	}

	@Override
	public void alert(Long userId, String emergencyContact, QxInvite invite) {
		Long otherUserId = userId.equals(invite.getInviter()) ? invite.getInvitee() : invite.getInviter();
		QxUser otherUser = qxUserMapper.selectById(otherUserId);
		saveAlert(userId, invite.getId());
		sendAlert(invite, otherUser, emergencyContact);
	}

	public void saveAlert(Long userId, Long inviteId) {
		QxAlert alert = new QxAlert();
		alert.setUserId(userId);
		alert.setInviteId(inviteId);
		alert.setStatus(ALERT_STATUS.UNHANDLE);
		qxAlertService.save(alert);
	}

	public void sendAlert(QxInvite invite, QxUser otherUser, String emergencyContact) {
		Map<String, String> pairs = new HashMap<>();
		pairs.put("inviteTime", invite.getInviteTime().toString());
		pairs.put("location", invite.getLocation());
		pairs.put("contact", otherUser.getMobile());
		noticeHelper.send(emergencyContact, SMS_CODE.EMERGENCY, pairs);
	}

	@Override
	public Page<List<QxInviteSearchPojo>> search(Page page, QxInviteQueryTo inviteQueryTo) {
		return this.baseMapper.search(page, configEntity.getInviteRange(), inviteQueryTo.getLongitude(),
				inviteQueryTo.getLatitude(), inviteQueryTo.getContent());
	}

	@Override
	public int getMyInviteCount(Long userId) {
		QueryWrapper<QxInvite> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("inviter", userId);
		return this.baseMapper.selectCount(queryWrapper);
	}

	@Override
	public int getInviteMeCount(Long userId) {
		QueryWrapper<QxInvite> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("invitee", userId);
		return this.baseMapper.selectCount(queryWrapper);
	}
}
