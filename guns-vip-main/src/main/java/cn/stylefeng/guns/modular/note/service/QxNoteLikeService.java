package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNoteLike;
import cn.stylefeng.guns.modular.note.model.params.QxNoteLikeParam;
import cn.stylefeng.guns.modular.note.model.result.QxNoteLikeResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2019-12-23
 */
public interface QxNoteLikeService extends IService<QxNoteLike> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-12-23
     */
    void add(QxNoteLikeParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-12-23
     */
    void delete(QxNoteLikeParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-12-23
     */
    void update(QxNoteLikeParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
    QxNoteLikeResult findBySpec(QxNoteLikeParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
    List<QxNoteLikeResult> findListBySpec(QxNoteLikeParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
     LayuiPageInfo findPageBySpec(QxNoteLikeParam param);

}
