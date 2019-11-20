package cn.stylefeng.guns.modular.note.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.modular.note.entity.QxEmergency;
import cn.stylefeng.guns.modular.note.service.QxEmergencyService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/emergency")
public class ApiEmergencyController extends ApiBaseController {

	@Resource
	private QxEmergencyService qxEmergencyService;
	
	@RequestMapping("/list")
	public Object list() {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxEmergency> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", getRequestUserId()).orderByDesc("created_time");
		qxEmergencyService.page(page, queryWrapper);
		log.info("/api/emergency/list");
		return ResultGenerator.genSuccessResult(page);
	}
}
