package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxPlatformLog;
import cn.stylefeng.guns.modular.note.mapper.QxPlatformLogMapper;
import cn.stylefeng.guns.modular.note.model.params.QxPlatformLogParam;
import cn.stylefeng.guns.modular.note.model.result.QxPlatformLogResult;
import  cn.stylefeng.guns.modular.note.service.QxPlatformLogService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 平台流水表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxPlatformLogServiceImpl extends ServiceImpl<QxPlatformLogMapper, QxPlatformLog> implements QxPlatformLogService {

    @Override
    public void add(QxPlatformLogParam param){
        QxPlatformLog entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxPlatformLogParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxPlatformLogParam param){
        QxPlatformLog oldEntity = getOldEntity(param);
        QxPlatformLog newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxPlatformLogResult findBySpec(QxPlatformLogParam param){
        return null;
    }

    @Override
    public List<QxPlatformLogResult> findListBySpec(QxPlatformLogParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxPlatformLogParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxPlatformLogParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxPlatformLog getOldEntity(QxPlatformLogParam param) {
        return this.getById(getKey(param));
    }

    private QxPlatformLog getEntity(QxPlatformLogParam param) {
        QxPlatformLog entity = new QxPlatformLog();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
