package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNotification;
import cn.stylefeng.guns.modular.note.mapper.QxNotificationMapper;
import cn.stylefeng.guns.modular.note.model.params.QxNotificationParam;
import cn.stylefeng.guns.modular.note.model.result.QxNotificationResult;
import  cn.stylefeng.guns.modular.note.service.QxNotificationService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统通知表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-14
 */
@Service
public class QxNotificationServiceImpl extends ServiceImpl<QxNotificationMapper, QxNotification> implements QxNotificationService {

    @Override
    public void add(QxNotificationParam param){
        QxNotification entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxNotificationParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxNotificationParam param){
        QxNotification oldEntity = getOldEntity(param);
        QxNotification newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxNotificationResult findBySpec(QxNotificationParam param){
        return null;
    }

    @Override
    public List<QxNotificationResult> findListBySpec(QxNotificationParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxNotificationParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxNotificationParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxNotification getOldEntity(QxNotificationParam param) {
        return this.getById(getKey(param));
    }

    private QxNotification getEntity(QxNotificationParam param) {
        QxNotification entity = new QxNotification();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
