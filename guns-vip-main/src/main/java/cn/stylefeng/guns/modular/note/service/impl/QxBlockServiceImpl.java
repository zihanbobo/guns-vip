package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxBlock;
import cn.stylefeng.guns.modular.note.mapper.QxBlockMapper;
import cn.stylefeng.guns.modular.note.model.params.QxBlockParam;
import cn.stylefeng.guns.modular.note.model.result.QxBlockResult;
import  cn.stylefeng.guns.modular.note.service.QxBlockService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 消息免打扰表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-20
 */
@Service
public class QxBlockServiceImpl extends ServiceImpl<QxBlockMapper, QxBlock> implements QxBlockService {

    @Override
    public void add(QxBlockParam param){
        QxBlock entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxBlockParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxBlockParam param){
        QxBlock oldEntity = getOldEntity(param);
        QxBlock newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxBlockResult findBySpec(QxBlockParam param){
        return null;
    }

    @Override
    public List<QxBlockResult> findListBySpec(QxBlockParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxBlockParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxBlockParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxBlock getOldEntity(QxBlockParam param) {
        return this.getById(getKey(param));
    }

    private QxBlock getEntity(QxBlockParam param) {
        QxBlock entity = new QxBlock();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
