package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxComplaint;
import cn.stylefeng.guns.modular.note.mapper.QxComplaintMapper;
import cn.stylefeng.guns.modular.note.model.params.QxComplaintParam;
import cn.stylefeng.guns.modular.note.model.result.QxComplaintResult;
import  cn.stylefeng.guns.modular.note.service.QxComplaintService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 约单投诉表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-14
 */
@Service
public class QxComplaintServiceImpl extends ServiceImpl<QxComplaintMapper, QxComplaint> implements QxComplaintService {

    @Override
    public void add(QxComplaintParam param){
        QxComplaint entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxComplaintParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxComplaintParam param){
        QxComplaint oldEntity = getOldEntity(param);
        QxComplaint newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxComplaintResult findBySpec(QxComplaintParam param){
        return null;
    }

    @Override
    public List<QxComplaintResult> findListBySpec(QxComplaintParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxComplaintParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxComplaintParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxComplaint getOldEntity(QxComplaintParam param) {
        return this.getById(getKey(param));
    }

    private QxComplaint getEntity(QxComplaintParam param) {
        QxComplaint entity = new QxComplaint();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
