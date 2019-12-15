package cn.stylefeng.guns.modular.note.mapper;

import cn.stylefeng.guns.modular.note.entity.QxProduct;
import cn.stylefeng.guns.modular.note.model.params.QxProductParam;
import cn.stylefeng.guns.modular.note.model.result.QxProductResult;
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
 * 商品表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxProductMapper extends BaseMapper<QxProduct> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxProductResult> customList(@Param("paramCondition") QxProductParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-11-18
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxProductParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-11-18
     */
    Page<QxProductResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxProductParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-11-18
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxProductParam paramCondition);


    /**
     * 我的兑换
     * @param page
     * @param requestUserId
     */
    @Select("select a.* from qx_product a inner join qx_user_product b on b.user_id = ${userId} and a.id = b.product_id order by a.created_time")
    @ResultMap("BaseResultMap")
	Page myExchange(@Param("page") Page page, @Param("userId") Long userId);
}
