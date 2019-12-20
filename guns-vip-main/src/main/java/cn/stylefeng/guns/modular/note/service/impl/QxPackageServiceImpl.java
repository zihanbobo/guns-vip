package cn.stylefeng.guns.modular.note.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxPackage;
import cn.stylefeng.guns.modular.note.mapper.QxPackageMapper;
import cn.stylefeng.guns.modular.note.model.params.QxPackageParam;
import cn.stylefeng.guns.modular.note.model.result.QxPackageResult;
import  cn.stylefeng.guns.modular.note.service.QxPackageService;
import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 充值套餐 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-21
 */
@Service
public class QxPackageServiceImpl extends ServiceImpl<QxPackageMapper, QxPackage> implements QxPackageService {

    @Override
    public void add(QxPackageParam param){
        QxPackage entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxPackageParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxPackageParam param){
        QxPackage oldEntity = getOldEntity(param);
        QxPackage newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxPackageResult findBySpec(QxPackageParam param){
        return null;
    }

    @Override
    public List<QxPackageResult> findListBySpec(QxPackageParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxPackageParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxPackageParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxPackage getOldEntity(QxPackageParam param) {
        return this.getById(getKey(param));
    }

    private QxPackage getEntity(QxPackageParam param) {
        QxPackage entity = new QxPackage();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

	@Override
	public QxPackage getByIapId(String iapId) {
		QueryWrapper<QxPackage> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("iap_id", iapId);
		return this.baseMapper.selectOne(queryWrapper);
	}

}
