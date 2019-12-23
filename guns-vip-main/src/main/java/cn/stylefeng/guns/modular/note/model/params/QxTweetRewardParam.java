package cn.stylefeng.guns.modular.note.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2019-12-23
 */
@Data
public class QxTweetRewardParam implements Serializable, BaseValidatingParam {

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
     * 推文ID
     */
    private Long tweetId;

    /**
     * 打赏者ID
     */
    private Long userId;

    /**
     * 打赏礼物名称
     */
    private String giftName;

    /**
     * 打赏礼物价格
     */
    private Integer giftPrice;

    /**
     * 礼物图片地址
     */
    private String giftImage;

    @Override
    public String checkParam() {
        return null;
    }

}
