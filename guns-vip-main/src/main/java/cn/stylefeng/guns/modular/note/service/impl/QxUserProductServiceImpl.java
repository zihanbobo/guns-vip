package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxUserProduct;
import cn.stylefeng.guns.modular.note.mapper.QxUserProductMapper;
import cn.stylefeng.guns.modular.note.model.params.QxUserProductParam;
import cn.stylefeng.guns.modular.note.model.result.QxUserProductResult;
import  cn.stylefeng.guns.modular.note.service.QxUserProductService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 兑换记录表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-14
 */
@Service
public class QxUserProductServiceImpl extends ServiceImpl<QxUserProductMapper, QxUserProduct> implements QxUserProductService {

    @Override
    public void add(QxUserProductParam param){
        QxUserProduct entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxUserProductParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxUserProductParam param){
        QxUserProduct oldEntity = getOldEntity(param);
        QxUserProduct newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxUserProductResult findBySpec(QxUserProductParam param){
        return null;
    }

    @Override
    public List<QxUserProductResult> findListBySpec(QxUserProductParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxUserProductParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxUserProductParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxUserProduct getOldEntity(QxUserProductParam param) {
        return this.getById(getKey(param));
    }

    private QxUserProduct getEntity(QxUserProductParam param) {
        QxUserProduct entity = new QxUserProduct();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
