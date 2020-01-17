package cn.stylefeng.guns.modular.note.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

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
		QueryWrapper<QxGift> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("deleted", false).orderByAsc("price");
		List<QxGift> list = qxGiftService.list(queryWrapper);
		log.info("/api/gift/list");
		return ResultGenerator.genSuccessResult(list);
	}
}
