package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxInviteOperate;
import cn.stylefeng.guns.modular.note.model.params.QxInviteOperateParam;
import cn.stylefeng.guns.modular.note.model.result.QxInviteOperateResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 约单操作表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-14
 */
public interface QxInviteOperateService extends IService<QxInviteOperate> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-14
     */
    void add(QxInviteOperateParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-14
     */
    void delete(QxInviteOperateParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-14
     */
    void update(QxInviteOperateParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-14
     */
    QxInviteOperateResult findBySpec(QxInviteOperateParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-14
     */
    List<QxInviteOperateResult> findListBySpec(QxInviteOperateParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-14
     */
     LayuiPageInfo findPageBySpec(QxInviteOperateParam param);

}
