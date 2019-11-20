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
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.modular.note.dvo.QxUserVo;
import cn.stylefeng.guns.modular.note.entity.QxFollow;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.service.QxFollowService;
import cn.stylefeng.guns.modular.note.service.QxUserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/follow")
public class ApiFollowController extends ApiBaseController {

	@Resource
	private QxFollowService qxFollowService;
	
	@Resource
	private QxUserService qxUserService;
	
	@RequestMapping("/follow")
	public Object follow(Long userId) {
		Long followerId = getRequestUserId();
		long followeeId = userId;
		
		if (followerId.equals(followeeId)) {
			throw new ServiceException("不能关注自己");
		}
		checkRepeatFollow(followerId, followeeId);
		qxFollowService.follow(followerId, followeeId);
		log.info("/api/follow/follow, userId=" + userId);
		return ResultGenerator.genSuccessResult();
		
	}
	
	public void checkRepeatFollow(Long followerId, Long followeeId) {
		QueryWrapper<QxFollow> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("follower_id", followerId).eq("followee_id", followeeId);
		int count = qxFollowService.count(queryWrapper);
		if (count > 0) {
			throw new ServiceException("不能重复关注");
		}
	}
	
	@RequestMapping("/follower")
	public Object follower() {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxFollow> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("followee_id", getRequestUserId()).orderByDesc("created_time");
		qxFollowService.page(page, queryWrapper);
		List<QxUserVo> vos = createQxUserVos(page.getRecords(), true);
		page.setRecords(vos);
		log.info("/api/follow/follower");
		return ResultGenerator.genSuccessResult(page);
	}
	
	public List<QxUserVo> createQxUserVos(List<QxFollow> list, Boolean isFollower) {
		List<QxUserVo> vos = new ArrayList<>();
		for (QxFollow follow : list) {
			Long userId = isFollower ? follow.getFollowerId() : follow.getFolloweeId();
			QxUser user = qxUserService.getById(userId);
			QxUserVo vo = new QxUserVo();
			BeanUtils.copyProperties(user, vo);
			vos.add(vo);
		}
		return vos;
	}
	
	@RequestMapping("/followee")
	public Object followee() {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxFollow> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("follower_id", getRequestUserId()).orderByDesc("created_time");
		qxFollowService.page(page, queryWrapper);
		List<QxUserVo> vos = createQxUserVos(page.getRecords(), false);
		page.setRecords(vos);
		log.info("/api/follow/followee");
		return ResultGenerator.genSuccessResult(page);
	}
	
	@RequestMapping("/cancel")
	public Object cancel(Long userId) {
		Long followerId = getRequestUserId();
		Long followeeId = userId;
		QueryWrapper<QxFollow> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("follower_id", followerId).eq("followee_id", followeeId);
		qxFollowService.remove(queryWrapper);
		log.info("/api/follow/cancel, userId=" + userId);
		return ResultGenerator.genSuccessResult();
	}
}
