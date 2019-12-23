package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxTweetReward;
import cn.stylefeng.guns.modular.note.mapper.QxTweetRewardMapper;
import cn.stylefeng.guns.modular.note.model.params.QxTweetRewardParam;
import cn.stylefeng.guns.modular.note.model.result.QxTweetRewardResult;
import  cn.stylefeng.guns.modular.note.service.QxTweetRewardService;
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
public class QxTweetRewardServiceImpl extends ServiceImpl<QxTweetRewardMapper, QxTweetReward> implements QxTweetRewardService {

    @Override
    public void add(QxTweetRewardParam param){
        QxTweetReward entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxTweetRewardParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxTweetRewardParam param){
        QxTweetReward oldEntity = getOldEntity(param);
        QxTweetReward newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxTweetRewardResult findBySpec(QxTweetRewardParam param){
        return null;
    }

    @Override
    public List<QxTweetRewardResult> findListBySpec(QxTweetRewardParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxTweetRewardParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxTweetRewardParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxTweetReward getOldEntity(QxTweetRewardParam param) {
        return this.getById(getKey(param));
    }

    private QxTweetReward getEntity(QxTweetRewardParam param) {
        QxTweetReward entity = new QxTweetReward();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
