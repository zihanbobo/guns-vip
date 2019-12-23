package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxTweetLike;
import cn.stylefeng.guns.modular.note.mapper.QxTweetLikeMapper;
import cn.stylefeng.guns.modular.note.model.params.QxTweetLikeParam;
import cn.stylefeng.guns.modular.note.model.result.QxTweetLikeResult;
import  cn.stylefeng.guns.modular.note.service.QxTweetLikeService;
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
public class QxTweetLikeServiceImpl extends ServiceImpl<QxTweetLikeMapper, QxTweetLike> implements QxTweetLikeService {

    @Override
    public void add(QxTweetLikeParam param){
        QxTweetLike entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxTweetLikeParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxTweetLikeParam param){
        QxTweetLike oldEntity = getOldEntity(param);
        QxTweetLike newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxTweetLikeResult findBySpec(QxTweetLikeParam param){
        return null;
    }

    @Override
    public List<QxTweetLikeResult> findListBySpec(QxTweetLikeParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxTweetLikeParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxTweetLikeParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxTweetLike getOldEntity(QxTweetLikeParam param) {
        return this.getById(getKey(param));
    }

    private QxTweetLike getEntity(QxTweetLikeParam param) {
        QxTweetLike entity = new QxTweetLike();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
