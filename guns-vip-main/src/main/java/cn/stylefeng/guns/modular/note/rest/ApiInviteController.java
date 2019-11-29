package cn.stylefeng.guns.modular.note.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.core.constant.ProjectConstants.INVITE_OPERATE_TYPE;
import cn.stylefeng.guns.core.constant.ProjectConstants.INVITE_STATUS;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.modular.note.dto.QxInviteCommentTo;
import cn.stylefeng.guns.modular.note.dto.QxInviteQueryTo;
import cn.stylefeng.guns.modular.note.dto.QxInviteTo;
import cn.stylefeng.guns.modular.note.dvo.QxInviteVo;
import cn.stylefeng.guns.modular.note.dvo.QxUserVo;
import cn.stylefeng.guns.modular.note.entity.QxComplaint;
import cn.stylefeng.guns.modular.note.entity.QxDateType;
import cn.stylefeng.guns.modular.note.entity.QxEmergency;
import cn.stylefeng.guns.modular.note.entity.QxInvite;
import cn.stylefeng.guns.modular.note.entity.QxInviteApply;
import cn.stylefeng.guns.modular.note.entity.QxInviteComment;
import cn.stylefeng.guns.modular.note.entity.QxInviteOperate;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.pojo.QxInviteSearchPojo;
import cn.stylefeng.guns.modular.note.pojo.QxInviteUserPojo;
import cn.stylefeng.guns.modular.note.service.QxComplaintService;
import cn.stylefeng.guns.modular.note.service.QxDateTypeService;
import cn.stylefeng.guns.modular.note.service.QxEmergencyService;
import cn.stylefeng.guns.modular.note.service.QxGiftService;
import cn.stylefeng.guns.modular.note.service.QxInviteApplyService;
import cn.stylefeng.guns.modular.note.service.QxInviteCommentService;
import cn.stylefeng.guns.modular.note.service.QxInviteOperateService;
import cn.stylefeng.guns.modular.note.service.QxInviteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/invite")
public class ApiInviteController extends ApiBaseController {

	@Resource
	private QxInviteService qxInviteService;

	@Resource
	private QxDateTypeService qxDateTypeService;

	@Resource
	private QxInviteApplyService qxInviteApplyService;
	
	@Resource
	private QxInviteOperateService qxInviteOperateService;
	
	@Resource
	private QxInviteCommentService qxInviteCommentService;
	
	@Resource
	private QxComplaintService qxComplaintService;
	
	@Resource
	private QxEmergencyService qxEmergencyService;
	
	@Resource
	private QxGiftService qxGiftService;

	/**
	 * 约单四种： 
	 * | 约单方式 | 约单类型 | inviter | invitee | apply | 事件 | 
	 * | 多人 | 主动 | A | B,C | B,C,D | 报名 |
	 * | 多人 | 被动 | A | B,C,D | B,C,D | 报名 |
	 * | 单人 | 主动 | A | B | B | 同意 |
	 * | 单人 | 被动 | A | B | B | 同意 |
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
	
	@PostMapping("/list")
	public Object list(QxInviteQueryTo inviteQueryTo) {
		Page page = LayuiPageFactory.defaultPage();
		qxInviteService.search(page, inviteQueryTo);
		List<QxInviteVo> vos = listQxInviteVos(page.getRecords());
		page.setRecords(vos);
		return ResultGenerator.genSuccessResult(page);
	}
	
	private List<QxInviteVo> listQxInviteVos(List<QxInviteSearchPojo> list) {
		List<QxInviteVo> vos = new ArrayList<>();
		for (QxInviteSearchPojo invite : list) {
			QxInviteVo vo = new QxInviteVo();
			BeanUtils.copyProperties(invite, vo);
			vo.setUserVo(createQxUserVo(getUser(invite.getInviter())));
			vo.setGift(qxGiftService.getById(invite.getGiftId()));
			vo.setDateType(qxDateTypeService.getById(invite.getDateTypeId()));
			vos.add(vo);
		}
		return vos;
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
			vo.setUserVo(createQxUserVo(getUser(invite.getInviter())));
			vo.setGift(qxGiftService.getById(invite.getGiftId()));
			vo.setDateType(qxDateTypeService.getById(invite.getDateTypeId()));
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
	
	@RequestMapping("/finish")
	public Object finish(Long inviteId) {
		checkRepeatOperate(inviteId, getRequestUserId(), INVITE_OPERATE_TYPE.CONFIRM_FINISH);
		qxInviteService.finish(inviteId, getRequestUserId());
		log.info("/api/invite/finish, inviteId=" + inviteId);
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/complaint")
	public Object complaint(Long inviteId, String reason) {
		checkRepeatComplaint(inviteId, getRequestUserId());
		qxInviteService.complaint(inviteId, getRequestUserId(), reason);
		log.info("/api/invite/complatint, inviteId=" + inviteId + ", reason=" + reason);
		return ResultGenerator.genSuccessResult();
	}
	
	public void checkRepeatComplaint(Long inviteId, Long userId) {
		QueryWrapper<QxComplaint> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId).eq("invite_id", inviteId);
		int count = qxComplaintService.count(queryWrapper);
		if (count > 0) {
			throw new ServiceException("不能重复投诉");
		}
	}
	
	@RequestMapping("/comment")
	public Object comment(QxInviteCommentTo commentTo) {
		checkRepeatComment(commentTo.getInviteId(), getRequestUserId());
		qxInviteService.comment(getRequestUserId(), commentTo);
		log.info("/api/invite/comment, commentTo=" + commentTo);
		return ResultGenerator.genSuccessResult();
	}
	
	public void checkRepeatComment(Long inviteId, Long userId) {
		QueryWrapper<QxInviteComment> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("invite_id", inviteId).eq("commenter_id", userId);
		int count = qxInviteCommentService.count(queryWrapper);
		if (count > 0) {
			throw new ServiceException("不能重复评价");
		}
	}
	
	@RequestMapping("/alert")
	public Object alert(Long inviteId) {
		QxInvite invite = qxInviteService.getById(inviteId);
		QxEmergency emergency = qxEmergencyService.getDefaultEmergency(getRequestUserId());
		qxInviteService.alert(getRequestUserId(), emergency.getContact(), invite);
		log.info("/api/invite/alert, inviteId=" + inviteId);
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/myInvite")
	public Object myInvite() {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxInvite> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("inviter", getRequestUserId());
		qxInviteService.page(page, queryWrapper);
		List<QxInviteVo> vos = createQxInviteVos(page.getRecords());
		page.setRecords(vos);
		log.info("/api/invite/current");
		return ResultGenerator.genSuccessResult(page);
	}
	
	@RequestMapping("/inviteMe")
	public Object inviteMe() {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxInvite> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("invitee", getRequestUserId());
		qxInviteService.page(page, queryWrapper);
		List<QxInviteVo> vos = createQxInviteVos(page.getRecords());
		page.setRecords(vos);
		log.info("/api/invite/current");
		return ResultGenerator.genSuccessResult(page);
	}
	
	@PostMapping("/applicants")
	public Object applicants(Long inviteId) {
		List<QxInviteUserPojo> list = qxInviteService.applicants(inviteId);
		List<QxUserVo> vos = new ArrayList<>();
		for (QxInviteUserPojo userPojo : list) {
			QxUser user = qxUserService.getById(userPojo.getUserId());
			vos.add(createQxUserVo(user));
		}
		return ResultGenerator.genSuccessResult(vos);
	}
}
