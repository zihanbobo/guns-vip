package cn.stylefeng.guns.modular.note.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 系统通知表
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Data
public class QxNoticeResult implements Serializable {

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
     * 用户账号
     */
    private String account;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 消息分类 消息分类
     */
    private Integer tag;

    /**
     * 通知类型 通知类型：0-短信，1-邮箱， 2-推送
     */
    private Integer type;

    /**
     * 发送状态 是否发送：0-未发送，1-已发送
     */
    private Integer statusSend;

    /**
     * 已读状态 读取状态：1-是.0-未读
     */
    private Integer statusRead;

}
