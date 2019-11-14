package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNoteComment;
import cn.stylefeng.guns.modular.note.mapper.QxNoteCommentMapper;
import cn.stylefeng.guns.modular.note.model.params.QxNoteCommentParam;
import cn.stylefeng.guns.modular.note.model.result.QxNoteCommentResult;
import  cn.stylefeng.guns.modular.note.service.QxNoteCommentService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 私密日记评论表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-14
 */
@Service
public class QxNoteCommentServiceImpl extends ServiceImpl<QxNoteCommentMapper, QxNoteComment> implements QxNoteCommentService {

    @Override
    public void add(QxNoteCommentParam param){
        QxNoteComment entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxNoteCommentParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxNoteCommentParam param){
        QxNoteComment oldEntity = getOldEntity(param);
        QxNoteComment newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxNoteCommentResult findBySpec(QxNoteCommentParam param){
        return null;
    }

    @Override
    public List<QxNoteCommentResult> findListBySpec(QxNoteCommentParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxNoteCommentParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxNoteCommentParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxNoteComment getOldEntity(QxNoteCommentParam param) {
        return this.getById(getKey(param));
    }

    private QxNoteComment getEntity(QxNoteCommentParam param) {
        QxNoteComment entity = new QxNoteComment();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
