package cn.stylefeng.guns.modular.note.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.core.constant.ProjectConstants.NOTICE_TYPE;
import cn.stylefeng.guns.modular.note.entity.QxNotice;
import cn.stylefeng.guns.modular.note.service.QxNoticeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/notice")
public class ApiNoticeController extends ApiBaseController {

	@Resource
	private QxNoticeService qxNoticeService;
	
	@RequestMapping("/list")
	public Object list() {
		String account = getUser().getMobile();
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxNotice> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("account", account).eq("type", NOTICE_TYPE.PUSH).orderByDesc("created_time");
		qxNoticeService.page(page, queryWrapper);
		log.info("/api/notice/list");
		return ResultGenerator.genSuccessResult(page);
	}
	
	@RequestMapping("/detail")
	public Object detail(Long id) {
		QxNotice notice = qxNoticeService.getById(id);
		log.info("/api/notice/detail, id=" + id);
		return ResultGenerator.genSuccessResult(notice);
	}
	
	@RequestMapping("/read")
	public Object read(Long id) {
		UpdateWrapper<QxNotice> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", id).set("status_read", true);
		qxNoticeService.update(updateWrapper);
		log.info("/api/notice/read, id=" + id);
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/delete")
	public Object delete(Long id) {
		qxNoticeService.removeById(id);
		log.info("/api/notice/delete, id=" + id);
		return ResultGenerator.genSuccessResult();
	}
}
