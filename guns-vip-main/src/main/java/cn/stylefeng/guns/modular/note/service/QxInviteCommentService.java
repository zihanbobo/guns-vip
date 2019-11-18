package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxInviteComment;
import cn.stylefeng.guns.modular.note.model.params.QxInviteCommentParam;
import cn.stylefeng.guns.modular.note.model.result.QxInviteCommentResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 约单评价表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxInviteCommentService extends IService<QxInviteComment> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxInviteCommentParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxInviteCommentParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxInviteCommentParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxInviteCommentResult findBySpec(QxInviteCommentParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxInviteCommentResult> findListBySpec(QxInviteCommentParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxInviteCommentParam param);

}
