package cn.stylefeng.guns.modular.note.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.stylefeng.guns.modular.note.entity.QxPayLog;
import cn.stylefeng.guns.modular.note.mapper.QxPayLogMapper;

@Component
public class QxPayLogHelper {

	@Resource
	private QxPayLogMapper qxPayLogMapper;
	
	public void createPayLog(Long userId, Integer amount, String type) {
		QxPayLog entity = new QxPayLog();
		entity.setUserId(userId);
		entity.setAmount(amount);
		entity.setType(type);
		qxPayLogMapper.insert(entity);
	}
}
