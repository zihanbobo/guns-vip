package cn.stylefeng.guns.modular.note.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.dto.QxAddressTo;
import cn.stylefeng.guns.modular.note.entity.QxAddress;
import cn.stylefeng.guns.modular.note.mapper.QxAddressMapper;
import cn.stylefeng.guns.modular.note.model.params.QxAddressParam;
import cn.stylefeng.guns.modular.note.model.result.QxAddressResult;
import  cn.stylefeng.guns.modular.note.service.QxAddressService;
import cn.stylefeng.roses.core.util.ToolUtil;

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

	@Override
	public void updateAddress(Long userId, QxAddressTo addressTo) {
		if (Boolean.TRUE.equals(addressTo.getIsDefault())) {
			unsetDefaultAddress(userId);
		}
		QxAddress address = new QxAddress();
		BeanUtils.copyProperties(addressTo, address);
		this.baseMapper.updateById(address);
	}
	
	public void unsetDefaultAddress(Long userId) {
		UpdateWrapper<QxAddress> unsetWrapper = new UpdateWrapper<>();
		unsetWrapper.eq("user_id", userId).set("is_default", false);
		this.baseMapper.update(null, unsetWrapper);
	}

	@Override
	public void deleteAddress(Long id) {
		UpdateWrapper<QxAddress> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", id).set("deleted", true);
		this.baseMapper.update(null, updateWrapper);
	}

	@Override
	public void addAddress(Long userId, QxAddressTo addressTo) {
		if (Boolean.TRUE.equals(addressTo.getIsDefault())) {
			unsetDefaultAddress(userId);
		}
		QxAddress model = new QxAddress();
		BeanUtils.copyProperties(addressTo, model);
		model.setUserId(userId);
		this.baseMapper.insert(model);
	}

}
