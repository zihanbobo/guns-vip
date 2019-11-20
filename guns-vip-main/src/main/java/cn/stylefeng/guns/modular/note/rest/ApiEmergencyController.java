package cn.stylefeng.guns.modular.note.rest;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.modular.note.dto.QxEmergencyTo;
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
	
	@RequestMapping("/add")
	public Object add(QxEmergencyTo emergencyTo) {
		checkRepeatEmergency(emergencyTo);
		QxEmergency emergency = new QxEmergency();
		BeanUtils.copyProperties(emergencyTo, emergency);
		emergency.setUserId(getRequestUserId());
		emergency.setIsDefault(false);
		qxEmergencyService.save(emergency);
		log.info("/api/emergency/add, emergencyTo=" + emergencyTo);
		return ResultGenerator.genSuccessResult();
	}
	
	public void checkRepeatEmergency(QxEmergencyTo emergencyTo) {
		QueryWrapper<QxEmergency> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", getRequestUserId()).eq("contact", emergencyTo.getContact());
		int count = qxEmergencyService.count(queryWrapper);
		if (count > 0) {
			throw new ServiceException("不能重复添加紧急联系人");
		}
	}
	
	@RequestMapping("/detail")
	public Object detail(Long id) {
		QxEmergency emergency = qxEmergencyService.getById(id);
		log.info("/api/emergency/detail, id=" + id);
		return ResultGenerator.genSuccessResult(emergency);
	}
	
	@RequestMapping("/update")
	public Object update(QxEmergencyTo emergencyTo) {
		QxEmergency emergency = new QxEmergency();
		BeanUtils.copyProperties(emergencyTo, emergency);
		qxEmergencyService.updateById(emergency);
		log.info("/api/emergency/update, emergencyTo=" + emergencyTo);
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/delete")
	public Object delete(Long id) {
		qxEmergencyService.removeById(id);
		log.info("/api/emergency/delete, id=" + id);
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/setDefault")
	public Object setDefault(Long id) {
		UpdateWrapper<QxEmergency> resetWrapper = new UpdateWrapper<>();
		resetWrapper.eq("user_id", getRequestUserId()).set("is_default", false);
		qxEmergencyService.update(resetWrapper);
		
		UpdateWrapper<QxEmergency> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", id).set("is_default", true);
		qxEmergencyService.update(updateWrapper);
		log.info("/api/emergency/setDefault, id=" + id);
		return ResultGenerator.genSuccessResult();
	}
}
