package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxBlack;
import cn.stylefeng.guns.modular.note.mapper.QxBlackMapper;
import cn.stylefeng.guns.modular.note.model.params.QxBlackParam;
import cn.stylefeng.guns.modular.note.model.result.QxBlackResult;
import  cn.stylefeng.guns.modular.note.service.QxBlackService;
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
public class QxBlackServiceImpl extends ServiceImpl<QxBlackMapper, QxBlack> implements QxBlackService {

    @Override
    public void add(QxBlackParam param){
        QxBlack entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxBlackParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxBlackParam param){
        QxBlack oldEntity = getOldEntity(param);
        QxBlack newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxBlackResult findBySpec(QxBlackParam param){
        return null;
    }

    @Override
    public List<QxBlackResult> findListBySpec(QxBlackParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxBlackParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxBlackParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxBlack getOldEntity(QxBlackParam param) {
        return this.getById(getKey(param));
    }

    private QxBlack getEntity(QxBlackParam param) {
        QxBlack entity = new QxBlack();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
