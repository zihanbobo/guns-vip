package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxDateType;
import cn.stylefeng.guns.modular.note.mapper.QxDateTypeMapper;
import cn.stylefeng.guns.modular.note.model.params.QxDateTypeParam;
import cn.stylefeng.guns.modular.note.model.result.QxDateTypeResult;
import  cn.stylefeng.guns.modular.note.service.QxDateTypeService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-19
 */
@Service
public class QxDateTypeServiceImpl extends ServiceImpl<QxDateTypeMapper, QxDateType> implements QxDateTypeService {

    @Override
    public void add(QxDateTypeParam param){
        QxDateType entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxDateTypeParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxDateTypeParam param){
        QxDateType oldEntity = getOldEntity(param);
        QxDateType newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxDateTypeResult findBySpec(QxDateTypeParam param){
        return null;
    }

    @Override
    public List<QxDateTypeResult> findListBySpec(QxDateTypeParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxDateTypeParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxDateTypeParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxDateType getOldEntity(QxDateTypeParam param) {
        return this.getById(getKey(param));
    }

    private QxDateType getEntity(QxDateTypeParam param) {
        QxDateType entity = new QxDateType();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
