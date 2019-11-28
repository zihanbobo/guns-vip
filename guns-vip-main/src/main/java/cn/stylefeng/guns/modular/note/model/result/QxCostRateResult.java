package cn.stylefeng.guns.modular.note.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 费用比例表
 * </p>
 *
 * @author 
 * @since 2019-11-28
 */
@Data
public class QxCostRateResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 标识
     */
    private Long id;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private Long updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 删除标识
     */
    private Boolean deleted;

    /**
     * 比例
     */
    private BigDecimal rate;

    /**
     * 类型 类型：0-金币兑换比例;1-提现手续费
     */
    private String type;

}
