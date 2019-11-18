package cn.stylefeng.guns.modular.note.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 用户支付流水表
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Data
public class QxPayLogParam implements Serializable, BaseValidatingParam {

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
     * 付费类型 0-约单支出；1-约单汇入；2-打赏支出；3-打赏汇入；4-兑换商品支出；5-购买礼物支出；6-付费日记支出；7-付费日记汇入；8-违约金支出；9-违约金汇入；
     */
    private String type;

    @Override
    public String checkParam() {
        return null;
    }

}
