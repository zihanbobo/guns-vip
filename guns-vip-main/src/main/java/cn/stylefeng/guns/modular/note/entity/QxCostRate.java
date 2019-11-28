package cn.stylefeng.guns.modular.note.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 费用比例表
 * </p>
 *
 * @author 
 * @since 2019-11-28
 */
@TableName("qx_cost_rate")
public class QxCostRate implements Serializable {

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
     * 比例
     */
    @TableField("rate")
    private BigDecimal rate;

    /**
     * 类型 类型：0-金币兑换比例;1-提现手续费
     */
    @TableField("type")
    private String type;


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

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "QxCostRate{" +
        "id=" + id +
        ", version=" + version +
        ", createdBy=" + createdBy +
        ", createdTime=" + createdTime +
        ", updatedBy=" + updatedBy +
        ", updatedTime=" + updatedTime +
        ", deleted=" + deleted +
        ", rate=" + rate +
        ", type=" + type +
        "}";
    }
}
