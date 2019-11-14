package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNoteComment;
import cn.stylefeng.guns.modular.note.model.params.QxNoteCommentParam;
import cn.stylefeng.guns.modular.note.model.result.QxNoteCommentResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 私密日记评论表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-14
 */
public interface QxNoteCommentService extends IService<QxNoteComment> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-14
     */
    void add(QxNoteCommentParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-14
     */
    void delete(QxNoteCommentParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-14
     */
    void update(QxNoteCommentParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-14
     */
    QxNoteCommentResult findBySpec(QxNoteCommentParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-14
     */
    List<QxNoteCommentResult> findListBySpec(QxNoteCommentParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-14
     */
     LayuiPageInfo findPageBySpec(QxNoteCommentParam param);

}
