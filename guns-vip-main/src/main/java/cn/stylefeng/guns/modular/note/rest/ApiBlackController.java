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
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.modular.note.dvo.QxUserVo;
import cn.stylefeng.guns.modular.note.entity.QxBlacklist;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.service.QxBlacklistService;
import cn.stylefeng.guns.modular.note.service.QxUserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/black")
public class ApiBlackController extends ApiBaseController {

	@Resource
	private QxUserService qxUserService;
	
	@Resource
	private QxBlacklistService qxBlacklistService;
	
	@RequestMapping("/list")
	public Object list() {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxBlacklist> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", getRequestUserId());
		qxBlacklistService.page(page, queryWrapper);
		List<QxUserVo> vos = createBlacklistVos(page.getRecords());
		page.setRecords(vos);
		log.info("/api/black/list");
		return ResultGenerator.genSuccessResult(page);
	}
	
	public List<QxUserVo> createBlacklistVos(List<QxBlacklist> list) {
		List<QxUserVo> vos = new ArrayList<>();
		for (QxBlacklist blaclist : list) {
			QxUser user = qxUserService.getById(blaclist.getBlackUserId());
			QxUserVo vo = new QxUserVo();
			BeanUtils.copyProperties(user, vo);
			vos.add(vo);
		}
		return vos;
	}
	
	@RequestMapping("/add")
	public Object add(Long blackUserId) {
		if (blackUserId.equals(getRequestUserId())) {
			throw new ServiceException("不能添加自己到黑名单");
		}
		checkRepeatBlacklist(blackUserId);
		QxBlacklist blacklist = new QxBlacklist();
		blacklist.setBlackUserId(blackUserId);
		blacklist.setUserId(getRequestUserId());
		qxBlacklistService.save(blacklist);
		log.info("/api/black/add, blackUserId=" + blackUserId);
		return ResultGenerator.genSuccessResult();
	}
	
	public void checkRepeatBlacklist(Long blackUserId) {
		QueryWrapper<QxBlacklist> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("black_user_id", blackUserId).eq("user_id", getRequestUserId());
		int count = qxBlacklistService.count(queryWrapper);
		if (count > 0) {
			throw new ServiceException("不能重复添加黑名单");
		}
	}
	
	@RequestMapping("/delete")
	public Object delete(Long id) {
		qxBlacklistService.removeById(id);
		log.info("/api/black/delete, id=" + id);
		return ResultGenerator.genSuccessResult();
	}
}
