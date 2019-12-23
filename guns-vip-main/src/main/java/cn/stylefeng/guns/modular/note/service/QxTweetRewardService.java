package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxTweetReward;
import cn.stylefeng.guns.modular.note.model.params.QxTweetRewardParam;
import cn.stylefeng.guns.modular.note.model.result.QxTweetRewardResult;
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
public interface QxTweetRewardService extends IService<QxTweetReward> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-12-23
     */
    void add(QxTweetRewardParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-12-23
     */
    void delete(QxTweetRewardParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-12-23
     */
    void update(QxTweetRewardParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
    QxTweetRewardResult findBySpec(QxTweetRewardParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
    List<QxTweetRewardResult> findListBySpec(QxTweetRewardParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
     LayuiPageInfo findPageBySpec(QxTweetRewardParam param);

}
