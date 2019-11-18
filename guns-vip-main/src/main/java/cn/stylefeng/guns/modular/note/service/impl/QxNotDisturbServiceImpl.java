package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNotDisturb;
import cn.stylefeng.guns.modular.note.mapper.QxNotDisturbMapper;
import cn.stylefeng.guns.modular.note.model.params.QxNotDisturbParam;
import cn.stylefeng.guns.modular.note.model.result.QxNotDisturbResult;
import  cn.stylefeng.guns.modular.note.service.QxNotDisturbService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 消息免打扰表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxNotDisturbServiceImpl extends ServiceImpl<QxNotDisturbMapper, QxNotDisturb> implements QxNotDisturbService {

    @Override
    public void add(QxNotDisturbParam param){
        QxNotDisturb entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxNotDisturbParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxNotDisturbParam param){
        QxNotDisturb oldEntity = getOldEntity(param);
        QxNotDisturb newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxNotDisturbResult findBySpec(QxNotDisturbParam param){
        return null;
    }

    @Override
    public List<QxNotDisturbResult> findListBySpec(QxNotDisturbParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxNotDisturbParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxNotDisturbParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxNotDisturb getOldEntity(QxNotDisturbParam param) {
        return this.getById(getKey(param));
    }

    private QxNotDisturb getEntity(QxNotDisturbParam param) {
        QxNotDisturb entity = new QxNotDisturb();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
