package cn.stylefeng.guns.modular.note.mapper;

import cn.stylefeng.guns.modular.note.entity.QxDateType;
import cn.stylefeng.guns.modular.note.model.params.QxDateTypeParam;
import cn.stylefeng.guns.modular.note.model.result.QxDateTypeResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-11-19
 */
public interface QxDateTypeMapper extends BaseMapper<QxDateType> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-11-19
     */
    List<QxDateTypeResult> customList(@Param("paramCondition") QxDateTypeParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-11-19
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxDateTypeParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-11-19
     */
    Page<QxDateTypeResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxDateTypeParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-11-19
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxDateTypeParam paramCondition);

}
