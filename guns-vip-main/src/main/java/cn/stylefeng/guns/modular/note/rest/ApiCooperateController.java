package cn.stylefeng.guns.modular.note.rest;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.core.constant.ProjectConstants.COOPERATE_STATUS;
import cn.stylefeng.guns.modular.note.dto.QxCooperateTo;
import cn.stylefeng.guns.modular.note.entity.QxCooperate;
import cn.stylefeng.guns.modular.note.service.QxCooperateService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/cooperate")
public class ApiCooperateController extends ApiBaseController {

	@Resource
	private QxCooperateService qxCooperateService;
	
	@RequestMapping("/add")
	public Object add(QxCooperateTo cooperateTo) {
		QxCooperate cooperate = new QxCooperate();
		BeanUtils.copyProperties(cooperateTo, cooperate);
		cooperate.setUserId(getRequestUserId());
		cooperate.setStatus(COOPERATE_STATUS.UNHANDLE);
		qxCooperateService.save(cooperate);
		log.info("/api/cooperate/add, cooperateTo=" + cooperateTo);
		return ResultGenerator.genSuccessResult();
	}
}
