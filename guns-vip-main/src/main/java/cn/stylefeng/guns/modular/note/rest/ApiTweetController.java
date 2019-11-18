package cn.stylefeng.guns.modular.note.rest;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.stylefeng.guns.config.ConfigEntity;
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.modular.note.dto.QxTweetTo;
import cn.stylefeng.guns.modular.note.entity.QxTweet;
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

}
