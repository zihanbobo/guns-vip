package cn.stylefeng.guns.modular.note.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.modular.note.dto.QxAddressTo;
import cn.stylefeng.guns.modular.note.entity.QxAddress;
import cn.stylefeng.guns.modular.note.service.QxAddressService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/address")
public class ApiAddressController extends ApiBaseController {

	@Resource
	private QxAddressService qxAddressService;
	
	@RequestMapping("/list")
	public Object list() {
		QueryWrapper<QxAddress> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", getRequestUserId()).eq("deleted", false);
		List<QxAddress> list = qxAddressService.list(queryWrapper);
		log.info("/api/address/list");
		return ResultGenerator.genSuccessResult(list);
	}
	
	@RequestMapping("/detail")
	public Object detail(Long id) {
		QxAddress address = qxAddressService.getById(id);
		log.info("/api/address/detail, id=" + id);
		return ResultGenerator.genSuccessResult(address);
	}
	
	@RequestMapping("/add")
	public Object add(QxAddressTo addressTo) {
		qxAddressService.addAddress(getRequestUserId(), addressTo);
		log.info("/api/address/add, addressTo=" + addressTo);
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/update")
	public Object update(QxAddressTo addressTo) {
		qxAddressService.updateAddress(getRequestUserId(), addressTo);
		log.info("/api/address/update, addressTo=" + addressTo);
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/delete")
	public Object delete(Long id) {
		qxAddressService.deleteAddress(id);
		log.info("/api/address/delete, id=" + id);
		return ResultGenerator.genSuccessResult();
	}
}
