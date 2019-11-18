package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxAddress;
import cn.stylefeng.guns.modular.note.mapper.QxAddressMapper;
import cn.stylefeng.guns.modular.note.model.params.QxAddressParam;
import cn.stylefeng.guns.modular.note.model.result.QxAddressResult;
import  cn.stylefeng.guns.modular.note.service.QxAddressService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 收货地址表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxAddressServiceImpl extends ServiceImpl<QxAddressMapper, QxAddress> implements QxAddressService {

    @Override
    public void add(QxAddressParam param){
        QxAddress entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxAddressParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxAddressParam param){
        QxAddress oldEntity = getOldEntity(param);
        QxAddress newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxAddressResult findBySpec(QxAddressParam param){
        return null;
    }

    @Override
    public List<QxAddressResult> findListBySpec(QxAddressParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxAddressParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxAddressParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxAddress getOldEntity(QxAddressParam param) {
        return this.getById(getKey(param));
    }

    private QxAddress getEntity(QxAddressParam param) {
        QxAddress entity = new QxAddress();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
