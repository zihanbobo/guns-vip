package cn.stylefeng.guns.modular.note.mapper;

import cn.stylefeng.guns.modular.note.entity.QxBlock;
import cn.stylefeng.guns.modular.note.model.params.QxBlockParam;
import cn.stylefeng.guns.modular.note.model.result.QxBlockResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 消息免打扰表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-11-20
 */
public interface QxBlockMapper extends BaseMapper<QxBlock> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-11-20
     */
    List<QxBlockResult> customList(@Param("paramCondition") QxBlockParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-11-20
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxBlockParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-11-20
     */
    Page<QxBlockResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxBlockParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-11-20
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxBlockParam paramCondition);

}
