package cn.stylefeng.guns.modular.note.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.modular.note.dvo.QxPayLogVo;
import cn.stylefeng.guns.modular.note.entity.QxPayLog;
import cn.stylefeng.guns.modular.note.service.QxPayLogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/finance")
public class ApiFinanceController extends ApiBaseController {

	@Resource
	private QxPayLogService qxPayLogService;
	
	@RequestMapping("/buy")
	public Object buy() {
		// TODO:购买金币
		log.info("/api/finance/buy");
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/withdraw")
	public Object withdraw() {
		// TODO: 提现操作
		log.info("/api/finance/withdraw");
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/packages")
	public Object packages() {
		// TODO: 套餐列表
		log.info("/api/finance/packages");
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/log")
	public Object log() {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxPayLog> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", getRequestUserId()).orderByDesc("created_time");
		qxPayLogService.page(page, queryWrapper);
		List<QxPayLogVo> vos =  createPayLogVos(page.getRecords());
		page.setRecords(vos);
		return ResultGenerator.genSuccessResult(page);
	}
	
	public List<QxPayLogVo> createPayLogVos(List<QxPayLog> list) {
		List<QxPayLogVo> vos = new ArrayList<>();
		for (QxPayLog log : list) {
			QxPayLogVo vo = new QxPayLogVo();
			BeanUtils.copyProperties(log, vo);
			vos.add(vo);
		}
		return vos;
	}
}
