package cn.stylefeng.guns.modular.note.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 用户金币订单表
 * </p>
 *
 * @author 
 * @since 2019-11-22
 */
@Data
public class QxCoinOrderResult implements Serializable {

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
     * 订单号
     */
    private String sn;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 金币套餐ID
     */
    private Long packageId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 状态 状态：0-待支付；1-已支付;2-已取消
     */
    private String status;

}
