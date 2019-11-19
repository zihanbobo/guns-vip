package cn.stylefeng.guns.modular.note.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.core.CommonUtils;
import cn.stylefeng.guns.core.constant.ProjectConstants.INVITE_APPLY_STATUS;
import cn.stylefeng.guns.core.constant.ProjectConstants.INVITE_STATUS;
import cn.stylefeng.guns.core.constant.ProjectConstants.SMS_CODE;
import cn.stylefeng.guns.core.util.NoticeHelper;
import cn.stylefeng.guns.modular.note.dto.QxInviteTo;
import cn.stylefeng.guns.modular.note.entity.QxInvite;
import cn.stylefeng.guns.modular.note.entity.QxInviteApply;
import cn.stylefeng.guns.modular.note.mapper.QxInviteApplyMapper;
import cn.stylefeng.guns.modular.note.mapper.QxInviteMapper;
import cn.stylefeng.guns.modular.note.model.params.QxInviteParam;
import cn.stylefeng.guns.modular.note.model.result.QxInviteResult;
import cn.stylefeng.guns.modular.note.pojo.QxInviteUserPojo;
import  cn.stylefeng.guns.modular.note.service.QxInviteService;
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
	private QxInviteMapper qxInviteMapper;
	
	@Resource
	private QxInviteApplyMapper qxInviteApplyMapper;
	
	@Resource
	private NoticeHelper noticeHelper;
    
	@Override
    public void add(QxInviteParam param){
        QxInvite entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxInviteParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxInviteParam param){
        QxInvite oldEntity = getOldEntity(param);
        QxInvite newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxInviteResult findBySpec(QxInviteParam param){
        return null;
    }

    @Override
    public List<QxInviteResult> findListBySpec(QxInviteParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxInviteParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxInviteParam param){
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
			
			if (inviteUser.getChoosed()) {
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
}
