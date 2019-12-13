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
import cn.stylefeng.guns.modular.note.dvo.QxProductVo;
import cn.stylefeng.guns.modular.note.dvo.QxUserVo;
import cn.stylefeng.guns.modular.note.entity.QxCategory;
import cn.stylefeng.guns.modular.note.entity.QxProduct;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.service.QxCategoryService;
import cn.stylefeng.guns.modular.note.service.QxProductService;
import cn.stylefeng.guns.modular.note.service.QxUserProductService;
import cn.stylefeng.guns.modular.note.service.QxUserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ApiProductController extends ApiBaseController {

	@Resource
	private QxProductService qxProductService;
	
	@Resource
	private QxUserProductService qxUserProductService;
	
	@Resource
	private QxUserService qxUserService;
	
	@Resource
	private QxCategoryService qxCategoryService;
	
	@RequestMapping("/category")
	public Object category() {
		QueryWrapper<QxCategory> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByAsc("order_no");
		List<QxCategory> list = qxCategoryService.list(queryWrapper);
		log.info("/api/product/category");
		return ResultGenerator.genSuccessResult(list);
	}
	
	@RequestMapping("/list")
	public Object list(Long categoryId) {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxProduct> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("category_id", categoryId).orderByDesc("created_time");
		qxProductService.page(page, queryWrapper);
		List<QxProductVo> vos = createProductVos(page.getRecords());
		page.setRecords(vos);
		log.info("/api/product/list, categoryId=" + categoryId);
		return ResultGenerator.genSuccessResult(page);
	}
	
	@RequestMapping("/detail")
	public Object detail(Long id) {
		QxProduct product = qxProductService.getById(id);
		QxProductVo vo = createProductVo(product, true);
		log.info("/api/product/detail, id=" + id);
		return ResultGenerator.genSuccessResult(vo);
	}
	
	public List<QxProductVo> createProductVos(List<QxProduct> list) {
		List<QxProductVo> vos = new ArrayList<>();
		for (QxProduct product : list) {
			QxProductVo vo = createProductVo(product, false);
			vos.add(vo);
		}
		return vos;
	}
	
	public QxProductVo createProductVo(QxProduct product, Boolean isDetail) {
		QxProductVo vo = new QxProductVo();
		BeanUtils.copyProperties(product, vo);
		if (isDetail) {
			Page page = LayuiPageFactory.defaultPage();
			List<QxUser> users = qxUserService.getProductUsers(page, product.getId());
			List<QxUserVo> vos = new ArrayList<>();
			for (QxUser user : users) {
				QxUserVo userVo = new QxUserVo();
				BeanUtils.copyProperties(user, userVo);
				vos.add(userVo);
			}
			vo.setUserVos(vos);
		}
		return vo;
	}
	
	@RequestMapping("/recommend")
	public Object recommend() {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxProduct> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("created_time");
		qxProductService.page(page, queryWrapper);
		List<QxProductVo> vos = createProductVos(page.getRecords());
		page.setRecords(vos);
		log.info("/api/product/recommend");
		return ResultGenerator.genSuccessResult(page);
	}
	
	@RequestMapping("/exchange")
	public Object exchange(Long productId, Long addressId) {
		qxProductService.exchange(getRequestUserId(), productId, addressId);
		return ResultGenerator.genSuccessResult();
	}
}
