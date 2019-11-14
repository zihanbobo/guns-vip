package cn.stylefeng.guns.modular.note.mapper;

import cn.stylefeng.guns.modular.note.entity.QxUserSocial;
import cn.stylefeng.guns.modular.note.model.params.QxUserSocialParam;
import cn.stylefeng.guns.modular.note.model.result.QxUserSocialResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户社交账号表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-11-14
 */
public interface QxUserSocialMapper extends BaseMapper<QxUserSocial> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-11-14
     */
    List<QxUserSocialResult> customList(@Param("paramCondition") QxUserSocialParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-11-14
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxUserSocialParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-11-14
     */
    Page<QxUserSocialResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxUserSocialParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-11-14
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxUserSocialParam paramCondition);

}
