package cn.stylefeng.guns.modular.note.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.stylefeng.guns.modular.note.entity.QxWithdrawLog;
import cn.stylefeng.guns.modular.note.model.params.QxWithdrawLogParam;
import cn.stylefeng.guns.modular.note.model.result.QxWithdrawLogResult;

/**
 * <p>
 * 平台提现表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxWithdrawLogMapper extends BaseMapper<QxWithdrawLog> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxWithdrawLogResult> customList(@Param("paramCondition") QxWithdrawLogParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-11-18
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxWithdrawLogParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-11-18
     */
    Page<QxWithdrawLogResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxWithdrawLogParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-11-18
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxWithdrawLogParam paramCondition);

    /**
     * 获取总提现
     * @return
     */
    @Select("<script>" +
    		"select IFNULL(sum(amount), 0) as amount from qx_withdraw_log where status = 1" +
    		"<if test='startDate != null'>" +
    		" and created_time &gt;= #{startDate}"+
    		"</if>" +
    		"<if test='endDate != null'>" +
    		" and created_time &lt;= #{endDate}" +
    		"</if>" +
    		"</script>")
    @ResultType(BigDecimal.class)
	BigDecimal getTotalWithdraw(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
