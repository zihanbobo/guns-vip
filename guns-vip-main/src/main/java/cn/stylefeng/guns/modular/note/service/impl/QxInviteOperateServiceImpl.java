package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxInviteOperate;
import cn.stylefeng.guns.modular.note.mapper.QxInviteOperateMapper;
import cn.stylefeng.guns.modular.note.model.params.QxInviteOperateParam;
import cn.stylefeng.guns.modular.note.model.result.QxInviteOperateResult;
import  cn.stylefeng.guns.modular.note.service.QxInviteOperateService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 约单操作表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxInviteOperateServiceImpl extends ServiceImpl<QxInviteOperateMapper, QxInviteOperate> implements QxInviteOperateService {

    @Override
    public void add(QxInviteOperateParam param){
        QxInviteOperate entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxInviteOperateParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxInviteOperateParam param){
        QxInviteOperate oldEntity = getOldEntity(param);
        QxInviteOperate newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxInviteOperateResult findBySpec(QxInviteOperateParam param){
        return null;
    }

    @Override
    public List<QxInviteOperateResult> findListBySpec(QxInviteOperateParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxInviteOperateParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxInviteOperateParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxInviteOperate getOldEntity(QxInviteOperateParam param) {
        return this.getById(getKey(param));
    }

    private QxInviteOperate getEntity(QxInviteOperateParam param) {
        QxInviteOperate entity = new QxInviteOperate();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
