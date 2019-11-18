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
import cn.stylefeng.guns.modular.note.dto.QxTweetTo;
import cn.stylefeng.guns.modular.note.dvo.QxTweetVo;
import cn.stylefeng.guns.modular.note.dvo.QxUserVo;
import cn.stylefeng.guns.modular.note.entity.QxTweet;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.service.QxTweetService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/tweet")
public class ApiTweetController extends ApiBaseController {

	@Resource
	private ConfigEntity configEntity;

	@Resource
	private QxTweetService qxTweetService;

	@RequestMapping("/add")
	public Object add(QxTweetTo tweetTo) {
		QxTweet tweet = new QxTweet();
		BeanUtils.copyProperties(tweetTo, tweet);
		tweet.setUserId(getRequestUserId());
		qxTweetService.save(tweet);
		log.info("/api/tweet/add, tweetTo=" + tweetTo);
		return ResultGenerator.genSuccessResult();
	}

	@RequestMapping("/list")
	public Object list(String keywords) {
		//获取分页参数
        Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxTweet> wrapper = new QueryWrapper<>();
		wrapper.like("title", keywords);
		qxTweetService.page(page, wrapper);
		List<QxTweetVo> vos = createQxTweetVos(page.getRecords());
		page.setRecords(vos);
		log.info("/api/tweet/list, keywords=" + keywords);
		return ResultGenerator.genSuccessResult(page);
	}
	
	public List<QxTweetVo> createQxTweetVos(List<QxTweet> list) {
		List<QxTweetVo> vos = new ArrayList<>();
		for (QxTweet tweet : list) {
			QxTweetVo vo = new QxTweetVo();
			BeanUtils.copyProperties(tweet, vo);
			vo.setUserVo(createQxUserVo(getUser(tweet.getUserId())));
			vos.add(vo);
		}
		return vos;
	}
	
	public QxUserVo createQxUserVo(QxUser user) {
		QxUserVo vo = new QxUserVo();
		BeanUtils.copyProperties(user, vo);
		return vo;
	}
}
