package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxPayLog;
import cn.stylefeng.guns.modular.note.mapper.QxPayLogMapper;
import cn.stylefeng.guns.modular.note.model.params.QxPayLogParam;
import cn.stylefeng.guns.modular.note.model.result.QxPayLogResult;
import  cn.stylefeng.guns.modular.note.service.QxPayLogService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户支付流水表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-14
 */
@Service
public class QxPayLogServiceImpl extends ServiceImpl<QxPayLogMapper, QxPayLog> implements QxPayLogService {

    @Override
    public void add(QxPayLogParam param){
        QxPayLog entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxPayLogParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxPayLogParam param){
        QxPayLog oldEntity = getOldEntity(param);
        QxPayLog newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxPayLogResult findBySpec(QxPayLogParam param){
        return null;
    }

    @Override
    public List<QxPayLogResult> findListBySpec(QxPayLogParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxPayLogParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxPayLogParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxPayLog getOldEntity(QxPayLogParam param) {
        return this.getById(getKey(param));
    }

    private QxPayLog getEntity(QxPayLogParam param) {
        QxPayLog entity = new QxPayLog();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
