package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxProduct;
import cn.stylefeng.guns.modular.note.mapper.QxProductMapper;
import cn.stylefeng.guns.modular.note.model.params.QxProductParam;
import cn.stylefeng.guns.modular.note.model.result.QxProductResult;
import  cn.stylefeng.guns.modular.note.service.QxProductService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-14
 */
@Service
public class QxProductServiceImpl extends ServiceImpl<QxProductMapper, QxProduct> implements QxProductService {

    @Override
    public void add(QxProductParam param){
        QxProduct entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxProductParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxProductParam param){
        QxProduct oldEntity = getOldEntity(param);
        QxProduct newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxProductResult findBySpec(QxProductParam param){
        return null;
    }

    @Override
    public List<QxProductResult> findListBySpec(QxProductParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxProductParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxProductParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxProduct getOldEntity(QxProductParam param) {
        return this.getById(getKey(param));
    }

    private QxProduct getEntity(QxProductParam param) {
        QxProduct entity = new QxProduct();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
