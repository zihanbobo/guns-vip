package cn.stylefeng.guns.modular.note.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.modular.note.entity.QxInvite;
import cn.stylefeng.guns.modular.note.model.params.QxInviteParam;
import cn.stylefeng.guns.modular.note.model.result.QxInviteResult;
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

    /**
     * 更新约单状态
     * @param inviteId
     * @param matched
     */
	void updateStatus(@Param("inviteId") Long inviteId, @Param("status") String status);

}
