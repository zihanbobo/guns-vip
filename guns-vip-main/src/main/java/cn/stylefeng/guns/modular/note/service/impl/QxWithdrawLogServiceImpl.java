package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxWithdrawLog;
import cn.stylefeng.guns.modular.note.mapper.QxWithdrawLogMapper;
import cn.stylefeng.guns.modular.note.model.params.QxWithdrawLogParam;
import cn.stylefeng.guns.modular.note.model.result.QxWithdrawLogResult;
import  cn.stylefeng.guns.modular.note.service.QxWithdrawLogService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 平台提现表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxWithdrawLogServiceImpl extends ServiceImpl<QxWithdrawLogMapper, QxWithdrawLog> implements QxWithdrawLogService {

    @Override
    public void add(QxWithdrawLogParam param){
        QxWithdrawLog entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxWithdrawLogParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxWithdrawLogParam param){
        QxWithdrawLog oldEntity = getOldEntity(param);
        QxWithdrawLog newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxWithdrawLogResult findBySpec(QxWithdrawLogParam param){
        return null;
    }

    @Override
    public List<QxWithdrawLogResult> findListBySpec(QxWithdrawLogParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxWithdrawLogParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxWithdrawLogParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxWithdrawLog getOldEntity(QxWithdrawLogParam param) {
        return this.getById(getKey(param));
    }

    private QxWithdrawLog getEntity(QxWithdrawLogParam param) {
        QxWithdrawLog entity = new QxWithdrawLog();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
