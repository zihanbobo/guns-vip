package cn.stylefeng.guns.modular.note.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxEmergency;
import cn.stylefeng.guns.modular.note.mapper.QxEmergencyMapper;
import cn.stylefeng.guns.modular.note.model.params.QxEmergencyParam;
import cn.stylefeng.guns.modular.note.model.result.QxEmergencyResult;
import  cn.stylefeng.guns.modular.note.service.QxEmergencyService;
import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 紧急联系人 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxEmergencyServiceImpl extends ServiceImpl<QxEmergencyMapper, QxEmergency> implements QxEmergencyService {

    @Override
    public void add(QxEmergencyParam param){
        QxEmergency entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxEmergencyParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxEmergencyParam param){
        QxEmergency oldEntity = getOldEntity(param);
        QxEmergency newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxEmergencyResult findBySpec(QxEmergencyParam param){
        return null;
    }

    @Override
    public List<QxEmergencyResult> findListBySpec(QxEmergencyParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxEmergencyParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxEmergencyParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxEmergency getOldEntity(QxEmergencyParam param) {
        return this.getById(getKey(param));
    }

    private QxEmergency getEntity(QxEmergencyParam param) {
        QxEmergency entity = new QxEmergency();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

	@Override
	public QxEmergency getDefaultEmergency(Long userId) {
		QueryWrapper<QxEmergency> queryWrapper = new QueryWrapper();
		queryWrapper.eq("user_id", userId).eq("is_default", true);
		return this.baseMapper.selectOne(queryWrapper);
	}

}
