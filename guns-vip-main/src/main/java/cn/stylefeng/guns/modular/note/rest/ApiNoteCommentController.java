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
import cn.stylefeng.guns.modular.note.dto.QxNoteCommentTo;
import cn.stylefeng.guns.modular.note.dvo.QxNoteCommentVo;
import cn.stylefeng.guns.modular.note.dvo.QxTweetCommentVo;
import cn.stylefeng.guns.modular.note.entity.QxNoteComment;
import cn.stylefeng.guns.modular.note.service.QxNoteCommentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/note/comment")
public class ApiNoteCommentController extends ApiBaseController {

	@Resource
	private ConfigEntity configEntity;

	@Resource
	private QxNoteCommentService qxNoteCommentService;

	@RequestMapping("/list")
	public Object list(Long noteId) {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxNoteComment> queryWrapper = new QueryWrapper();
		queryWrapper.eq("note_id", noteId);
		queryWrapper.orderByDesc("created_time");
		qxNoteCommentService.page(page, queryWrapper);
		List<QxTweetCommentVo> vos = createQxNoteCommentVos(page.getRecords());
		page.setRecords(vos);
		log.info("/api/tweet/comment/list, noteId=" + noteId);
		return ResultGenerator.genSuccessResult(page);
	}

	private List<QxNoteCommentVo> createQxNoteCommentVos(List<QxNoteComment> list) {
		List<QxNoteCommentVo> vos = new ArrayList<>();
		for (QxNoteComment comment : list) {
			QxNoteCommentVo vo = new QxNoteCommentVo();
			BeanUtils.copyProperties(comment, vo);
			vo.setUserVo(createQxUserVo(getUser(comment.getCreatedBy())));
			vos.add(vo);
		}
		return vos;
	}
	
	@RequestMapping("/add")
	public Object add(QxNoteCommentTo commentTo) {
		QxNoteComment noteComment = new QxNoteComment();
		BeanUtils.copyProperties(commentTo, noteComment);
		noteComment.setCreatedBy(getRequestUserId());
		qxNoteCommentService.save(noteComment);
		log.info("/api/note/comment/add, commentTo=" + commentTo);
		return ResultGenerator.genSuccessResult();
	}
}
