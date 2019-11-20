package cn.stylefeng.guns.modular.note.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.core.constant.ProjectConstants.FEEDBACK_STATUS;
import cn.stylefeng.guns.modular.note.entity.QxFeedback;
import cn.stylefeng.guns.modular.note.service.QxFeedbackService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/feedback")
public class ApiFeedbackController extends ApiBaseController {

	@Resource
	private QxFeedbackService qxFeedbackService;
	
	@RequestMapping("/add")
	public Object add(String type, String content) {
		QxFeedback feedback = new QxFeedback();
		feedback.setUserId(getRequestUserId());
		feedback.setType(type);
		feedback.setContent(content);
		feedback.setStatus(FEEDBACK_STATUS.UNHANDLE);
		qxFeedbackService.save(feedback);
		log.info("/api/feedback/add, type=" + type + ", content=" + content);
		return ResultGenerator.genSuccessResult();
	}
}
