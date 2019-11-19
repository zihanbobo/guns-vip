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
 * @since 2019-11-19
 */
public interface QxInviteApplyService extends IService<QxInviteApply> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-19
     */
    void add(QxInviteApplyParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-19
     */
    void delete(QxInviteApplyParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-19
     */
    void update(QxInviteApplyParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-19
     */
    QxInviteApplyResult findBySpec(QxInviteApplyParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-19
     */
    List<QxInviteApplyResult> findListBySpec(QxInviteApplyParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-19
     */
     LayuiPageInfo findPageBySpec(QxInviteApplyParam param);

}
