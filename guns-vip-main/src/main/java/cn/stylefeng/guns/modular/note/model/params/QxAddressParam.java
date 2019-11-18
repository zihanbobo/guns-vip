package cn.stylefeng.guns.modular.note.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 收货地址表
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Data
public class QxAddressParam implements Serializable, BaseValidatingParam {

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
     * 收货人
     */
    private String name;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 所在地区
     */
    private String area;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 是否默认
     */
    private Boolean isDefault;

    @Override
    public String checkParam() {
        return null;
    }

}
