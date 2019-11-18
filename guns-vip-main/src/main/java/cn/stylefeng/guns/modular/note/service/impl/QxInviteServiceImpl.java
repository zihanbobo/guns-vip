package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.dto.QxInviteTo;
import cn.stylefeng.guns.modular.note.entity.QxInvite;
import cn.stylefeng.guns.modular.note.mapper.QxInviteMapper;
import cn.stylefeng.guns.modular.note.model.params.QxInviteParam;
import cn.stylefeng.guns.modular.note.model.result.QxInviteResult;
import  cn.stylefeng.guns.modular.note.service.QxInviteService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 约单表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxInviteServiceImpl extends ServiceImpl<QxInviteMapper, QxInvite> implements QxInviteService {

    @Override
    public void add(QxInviteParam param){
        QxInvite entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxInviteParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxInviteParam param){
        QxInvite oldEntity = getOldEntity(param);
        QxInvite newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxInviteResult findBySpec(QxInviteParam param){
        return null;
    }

    @Override
    public List<QxInviteResult> findListBySpec(QxInviteParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxInviteParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxInviteParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxInvite getOldEntity(QxInviteParam param) {
        return this.getById(getKey(param));
    }

    private QxInvite getEntity(QxInviteParam param) {
        QxInvite entity = new QxInvite();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

	@Override
	public void addInvite(Long requestUserId, QxInviteTo inviteTo) {
		// TODO Auto-generated method stub
		
	}

}
