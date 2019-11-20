package cn.stylefeng.guns.modular.note.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.mapper.QxUserMapper;
import cn.stylefeng.guns.modular.note.model.params.QxUserParam;
import cn.stylefeng.guns.modular.note.model.result.QxUserResult;
import cn.stylefeng.guns.modular.note.service.QxUserService;
import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author
 * @since 2019-11-14
 */
@Service
public class QxUserServiceImpl extends ServiceImpl<QxUserMapper, QxUser> implements QxUserService {

	@Override
	public void add(QxUserParam param) {
		QxUser entity = getEntity(param);
		this.save(entity);
	}

	@Override
	public void delete(QxUserParam param) {
		this.removeById(getKey(param));
	}

	@Override
	public void update(QxUserParam param) {
		QxUser oldEntity = getOldEntity(param);
		QxUser newEntity = getEntity(param);
		ToolUtil.copyProperties(newEntity, oldEntity);
		this.updateById(newEntity);
	}

	@Override
	public QxUserResult findBySpec(QxUserParam param) {
		return null;
	}

	@Override
	public List<QxUserResult> findListBySpec(QxUserParam param) {
		return null;
	}

	@Override
	public LayuiPageInfo findPageBySpec(QxUserParam param) {
		Page pageContext = getPageContext();
		IPage page = this.baseMapper.customPageList(pageContext, param);
		return LayuiPageFactory.createPageInfo(page);
	}

	private Serializable getKey(QxUserParam param) {
		return param.getId();
	}

	private Page getPageContext() {
		return LayuiPageFactory.defaultPage();
	}

	private QxUser getOldEntity(QxUserParam param) {
		return this.getById(getKey(param));
	}

	private QxUser getEntity(QxUserParam param) {
		QxUser entity = new QxUser();
		ToolUtil.copyProperties(param, entity);
		return entity;
	}

	@Override
	public QxUser getUserByAccount(String account) {
		return this.baseMapper.getByAccount(account);
	}

	@Override
	public List<QxUser> getProductUsers(Page page, Long id) {
		return this.baseMapper.getProductUsers(page, id);
	}
}
