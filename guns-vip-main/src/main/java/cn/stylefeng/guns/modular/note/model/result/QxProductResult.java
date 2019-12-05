package cn.stylefeng.guns.modular.note.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Data
public class QxProductResult implements Serializable {

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
     * 品类ID
     */
    private Long categoryId;
    
    /**
     * 品类名称
     */
    private String categoryName;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 轮播图片列表
     */
    private String headImages;

    /**
     * 详情图片列表
     */
    private String detailImages;

    /**
     * 兑换金币价格
     */
    private Integer price;

    /**
     * 库存数量
     */
    private Integer stock;

}
