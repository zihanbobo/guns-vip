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
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.modular.note.dto.QxNoteTo;
import cn.stylefeng.guns.modular.note.dvo.QxNoteVo;
import cn.stylefeng.guns.modular.note.dvo.QxTweetVo;
import cn.stylefeng.guns.modular.note.entity.QxNote;
import cn.stylefeng.guns.modular.note.entity.QxUserNote;
import cn.stylefeng.guns.modular.note.service.QxGiftService;
import cn.stylefeng.guns.modular.note.service.QxNoteService;
import cn.stylefeng.guns.modular.note.service.QxUserNoteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/note")
public class ApiNoteController extends ApiBaseController {

	@Resource
	private QxNoteService qxNoteService;

	@Resource
	private QxUserNoteService qxUserNoteService;

	@Resource
	private QxGiftService qxGiftService;
	
	@RequestMapping("/list")
	public Object list(Long userId) {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxNote> queryWrapper = new QueryWrapper();
		queryWrapper.eq("user_id", userId);
		queryWrapper.orderByDesc("created_time");
		qxNoteService.page(page, queryWrapper);
		List<QxNoteVo> vos = createQxNoteVos(page.getRecords());
		page.setRecords(vos);
		log.info("/api/note/list, usrId=" + userId);
		return ResultGenerator.genSuccessResult(vos);
	}

	private List<QxNoteVo> createQxNoteVos(List<QxNote> list) {
		Long currentUserId = getRequestUserId();
		List<QxNoteVo> vos = new ArrayList<>();
		List<Long> paidNotes = getPaidNotes(currentUserId);
		for (QxNote note : list) {
			QxNoteVo vo = new QxNoteVo();
			BeanUtils.copyProperties(note, vo);
			if (!note.getIsPrivate() || paidNotes.contains(currentUserId)) {
				vo.setIsLock(false);
			} else {
				vo.setIsLock(true);
			}
			vo.setUserVo(createQxUserVo(getUser(note.getUserId())));
			vos.add(vo);
		}
		return vos;
	}

	private List<Long> getPaidNotes(Long userId) {
		QueryWrapper<QxUserNote> queryWrapper = new QueryWrapper();
		queryWrapper.eq("user_id", userId);
		List<QxUserNote> userNotes = qxUserNoteService.list(queryWrapper);
		List<Long> paidNotes = new ArrayList<>();
		for (QxUserNote userNote : userNotes) {
			paidNotes.add(userNote.getNoteId());
		}
		return paidNotes;
	}

	@RequestMapping("/add")
	public Object add(QxNoteTo noteTo) {
		QxNote note = new QxNote();
		BeanUtils.copyProperties(noteTo, note);
		note.setUserId(getRequestUserId());
		qxNoteService.save(note);
		log.info("/api/note/add, noteTo=" + noteTo);
		return ResultGenerator.genSuccessResult();
	}

	@RequestMapping("/like")
	public Object like(Long noteId) {
		QxNote note = qxNoteService.getById(noteId);
		note.setFavoriteCount(note.getFavoriteCount() + 1);
		qxNoteService.updateById(note);
		log.info("/api/note/like, noteId=" + noteId);
		return ResultGenerator.genSuccessResult(note.getFavoriteCount());
	}
	
	@RequestMapping("/myNote")
	public Object myNote() {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxNote> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", getRequestUserId()).orderByDesc("created_time");
		qxNoteService.page(page, queryWrapper);
		List<QxTweetVo> vos = createQxNoteVos(page.getRecords());
		page.setRecords(vos);
		log.info("/api/note/myNote");
		return ResultGenerator.genSuccessResult(page);
	}
	
	@RequestMapping("/userNotes")
	public Object userNotes(Long userId) {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxNote> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId).orderByDesc("created_time");
		qxNoteService.page(page, queryWrapper);
		List<QxTweetVo> vos = createQxNoteVos(page.getRecords());
		page.setRecords(vos);
		log.info("/api/note/userNotes");
		return ResultGenerator.genSuccessResult(page);
	}
	
	@PostMapping("/reward")
	public Object reward(Long userId, Long noteId, Long giftId) {
		if (getRequestUserId().equals(userId)) {
			throw new ServiceException("不能给自己打赏");
		}
		qxGiftService.rewardNote(getRequestUserId(), userId, noteId, giftId);
		log.info("/api/note/reward, userId=" + userId + ",giftId=" + giftId);
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/unlock")
	public Object unlock(Long noteId) {
		QxNote note = qxNoteService.getById(noteId);
		if (note.getUserId().equals(getRequestUserId())) {
			throw new ServiceException("不能解锁自己的日记");
		}
		qxGiftService.unlockNote(getRequestUserId(), note.getUserId(), noteId, note.getGiftId());
		log.info("/api/note/unlock, noteId=" + noteId);
		return ResultGenerator.genSuccessResult();
	}
}
