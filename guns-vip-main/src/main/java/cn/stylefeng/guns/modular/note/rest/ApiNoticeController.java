package cn.stylefeng.guns.modular.note.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.stylefeng.guns.core.ResultGenerator;
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
		return ResultGenerator.genSuccessResult();
	}
}
