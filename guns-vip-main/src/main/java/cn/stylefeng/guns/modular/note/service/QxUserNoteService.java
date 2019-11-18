package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxUserNote;
import cn.stylefeng.guns.modular.note.model.params.QxUserNoteParam;
import cn.stylefeng.guns.modular.note.model.result.QxUserNoteResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户付费日记关系表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxUserNoteService extends IService<QxUserNote> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxUserNoteParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxUserNoteParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxUserNoteParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxUserNoteResult findBySpec(QxUserNoteParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxUserNoteResult> findListBySpec(QxUserNoteParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxUserNoteParam param);

}
