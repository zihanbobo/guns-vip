package cn.stylefeng.guns.modular.note.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 私密日记
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Data
public class QxNoteResult implements Serializable {

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
     * 图片 图片地址1;图片地址2;图片地址3
     */
    private String images;

    /**
     * 推文标题
     */
    private String title;

    /**
     * 推文内容
     */
    private String content;

    /**
     * 是否允许评价
     */
    private Boolean canComment;

    /**
     * 点赞数
     */
    private Integer favoriteCount;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 已赠送礼物数
     */
    private Integer giftCount;

    /**
     * 是否加密
     */
    private Boolean isPrivate;

    /**
     * 打开条件 解锁需付出的礼物
     */
    private Long giftId;

    /**
     * 查看次数 查看次数
     */
    private Integer watchCount;

}
