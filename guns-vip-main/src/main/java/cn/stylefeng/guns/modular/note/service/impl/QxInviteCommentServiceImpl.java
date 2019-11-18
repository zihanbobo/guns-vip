package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxInviteComment;
import cn.stylefeng.guns.modular.note.mapper.QxInviteCommentMapper;
import cn.stylefeng.guns.modular.note.model.params.QxInviteCommentParam;
import cn.stylefeng.guns.modular.note.model.result.QxInviteCommentResult;
import  cn.stylefeng.guns.modular.note.service.QxInviteCommentService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 约单评价表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxInviteCommentServiceImpl extends ServiceImpl<QxInviteCommentMapper, QxInviteComment> implements QxInviteCommentService {

    @Override
    public void add(QxInviteCommentParam param){
        QxInviteComment entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxInviteCommentParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxInviteCommentParam param){
        QxInviteComment oldEntity = getOldEntity(param);
        QxInviteComment newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxInviteCommentResult findBySpec(QxInviteCommentParam param){
        return null;
    }

    @Override
    public List<QxInviteCommentResult> findListBySpec(QxInviteCommentParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxInviteCommentParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxInviteCommentParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxInviteComment getOldEntity(QxInviteCommentParam param) {
        return this.getById(getKey(param));
    }

    private QxInviteComment getEntity(QxInviteCommentParam param) {
        QxInviteComment entity = new QxInviteComment();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
