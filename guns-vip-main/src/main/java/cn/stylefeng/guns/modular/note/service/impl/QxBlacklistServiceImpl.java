package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxBlacklist;
import cn.stylefeng.guns.modular.note.mapper.QxBlacklistMapper;
import cn.stylefeng.guns.modular.note.model.params.QxBlacklistParam;
import cn.stylefeng.guns.modular.note.model.result.QxBlacklistResult;
import  cn.stylefeng.guns.modular.note.service.QxBlacklistService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 黑名单表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxBlacklistServiceImpl extends ServiceImpl<QxBlacklistMapper, QxBlacklist> implements QxBlacklistService {

    @Override
    public void add(QxBlacklistParam param){
        QxBlacklist entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxBlacklistParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxBlacklistParam param){
        QxBlacklist oldEntity = getOldEntity(param);
        QxBlacklist newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxBlacklistResult findBySpec(QxBlacklistParam param){
        return null;
    }

    @Override
    public List<QxBlacklistResult> findListBySpec(QxBlacklistParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxBlacklistParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxBlacklistParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxBlacklist getOldEntity(QxBlacklistParam param) {
        return this.getById(getKey(param));
    }

    private QxBlacklist getEntity(QxBlacklistParam param) {
        QxBlacklist entity = new QxBlacklist();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
