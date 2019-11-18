package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxUserNote;
import cn.stylefeng.guns.modular.note.mapper.QxUserNoteMapper;
import cn.stylefeng.guns.modular.note.model.params.QxUserNoteParam;
import cn.stylefeng.guns.modular.note.model.result.QxUserNoteResult;
import  cn.stylefeng.guns.modular.note.service.QxUserNoteService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户付费日记关系表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxUserNoteServiceImpl extends ServiceImpl<QxUserNoteMapper, QxUserNote> implements QxUserNoteService {

    @Override
    public void add(QxUserNoteParam param){
        QxUserNote entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxUserNoteParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxUserNoteParam param){
        QxUserNote oldEntity = getOldEntity(param);
        QxUserNote newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxUserNoteResult findBySpec(QxUserNoteParam param){
        return null;
    }

    @Override
    public List<QxUserNoteResult> findListBySpec(QxUserNoteParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxUserNoteParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxUserNoteParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxUserNote getOldEntity(QxUserNoteParam param) {
        return this.getById(getKey(param));
    }

    private QxUserNote getEntity(QxUserNoteParam param) {
        QxUserNote entity = new QxUserNote();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
