package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxUserSocial;
import cn.stylefeng.guns.modular.note.model.params.QxUserSocialParam;
import cn.stylefeng.guns.modular.note.model.result.QxUserSocialResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户社交账号表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-14
 */
public interface QxUserSocialService extends IService<QxUserSocial> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-14
     */
    void add(QxUserSocialParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-14
     */
    void delete(QxUserSocialParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-14
     */
    void update(QxUserSocialParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-14
     */
    QxUserSocialResult findBySpec(QxUserSocialParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-14
     */
    List<QxUserSocialResult> findListBySpec(QxUserSocialParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-14
     */
     LayuiPageInfo findPageBySpec(QxUserSocialParam param);

}
