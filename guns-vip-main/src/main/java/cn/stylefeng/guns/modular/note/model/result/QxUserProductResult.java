package cn.stylefeng.guns.modular.note.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 兑换记录表
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Data
public class QxUserProductResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 标识
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 删除标识
     */
    private Boolean deleted;

    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 用户名称
     */
    private String nickname;
    
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 商品ID
     */
    private Long productId;
    
    /**
     * 商品名称
     */
    private String productName;

    /**
     * 状态 0-未处理；1-已处理
     */
    private String status;

    /**
     * 收货地址
     */
    private Long addressId;
    
    /**
     * 收货人姓名
     */
    private String receiverName;
    
    /**
     * 收货人联系方式
     */
    private String receiverContact;
    
    /**
     * 区域
     */
    private String area;

    /**
     * 地址
     */
    private String address;
}
