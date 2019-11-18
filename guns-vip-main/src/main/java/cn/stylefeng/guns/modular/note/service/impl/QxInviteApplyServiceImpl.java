package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxInviteApply;
import cn.stylefeng.guns.modular.note.mapper.QxInviteApplyMapper;
import cn.stylefeng.guns.modular.note.model.params.QxInviteApplyParam;
import cn.stylefeng.guns.modular.note.model.result.QxInviteApplyResult;
import  cn.stylefeng.guns.modular.note.service.QxInviteApplyService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 约单报名 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxInviteApplyServiceImpl extends ServiceImpl<QxInviteApplyMapper, QxInviteApply> implements QxInviteApplyService {

    @Override
    public void add(QxInviteApplyParam param){
        QxInviteApply entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxInviteApplyParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxInviteApplyParam param){
        QxInviteApply oldEntity = getOldEntity(param);
        QxInviteApply newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxInviteApplyResult findBySpec(QxInviteApplyParam param){
        return null;
    }

    @Override
    public List<QxInviteApplyResult> findListBySpec(QxInviteApplyParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxInviteApplyParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxInviteApplyParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxInviteApply getOldEntity(QxInviteApplyParam param) {
        return this.getById(getKey(param));
    }

    private QxInviteApply getEntity(QxInviteApplyParam param) {
        QxInviteApply entity = new QxInviteApply();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
