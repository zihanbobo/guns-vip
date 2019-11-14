package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxCategory;
import cn.stylefeng.guns.modular.note.mapper.QxCategoryMapper;
import cn.stylefeng.guns.modular.note.model.params.QxCategoryParam;
import cn.stylefeng.guns.modular.note.model.result.QxCategoryResult;
import  cn.stylefeng.guns.modular.note.service.QxCategoryService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 商品品类表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-14
 */
@Service
public class QxCategoryServiceImpl extends ServiceImpl<QxCategoryMapper, QxCategory> implements QxCategoryService {

    @Override
    public void add(QxCategoryParam param){
        QxCategory entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxCategoryParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxCategoryParam param){
        QxCategory oldEntity = getOldEntity(param);
        QxCategory newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxCategoryResult findBySpec(QxCategoryParam param){
        return null;
    }

    @Override
    public List<QxCategoryResult> findListBySpec(QxCategoryParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxCategoryParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxCategoryParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxCategory getOldEntity(QxCategoryParam param) {
        return this.getById(getKey(param));
    }

    private QxCategory getEntity(QxCategoryParam param) {
        QxCategory entity = new QxCategory();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
