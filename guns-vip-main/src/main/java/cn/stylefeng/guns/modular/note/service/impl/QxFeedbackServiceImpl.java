package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxFeedback;
import cn.stylefeng.guns.modular.note.mapper.QxFeedbackMapper;
import cn.stylefeng.guns.modular.note.model.params.QxFeedbackParam;
import cn.stylefeng.guns.modular.note.model.result.QxFeedbackResult;
import  cn.stylefeng.guns.modular.note.service.QxFeedbackService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 建议反馈表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxFeedbackServiceImpl extends ServiceImpl<QxFeedbackMapper, QxFeedback> implements QxFeedbackService {

    @Override
    public void add(QxFeedbackParam param){
        QxFeedback entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxFeedbackParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxFeedbackParam param){
        QxFeedback oldEntity = getOldEntity(param);
        QxFeedback newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxFeedbackResult findBySpec(QxFeedbackParam param){
        return null;
    }

    @Override
    public List<QxFeedbackResult> findListBySpec(QxFeedbackParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxFeedbackParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxFeedbackParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxFeedback getOldEntity(QxFeedbackParam param) {
        return this.getById(getKey(param));
    }

    private QxFeedback getEntity(QxFeedbackParam param) {
        QxFeedback entity = new QxFeedback();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
