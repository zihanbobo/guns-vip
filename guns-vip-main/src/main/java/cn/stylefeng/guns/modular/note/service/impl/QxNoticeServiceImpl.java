package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNotice;
import cn.stylefeng.guns.modular.note.mapper.QxNoticeMapper;
import cn.stylefeng.guns.modular.note.model.params.QxNoticeParam;
import cn.stylefeng.guns.modular.note.model.result.QxNoticeResult;
import  cn.stylefeng.guns.modular.note.service.QxNoticeService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统通知表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxNoticeServiceImpl extends ServiceImpl<QxNoticeMapper, QxNotice> implements QxNoticeService {

    @Override
    public void add(QxNoticeParam param){
        QxNotice entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxNoticeParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxNoticeParam param){
        QxNotice oldEntity = getOldEntity(param);
        QxNotice newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxNoticeResult findBySpec(QxNoticeParam param){
        return null;
    }

    @Override
    public List<QxNoticeResult> findListBySpec(QxNoticeParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxNoticeParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxNoticeParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxNotice getOldEntity(QxNoticeParam param) {
        return this.getById(getKey(param));
    }

    private QxNotice getEntity(QxNoticeParam param) {
        QxNotice entity = new QxNotice();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
