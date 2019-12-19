package cn.stylefeng.guns.modular.note.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.modular.note.entity.QxInvite;
import cn.stylefeng.guns.modular.note.model.params.QxInviteParam;
import cn.stylefeng.guns.modular.note.model.result.QxInviteResult;
import cn.stylefeng.guns.modular.note.pojo.QxInviteSearchPojo;
import cn.stylefeng.guns.modular.note.pojo.QxInviteUserPojo;

/**
 * <p>
 * 约单表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxInviteMapper extends BaseMapper<QxInvite> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxInviteResult> customList(@Param("paramCondition") QxInviteParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-11-18
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxInviteParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-11-18
     */
    Page<QxInviteResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxInviteParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-11-18
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxInviteParam paramCondition);
    
    /**
     * 获取约单所有用户信息
     * @param inviteId
     * @return
     */
    List<QxInviteUserPojo> getInviteUsers(@Param("inviteId") Long inviteId);

    @Select("SELECT a.* FROM qx_invite a WHERE a.status = 2 and (a.inviter = #{userId} or a.invitee = #{userId}) order by created_time desc")
    @ResultMap("BaseResultMap")
	List<QxInvite> getCurrentInvites(@Param("page") Page page, @Param("userId") Long userId);

    /**
     * 根据条件搜索附近约单
     */
	Page<List<QxInviteSearchPojo>> search(@Param("page") Page page, @Param("paramCondition") QxInviteParam paramCondition);

	/**
	 * 获取约单详情
	 * @param id
	 * @return
	 */
	QxInviteSearchPojo getInviteById(@Param("inviteId") Long id);
}
