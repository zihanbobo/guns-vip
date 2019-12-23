package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxTweetLike;
import cn.stylefeng.guns.modular.note.model.params.QxTweetLikeParam;
import cn.stylefeng.guns.modular.note.model.result.QxTweetLikeResult;
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
public interface QxTweetLikeService extends IService<QxTweetLike> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-12-23
     */
    void add(QxTweetLikeParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-12-23
     */
    void delete(QxTweetLikeParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-12-23
     */
    void update(QxTweetLikeParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
    QxTweetLikeResult findBySpec(QxTweetLikeParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
    List<QxTweetLikeResult> findListBySpec(QxTweetLikeParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
     LayuiPageInfo findPageBySpec(QxTweetLikeParam param);

}
