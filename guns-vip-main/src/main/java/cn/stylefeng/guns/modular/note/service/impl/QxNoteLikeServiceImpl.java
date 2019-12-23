package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNoteLike;
import cn.stylefeng.guns.modular.note.mapper.QxNoteLikeMapper;
import cn.stylefeng.guns.modular.note.model.params.QxNoteLikeParam;
import cn.stylefeng.guns.modular.note.model.result.QxNoteLikeResult;
import  cn.stylefeng.guns.modular.note.service.QxNoteLikeService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2019-12-23
 */
@Service
public class QxNoteLikeServiceImpl extends ServiceImpl<QxNoteLikeMapper, QxNoteLike> implements QxNoteLikeService {

    @Override
    public void add(QxNoteLikeParam param){
        QxNoteLike entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxNoteLikeParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxNoteLikeParam param){
        QxNoteLike oldEntity = getOldEntity(param);
        QxNoteLike newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxNoteLikeResult findBySpec(QxNoteLikeParam param){
        return null;
    }

    @Override
    public List<QxNoteLikeResult> findListBySpec(QxNoteLikeParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxNoteLikeParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxNoteLikeParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxNoteLike getOldEntity(QxNoteLikeParam param) {
        return this.getById(getKey(param));
    }

    private QxNoteLike getEntity(QxNoteLikeParam param) {
        QxNoteLike entity = new QxNoteLike();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
