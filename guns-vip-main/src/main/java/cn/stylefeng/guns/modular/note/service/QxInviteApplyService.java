package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxInviteApply;
import cn.stylefeng.guns.modular.note.model.params.QxInviteApplyParam;
import cn.stylefeng.guns.modular.note.model.result.QxInviteApplyResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 约单报名 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxInviteApplyService extends IService<QxInviteApply> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxInviteApplyParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxInviteApplyParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxInviteApplyParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxInviteApplyResult findBySpec(QxInviteApplyParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxInviteApplyResult> findListBySpec(QxInviteApplyParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxInviteApplyParam param);

}
