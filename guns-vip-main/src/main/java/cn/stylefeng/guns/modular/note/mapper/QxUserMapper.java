package cn.stylefeng.guns.modular.note.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.model.params.QxUserParam;
import cn.stylefeng.guns.modular.note.model.result.QxUserResult;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-11-14
 */
public interface QxUserMapper extends BaseMapper<QxUser> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-11-14
     */
    List<QxUserResult> customList(@Param("paramCondition") QxUserParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-11-14
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxUserParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-11-14
     */
    Page<QxUserResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxUserParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-11-14
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxUserParam paramCondition);

    /**
     * 通过账号获取用户
     */
    QxUser getByAccount(@Param("account") String account);

    /**
     * 获取商品兑换的用户列表
     * @param page
     * @param id
     * @return
     */
    @Select("select a.* from qx_user a inner join qx_user_product b on a.id = b.user_id and b.product_id = #{id}")
    @ResultMap("BaseResultMap")
	List<QxUser> getProductUsers(@Param("page") Page page, @Param("id") Long id);

    /**
     * 根据unionId获取用户
     * @param unionId
     * @return
     */
    @Select("select a.* from qx_user a inner join qx_user_social b on a.id = b.user_id and b.union_id = #{unionId} and b.appId = #{appId}")
    @ResultMap("BaseResultMap")
	QxUser getUserByUnionId(@Param("appId") String appid, @Param("unionId") String unionId);
}
