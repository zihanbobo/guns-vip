package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNoteReward;
import cn.stylefeng.guns.modular.note.mapper.QxNoteRewardMapper;
import cn.stylefeng.guns.modular.note.model.params.QxNoteRewardParam;
import cn.stylefeng.guns.modular.note.model.result.QxNoteRewardResult;
import  cn.stylefeng.guns.modular.note.service.QxNoteRewardService;
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
public class QxNoteRewardServiceImpl extends ServiceImpl<QxNoteRewardMapper, QxNoteReward> implements QxNoteRewardService {

    @Override
    public void add(QxNoteRewardParam param){
        QxNoteReward entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxNoteRewardParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxNoteRewardParam param){
        QxNoteReward oldEntity = getOldEntity(param);
        QxNoteReward newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxNoteRewardResult findBySpec(QxNoteRewardParam param){
        return null;
    }

    @Override
    public List<QxNoteRewardResult> findListBySpec(QxNoteRewardParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxNoteRewardParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxNoteRewardParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxNoteReward getOldEntity(QxNoteRewardParam param) {
        return this.getById(getKey(param));
    }

    private QxNoteReward getEntity(QxNoteRewardParam param) {
        QxNoteReward entity = new QxNoteReward();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
