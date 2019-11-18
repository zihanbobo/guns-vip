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
import cn.stylefeng.guns.modular.note.dto.QxTweetCommentTo;
import cn.stylefeng.guns.modular.note.dvo.QxTweetCommentVo;
import cn.stylefeng.guns.modular.note.entity.QxTweetComment;
import cn.stylefeng.guns.modular.note.service.QxTweetCommentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/tweet/comment")
public class ApiTweetCommentController extends ApiBaseController {

	@Resource
	private ConfigEntity configEntity;
	
	@Resource
	private QxTweetCommentService qxTweetCommentService;
	
	@RequestMapping("/list")
	public Object list(Long tweetId) {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxTweetComment> queryWrapper = new QueryWrapper();
		queryWrapper.eq("tweet_id", tweetId);
		queryWrapper.orderByDesc("created_time");
		qxTweetCommentService.page(page, queryWrapper);
		List<QxTweetCommentVo> vos = createQxTweetCommentVos(page.getRecords());
		page.setRecords(vos);
		log.info("/api/tweet/comment/list, tweetId=" + tweetId);
		return ResultGenerator.genSuccessResult(page);
	}
	
	private List<QxTweetCommentVo> createQxTweetCommentVos(List<QxTweetComment> list) {
		List<QxTweetCommentVo> vos = new ArrayList<>();
		for (QxTweetComment comment : list) {
			QxTweetCommentVo vo = new QxTweetCommentVo();
			BeanUtils.copyProperties(comment, vo);
			vo.setUserVo(createQxUserVo(getUser(comment.getCreatedBy())));
			vos.add(vo);
		}
		return vos;
	}
	
	@RequestMapping("/add")
	public Object add(QxTweetCommentTo commentTo) {
		QxTweetComment tweetComment = new QxTweetComment();
		BeanUtils.copyProperties(commentTo, tweetComment);
		tweetComment.setCreatedBy(getRequestUserId());
		qxTweetCommentService.save(tweetComment);
		log.info("/api/tweet/comment/add, commentTo=" + commentTo);
		return ResultGenerator.genSuccessResult();
	}
}
