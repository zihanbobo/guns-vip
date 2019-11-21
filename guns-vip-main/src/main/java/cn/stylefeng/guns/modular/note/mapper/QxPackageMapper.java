package cn.stylefeng.guns.modular.note.mapper;

import cn.stylefeng.guns.modular.note.entity.QxPackage;
import cn.stylefeng.guns.modular.note.model.params.QxPackageParam;
import cn.stylefeng.guns.modular.note.model.result.QxPackageResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 充值套餐 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-11-21
 */
public interface QxPackageMapper extends BaseMapper<QxPackage> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-11-21
     */
    List<QxPackageResult> customList(@Param("paramCondition") QxPackageParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-11-21
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxPackageParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-11-21
     */
    Page<QxPackageResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxPackageParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-11-21
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxPackageParam paramCondition);

}
