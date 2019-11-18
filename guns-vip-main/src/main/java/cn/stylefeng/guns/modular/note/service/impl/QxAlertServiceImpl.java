package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxAlert;
import cn.stylefeng.guns.modular.note.mapper.QxAlertMapper;
import cn.stylefeng.guns.modular.note.model.params.QxAlertParam;
import cn.stylefeng.guns.modular.note.model.result.QxAlertResult;
import  cn.stylefeng.guns.modular.note.service.QxAlertService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 约单报警记录 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxAlertServiceImpl extends ServiceImpl<QxAlertMapper, QxAlert> implements QxAlertService {

    @Override
    public void add(QxAlertParam param){
        QxAlert entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxAlertParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxAlertParam param){
        QxAlert oldEntity = getOldEntity(param);
        QxAlert newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxAlertResult findBySpec(QxAlertParam param){
        return null;
    }

    @Override
    public List<QxAlertResult> findListBySpec(QxAlertParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxAlertParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxAlertParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxAlert getOldEntity(QxAlertParam param) {
        return this.getById(getKey(param));
    }

    private QxAlert getEntity(QxAlertParam param) {
        QxAlert entity = new QxAlert();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
