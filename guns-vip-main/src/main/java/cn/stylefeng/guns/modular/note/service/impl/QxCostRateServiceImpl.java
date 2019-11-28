package cn.stylefeng.guns.modular.note.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxCostRate;
import cn.stylefeng.guns.modular.note.mapper.QxCostRateMapper;
import cn.stylefeng.guns.modular.note.model.params.QxCostRateParam;
import cn.stylefeng.guns.modular.note.model.result.QxCostRateResult;
import  cn.stylefeng.guns.modular.note.service.QxCostRateService;
import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 费用比例表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-28
 */
@Service
public class QxCostRateServiceImpl extends ServiceImpl<QxCostRateMapper, QxCostRate> implements QxCostRateService {

    @Override
    public void add(QxCostRateParam param){
        QxCostRate entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxCostRateParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxCostRateParam param){
        QxCostRate oldEntity = getOldEntity(param);
        QxCostRate newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxCostRateResult findBySpec(QxCostRateParam param){
        return null;
    }

    @Override
    public List<QxCostRateResult> findListBySpec(QxCostRateParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxCostRateParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxCostRateParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxCostRate getOldEntity(QxCostRateParam param) {
        return this.getById(getKey(param));
    }

    private QxCostRate getEntity(QxCostRateParam param) {
        QxCostRate entity = new QxCostRate();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

	@Override
	public BigDecimal getRateByType(String type) {
		QueryWrapper<QxCostRate> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("type", type);
		QxCostRate costRate = this.getOne(queryWrapper);
		return costRate.getRate();
	}

}
