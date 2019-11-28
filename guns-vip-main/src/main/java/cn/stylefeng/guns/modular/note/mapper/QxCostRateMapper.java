package cn.stylefeng.guns.modular.note.mapper;

import cn.stylefeng.guns.modular.note.entity.QxCostRate;
import cn.stylefeng.guns.modular.note.model.params.QxCostRateParam;
import cn.stylefeng.guns.modular.note.model.result.QxCostRateResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 费用比例表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-11-28
 */
public interface QxCostRateMapper extends BaseMapper<QxCostRate> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-11-28
     */
    List<QxCostRateResult> customList(@Param("paramCondition") QxCostRateParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-11-28
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxCostRateParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-11-28
     */
    Page<QxCostRateResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxCostRateParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-11-28
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxCostRateParam paramCondition);

}
