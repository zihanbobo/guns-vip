package cn.stylefeng.guns.modular.note.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 系统通知表
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@TableName("qx_notice")
public class QxNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 乐观锁
     */
    @TableField("version")
    private Integer version;

    /**
     * 创建人
     */
    @TableField("created_by")
    private Long createdBy;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;

    /**
     * 更新人
     */
    @TableField("updated_by")
    private Long updatedBy;

    /**
     * 更新时间
     */
    @TableField("updated_time")
    private Date updatedTime;

    /**
     * 删除标识
     */
    @TableField("deleted")
    private Boolean deleted;

    /**
     * 用户账号
     */
    @TableField("account")
    private String account;

    /**
     * 通知内容
     */
    @TableField("content")
    private String content;

    /**
     * 消息分类 消息分类
     */
    @TableField("tag")
    private Integer tag;

    /**
     * 通知类型 通知类型：0-短信，1-邮箱， 2-推送
     */
    @TableField("type")
    private Integer type;

    /**
     * 发送状态 是否发送：0-未发送，1-已发送
     */
    @TableField("status_send")
    private Integer statusSend;

    /**
     * 已读状态 读取状态：1-是.0-未读
     */
    @TableField("status_read")
    private Integer statusRead;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatusSend() {
        return statusSend;
    }

    public void setStatusSend(Integer statusSend) {
        this.statusSend = statusSend;
    }

    public Integer getStatusRead() {
        return statusRead;
    }

    public void setStatusRead(Integer statusRead) {
        this.statusRead = statusRead;
    }

    @Override
    public String toString() {
        return "QxNotice{" +
        "id=" + id +
        ", version=" + version +
        ", createdBy=" + createdBy +
        ", createdTime=" + createdTime +
        ", updatedBy=" + updatedBy +
        ", updatedTime=" + updatedTime +
        ", deleted=" + deleted +
        ", account=" + account +
        ", content=" + content +
        ", tag=" + tag +
        ", type=" + type +
        ", statusSend=" + statusSend +
        ", statusRead=" + statusRead +
        "}";
    }
}
