package cn.stylefeng.guns.modular.note.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.modular.note.entity.QxGift;
import cn.stylefeng.guns.modular.note.service.QxGiftService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/gift")
public class ApiGiftConotroller extends ApiBaseController {

	@Resource
	private QxGiftService qxGiftService;
	
	@RequestMapping("/list")
	public Object list() {
		List<QxGift> list = qxGiftService.list();
		log.info("/api/gift/list");
		return ResultGenerator.genSuccessResult(list);
	}
}
