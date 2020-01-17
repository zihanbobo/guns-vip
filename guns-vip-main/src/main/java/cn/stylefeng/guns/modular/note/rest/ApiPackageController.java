package cn.stylefeng.guns.modular.note.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.modular.note.entity.QxPackage;
import cn.stylefeng.guns.modular.note.service.QxPackageService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/package")
public class ApiPackageController extends ApiBaseController {

	@Resource
	private QxPackageService qxPackageService;
	
	@PostMapping("/list")
	public Object list() {
		QueryWrapper<QxPackage> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("deleted", false).orderByAsc("order_no");
		List<QxPackage> list = qxPackageService.list(queryWrapper);
		log.info("/api/package/list");
		return ResultGenerator.genSuccessResult(list);
	}
}
