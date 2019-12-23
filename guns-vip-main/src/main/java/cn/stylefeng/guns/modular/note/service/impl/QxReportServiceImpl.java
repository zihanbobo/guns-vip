package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxReport;
import cn.stylefeng.guns.modular.note.mapper.QxReportMapper;
import cn.stylefeng.guns.modular.note.model.params.QxReportParam;
import cn.stylefeng.guns.modular.note.model.result.QxReportResult;
import  cn.stylefeng.guns.modular.note.service.QxReportService;
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
public class QxReportServiceImpl extends ServiceImpl<QxReportMapper, QxReport> implements QxReportService {

    @Override
    public void add(QxReportParam param){
        QxReport entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxReportParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxReportParam param){
        QxReport oldEntity = getOldEntity(param);
        QxReport newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxReportResult findBySpec(QxReportParam param){
        return null;
    }

    @Override
    public List<QxReportResult> findListBySpec(QxReportParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxReportParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxReportParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxReport getOldEntity(QxReportParam param) {
        return this.getById(getKey(param));
    }

    private QxReport getEntity(QxReportParam param) {
        QxReport entity = new QxReport();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
