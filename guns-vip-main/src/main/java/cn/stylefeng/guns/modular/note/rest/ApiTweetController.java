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
import com.google.common.base.Strings;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.modular.note.dto.QxTweetTo;
import cn.stylefeng.guns.modular.note.dvo.QxTweetVo;
import cn.stylefeng.guns.modular.note.entity.QxTweet;
import cn.stylefeng.guns.modular.note.service.QxGiftService;
import cn.stylefeng.guns.modular.note.service.QxTweetLikeService;
import cn.stylefeng.guns.modular.note.service.QxTweetService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/tweet")
public class ApiTweetController extends ApiBaseController {

	@Resource
	private QxTweetService qxTweetService;
	
	@Resource
	private QxGiftService qxGiftService;
	
	@Resource
	private QxTweetLikeService qxTweetLikeService;

	@PostMapping("/add")
	public Object add(QxTweetTo tweetTo) {
		QxTweet tweet = new QxTweet();
		BeanUtils.copyProperties(tweetTo, tweet);
		tweet.setUserId(getRequestUserId());
		qxTweetService.save(tweet);
		log.info("/api/tweet/add, tweetTo=" + tweetTo);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/list")
	public Object list(String keywords) {
		//获取分页参数
        Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxTweet> queryWrapper = new QueryWrapper<>();
		if (!Strings.isNullOrEmpty(keywords)) {
			queryWrapper.like("title", keywords);
		}
		queryWrapper.orderByDesc("created_time");
		qxTweetService.page(page, queryWrapper);
		List<QxTweetVo> vos = createQxTweetVos(page.getRecords());
		page.setRecords(vos);
		log.info("/api/tweet/list, keywords=" + keywords);
		return ResultGenerator.genSuccessResult(page);
	}
	
	public List<QxTweetVo> createQxTweetVos(List<QxTweet> list) {
		List<QxTweetVo> vos = new ArrayList<>();
		for (QxTweet tweet : list) {
			QxTweetVo vo = createQxTweetVo(tweet);
			vos.add(vo);
		}
		return vos;
	}
	
	public QxTweetVo createQxTweetVo(QxTweet tweet) {
		QxTweetVo vo = new QxTweetVo();
		BeanUtils.copyProperties(tweet, vo);
		vo.setUserVo(createQxUserVo(getUser(tweet.getUserId())));
		return vo;
	}
	
	@PostMapping("/userList")
	public Object userList(Long userId) {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxTweet> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId).orderByDesc("created_time");
		qxTweetService.page(page, queryWrapper);
		List<QxTweetVo> vos = createQxTweetVos(page.getRecords());
		page.setRecords(vos);
		log.info("/api/tweet/userList");
		return ResultGenerator.genSuccessResult(page);
	}
	
	@PostMapping("/detail")
	public Object detail(Long id) {
		QxTweet tweet = qxTweetService.getById(id);
		QxTweetVo vo = createQxTweetVo(tweet);
		log.info("/api/tweet/detail");
		return ResultGenerator.genSuccessResult(vo);
	}
	
	@PostMapping("/like")
	public Object like(Long id) {
		qxTweetService.like(getRequestUserId(), id);
		log.info("/api/tweet/like, id=" + id);
		return ResultGenerator.genSuccessResult();
	}
	
	@PostMapping("/myTweet")
	public Object myTweet() {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxTweet> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", getRequestUserId()).orderByDesc("created_time");
		qxTweetService.page(page, queryWrapper);
		List<QxTweetVo> vos = createQxTweetVos(page.getRecords());
		page.setRecords(vos);
		log.info("/api/tweet/myTweet");
		return ResultGenerator.genSuccessResult(page);
	}
	
	@PostMapping("/followList")
	public Object followList() {
		Page page = LayuiPageFactory.defaultPage();
		qxTweetService.followList(page, getRequestUserId());
		List<QxTweetVo> vos = createQxTweetVos(page.getRecords());
		page.setRecords(vos);
		log.info("/api/tweet/followList");
		return ResultGenerator.genSuccessResult(page);
	}
	
	@PostMapping("/reward")
	public Object reward(Long userId, Long tweetId, Long giftId) {
		if (getRequestUserId().equals(userId)) {
			throw new ServiceException("不能给自己打赏");
		}
		qxTweetService.rewardTweet(getRequestUserId(), userId, tweetId, giftId);
		log.info("/api/tweet/reward, userId=" + userId + ",giftId=" + giftId);
		return ResultGenerator.genSuccessResult();
	}
}
