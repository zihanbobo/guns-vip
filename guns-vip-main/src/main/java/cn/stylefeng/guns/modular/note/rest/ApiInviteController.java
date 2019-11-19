package cn.stylefeng.guns.modular.note.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.config.ConfigEntity;
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.core.constant.ProjectConstants.INVITE_OPERATE_TYPE;
import cn.stylefeng.guns.core.constant.ProjectConstants.INVITE_STATUS;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.modular.note.dto.QxInviteTo;
import cn.stylefeng.guns.modular.note.dvo.QxInviteVo;
import cn.stylefeng.guns.modular.note.entity.QxDateType;
import cn.stylefeng.guns.modular.note.entity.QxInvite;
import cn.stylefeng.guns.modular.note.entity.QxInviteApply;
import cn.stylefeng.guns.modular.note.entity.QxInviteOperate;
import cn.stylefeng.guns.modular.note.service.QxDateTypeService;
import cn.stylefeng.guns.modular.note.service.QxInviteApplyService;
import cn.stylefeng.guns.modular.note.service.QxInviteOperateService;
import cn.stylefeng.guns.modular.note.service.QxInviteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/invite")
public class ApiInviteController extends ApiBaseController {

	@Resource
	private ConfigEntity configEntity;

	@Resource
	private QxInviteService qxInviteService;

	@Resource
	private QxDateTypeService qxDateTypeService;

	@Resource
	private QxInviteApplyService qxInviteApplyService;
	
	@Resource
	private QxInviteOperateService qxInviteOperateService;

	/**
	 * 约单四种： | 约单方式 | 约单类型 | inviter | invitee | apply | 事件 | | 多人 | 主动 | A | B,C,D
	 * | B,C,D | 报名 | | 多人 | 被动 | A | B,C,D | B,C,D | 报名 | | 单人 | 主动 | A | B | B |
	 * 同意 | | 单人 | 被动 | A | B | B | 同意 |
	 * 
	 * @param inviteTo
	 * @return
	 */
	@RequestMapping("/add")
	public Object add(QxInviteTo inviteTo) {
		if (getRequestUserId().equals(inviteTo.getInvitee())) {
			throw new ServiceException("邀请对象不能是本人");
		}
		qxInviteService.addInvite(getRequestUserId(), inviteTo);
		log.info("/api/invite/add, inviteTo=" + inviteTo);
		return ResultGenerator.genSuccessResult();
	}

	@RequestMapping("/dateTypes")
	public Object dateTypes() {
		QueryWrapper<QxDateType> queryWrapper = new QueryWrapper<QxDateType>();
		queryWrapper.ge("id", 0);
		queryWrapper.orderByAsc("order_no");
		List<QxDateType> list = qxDateTypeService.list(queryWrapper);
		log.info("/api/invite/dateTypes");
		return ResultGenerator.genSuccessResult(list);
	}

	@RequestMapping("/apply")
	public Object apply(Long inviteId) {
		Long currentUserId = getRequestUserId();
		// 检查重复报名
		checkRepeatApply(currentUserId, inviteId);
		QxInvite invite = qxInviteService.getById(inviteId);
		if (!INVITE_STATUS.WAIT_MATCH.equals(invite.getStatus())) {
			throw new ServiceException("该约单已结束，不能报名");
		}
		qxInviteService.apply(currentUserId, inviteId);
		log.info("/api/invite/apply, inviteId=" + inviteId);
		return ResultGenerator.genSuccessResult();
	}

	private void checkRepeatApply(Long userId, Long inviteId) {
		QueryWrapper<QxInviteApply> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId).eq("invite_id", inviteId);
		int count = qxInviteApplyService.count(queryWrapper);
		if (count > 0) {
			throw new ServiceException("不能重复报名");
		}
	}

	@RequestMapping("/choose")
	public Object choose(Long inviteId, Long userId) {
		// 检查约单状态
		QxInvite invite = qxInviteService.getById(inviteId);
		if (!INVITE_STATUS.WAIT_MATCH.equals(invite.getStatus())) {
			throw new ServiceException("报名已结束，不能选择报名者");
		}
		qxInviteService.choose(inviteId, userId);
		log.info("/api/invite/choose, inviteId=" + inviteId + ", userId=" + userId);
		return ResultGenerator.genSuccessResult();
	}

	@RequestMapping("/agree")
	public Object agree(Long inviteId) {
		// 检查约单状态
		QxInvite invite = qxInviteService.getById(inviteId);
		if (!INVITE_STATUS.WAIT_MATCH.equals(invite.getStatus())) {
			throw new ServiceException("不能重复同意");
		}
		qxInviteService.agree(inviteId);
		log.info("/api/invite/agree, inviteId=" + inviteId);
		return ResultGenerator.genSuccessResult();
	}

	@RequestMapping("/reject")
	public Object reject(Long inviteId) {
		// 检查约单状态
		QxInvite invite = qxInviteService.getById(inviteId);
		if (!INVITE_STATUS.WAIT_MATCH.equals(invite.getStatus())) {
			throw new ServiceException("不能重复拒绝");
		}
		qxInviteService.reject(inviteId);
		log.info("/api/invite/reject, inviteId=" + inviteId);
		return ResultGenerator.genSuccessResult();
	}

	@RequestMapping("/current")
	public Object current() {
		Page page = LayuiPageFactory.defaultPage();
		List<QxInvite> list = qxInviteService.getCurrentInvites(page, getRequestUserId());
		List<QxInviteVo> vos = createQxInviteVos(list);
		page.setRecords(vos);
		log.info("/api/invite/current");
		return ResultGenerator.genSuccessResult(page);
	}
	
	private List<QxInviteVo> createQxInviteVos(List<QxInvite> list) {
		List<QxInviteVo> vos = new ArrayList<>();
		for (QxInvite invite : list) {
			QxInviteVo vo = new QxInviteVo();
			BeanUtils.copyProperties(invite, vo);
			vos.add(vo);
		}
		return vos;
	}
	
	@RequestMapping("/start")
	public Object start(Long inviteId) {
		checkRepeatOperate(inviteId, getRequestUserId(), INVITE_OPERATE_TYPE.CONFIRM_START);
		qxInviteService.start(inviteId, getRequestUserId());
		log.info("/api/invite/start, inviteId=" + inviteId);
		return ResultGenerator.genSuccessResult();
	}
	
	public void checkRepeatOperate(Long inviteId, Long userId, String operateType) {
		QueryWrapper<QxInviteOperate> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId).eq("invite_id", inviteId).eq("type", operateType);
		int count = qxInviteOperateService.count(queryWrapper);
		if (count > 0) {
			throw new ServiceException("不能重复操作");
		}
	}
}
