package cn.stylefeng.guns.modular.note.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.modular.note.entity.QxCoinOrder;
import cn.stylefeng.guns.modular.note.model.params.QxCoinOrderParam;
import cn.stylefeng.guns.modular.note.model.result.QxCoinOrderResult;

/**
 * <p>
 * 用户金币订单表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-11-22
 */
public interface QxCoinOrderMapper extends BaseMapper<QxCoinOrder> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-11-22
     */
    List<QxCoinOrderResult> customList(@Param("paramCondition") QxCoinOrderParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-11-22
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxCoinOrderParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-11-22
     */
    Page<QxCoinOrderResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxCoinOrderParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-11-22
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxCoinOrderParam paramCondition);

    @Select("select IFNULL(sum(amount), 0) as totalAmount from qx_coin_order where status = 1")
    @ResultType(BigDecimal.class)
	BigDecimal getTotalCharge();

}
