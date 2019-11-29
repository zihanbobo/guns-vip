package cn.stylefeng.guns.modular.note.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxTweet;
import cn.stylefeng.guns.modular.note.model.params.QxTweetParam;
import cn.stylefeng.guns.modular.note.model.result.QxTweetResult;

/**
 * <p>
 * 推文表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxTweetService extends IService<QxTweet> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxTweetParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxTweetParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxTweetParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxTweetResult findBySpec(QxTweetParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxTweetResult> findListBySpec(QxTweetParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxTweetParam param);

     /**
      * 查询我关注的人动态
      * @param page
      * @param requestUserId
      */
     Page<List<QxTweet>> followList(Page page, Long userId);
     

 	/**
 	 * 打赏
 	 * 
 	 * @param requestUserId
 	 * @param userId
 	 * @param tweetId
 	 * @param giftId
 	 */
 	void rewardTweet(Long requestUserId, Long userId, Long tweetId, Long giftId);

}
