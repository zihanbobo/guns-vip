package cn.stylefeng.guns.modular.note.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 平台提现表
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Data
public class QxWithdrawLogParam implements Serializable, BaseValidatingParam {

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
     * 用户ID
     */
    private Long userId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 提现方式 0-支付宝；1-微信
     */
    private String payWay;

    /**
     * 收款账号 支付宝账号；微信uuid;
     */
    private String payeeAccount;

    /**
     * 状态 0-已申请；1-已提现;2-已取消
     */
    private String status;
    
    /**
     * 订单号
     */
    private String sn;

    @Override
    public String checkParam() {
        return null;
    }

}
