package cn.stylefeng.guns.modular.note.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.modular.note.entity.QxTweet;
import cn.stylefeng.guns.modular.note.model.params.QxTweetParam;
import cn.stylefeng.guns.modular.note.model.result.QxTweetResult;

/**
 * <p>
 * 推文表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxTweetMapper extends BaseMapper<QxTweet> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxTweetResult> customList(@Param("paramCondition") QxTweetParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-11-18
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxTweetParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-11-18
     */
    Page<QxTweetResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxTweetParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-11-18
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxTweetParam paramCondition);

    /**
     * 获取关注人的动态
     * @param page
     * @param userId
     */
    @Select("select a.* from qx_tweet a inner join qx_follow b on a.user_id = b.followee_id and b.follower_id = #{userId}")
    @ResultMap("BaseResultMap")
	Page<List<QxTweet>> followList(@Param("page") Page page, @Param("userId") Long userId);

    /**
     * 获得打赏用户列表
     * @param page
     * @param tweetId
     * @return
     */
	Page rewardUsers(@Param("page") Page page, @Param("tweetId") Long tweetId);

}
