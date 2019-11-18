package cn.stylefeng.guns.modular.note.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.stylefeng.guns.config.ConfigEntity;
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.modular.note.dto.QxInviteTo;
import cn.stylefeng.guns.modular.note.service.QxInviteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/invite")
public class ApiInviteController extends ApiBaseController {

	@Resource
	private ConfigEntity configEntity;
	
	@Resource
	private QxInviteService qxInviteService;
	
	@RequestMapping("/add")
	public Object add(QxInviteTo inviteTo) {
		qxInviteService.addInvite(getRequestUserId(), inviteTo);
		log.info("/api/invite/add, inviteTo=" + inviteTo);
		return ResultGenerator.genSuccessResult();
	}
}
