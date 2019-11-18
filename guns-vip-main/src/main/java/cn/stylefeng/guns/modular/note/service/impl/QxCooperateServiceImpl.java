package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxCooperate;
import cn.stylefeng.guns.modular.note.mapper.QxCooperateMapper;
import cn.stylefeng.guns.modular.note.model.params.QxCooperateParam;
import cn.stylefeng.guns.modular.note.model.result.QxCooperateResult;
import  cn.stylefeng.guns.modular.note.service.QxCooperateService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 商务合作 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxCooperateServiceImpl extends ServiceImpl<QxCooperateMapper, QxCooperate> implements QxCooperateService {

    @Override
    public void add(QxCooperateParam param){
        QxCooperate entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxCooperateParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxCooperateParam param){
        QxCooperate oldEntity = getOldEntity(param);
        QxCooperate newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxCooperateResult findBySpec(QxCooperateParam param){
        return null;
    }

    @Override
    public List<QxCooperateResult> findListBySpec(QxCooperateParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxCooperateParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxCooperateParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxCooperate getOldEntity(QxCooperateParam param) {
        return this.getById(getKey(param));
    }

    private QxCooperate getEntity(QxCooperateParam param) {
        QxCooperate entity = new QxCooperate();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
