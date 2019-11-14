package cn.stylefeng.guns.modular.note.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 约单评价表
 * </p>
 *
 * @author 
 * @since 2019-11-14
 */
@Data
public class QxInviteCommentResult implements Serializable {

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
    private String deleted;

    /**
     * 评价者ID
     */
    private Long commenterId;

    /**
     * 被评价者ID
     */
    private Long commenteeId;

    /**
     * 约单ID
     */
    private Long inviteId;

    /**
     * 评价等级 0-不满意；1-一般；2-满意
     */
    private String level;

    /**
     * 评价内容
     */
    private String content;

}
