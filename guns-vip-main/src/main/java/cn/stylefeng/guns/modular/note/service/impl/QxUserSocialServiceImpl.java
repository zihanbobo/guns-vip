package cn.stylefeng.guns.modular.note.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxUserSocial;
import cn.stylefeng.guns.modular.note.mapper.QxUserSocialMapper;
import cn.stylefeng.guns.modular.note.model.params.QxUserSocialParam;
import cn.stylefeng.guns.modular.note.model.result.QxUserSocialResult;
import  cn.stylefeng.guns.modular.note.service.QxUserSocialService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户社交账号表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxUserSocialServiceImpl extends ServiceImpl<QxUserSocialMapper, QxUserSocial> implements QxUserSocialService {

    @Override
    public void add(QxUserSocialParam param){
        QxUserSocial entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxUserSocialParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxUserSocialParam param){
        QxUserSocial oldEntity = getOldEntity(param);
        QxUserSocial newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxUserSocialResult findBySpec(QxUserSocialParam param){
        return null;
    }

    @Override
    public List<QxUserSocialResult> findListBySpec(QxUserSocialParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxUserSocialParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxUserSocialParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxUserSocial getOldEntity(QxUserSocialParam param) {
        return this.getById(getKey(param));
    }

    private QxUserSocial getEntity(QxUserSocialParam param) {
        QxUserSocial entity = new QxUserSocial();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
