package cn.stylefeng.guns.modular.note.mapper;

import cn.stylefeng.guns.modular.note.entity.QxNote;
import cn.stylefeng.guns.modular.note.entity.QxTweet;
import cn.stylefeng.guns.modular.note.model.params.QxNoteParam;
import cn.stylefeng.guns.modular.note.model.result.QxNoteResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 私密日记 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxNoteMapper extends BaseMapper<QxNote> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxNoteResult> customList(@Param("paramCondition") QxNoteParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-11-18
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxNoteParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-11-18
     */
    Page<QxNoteResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxNoteParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-11-18
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxNoteParam paramCondition);

    /**
     * 获得打赏用户列表
     * @param page
     * @param noteId
     * @return
     */
	Page rewardUsers(@Param("page") Page page, @Param("noteId") Long noteId);

	/**
	 * 获得推文列表
	 * @param page
	 * @param userId
	 * @param keywords
	 * @return
	 */
	Page listNotes(@Param("page") Page page, @Param("userId") Long userId, @Param("keywords") String keywords);
	
    /**
     * 获取关注人的动态
     * @param page
     * @param userId
     */
    @Select("select a.* from qx_note a inner join qx_follow b on a.user_id = b.followee_id and b.follower_id = #{userId} order by a.created_time desc")
    @ResultMap("BaseResultMap")
	Page<List<QxTweet>> followList(@Param("page") Page page, @Param("userId") Long userId);
}
