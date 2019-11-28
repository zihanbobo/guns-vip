package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxCostRate;
import cn.stylefeng.guns.modular.note.mapper.QxCostRateMapper;
import cn.stylefeng.guns.modular.note.model.params.QxCostRateParam;
import cn.stylefeng.guns.modular.note.model.result.QxCostRateResult;
import  cn.stylefeng.guns.modular.note.service.QxCostRateService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 费用比例表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-28
 */
@Service
public class QxCostRateServiceImpl extends ServiceImpl<QxCostRateMapper, QxCostRate> implements QxCostRateService {

    @Override
    public void add(QxCostRateParam param){
        QxCostRate entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxCostRateParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxCostRateParam param){
        QxCostRate oldEntity = getOldEntity(param);
        QxCostRate newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxCostRateResult findBySpec(QxCostRateParam param){
        return null;
    }

    @Override
    public List<QxCostRateResult> findListBySpec(QxCostRateParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxCostRateParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxCostRateParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxCostRate getOldEntity(QxCostRateParam param) {
        return this.getById(getKey(param));
    }

    private QxCostRate getEntity(QxCostRateParam param) {
        QxCostRate entity = new QxCostRate();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
