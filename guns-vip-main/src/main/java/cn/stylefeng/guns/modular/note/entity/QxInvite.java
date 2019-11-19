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
 * 约单表
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@TableName("qx_invite")
public class QxInvite implements Serializable {

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
     * 约单单号
     */
    @TableField("sn")
    private String sn;

    /**
     * 删除标识
     */
    @TableField("deleted")
    private Boolean deleted;

    /**
     * 邀请人
     */
    @TableField("inviter")
    private Long inviter;

    /**
     * 被邀请人
     */
    @TableField("invitee")
    private Long invitee;

    /**
     * 邀请时间
     */
    @TableField("invite_time")
    private Date inviteTime;

    /**
     * 约单类型 0-主动约；1-被动约
     */
    @TableField("invite_type")
    private String inviteType;

    /**
     * 约会类型
     */
    @TableField("date_type_id")
    private Long dateTypeId;

    /**
     * 扣款种类 礼物ID
     */
    @TableField("gift_id")
    private Long giftId;

    /**
     * 经度
     */
    @TableField("longitude")
    private BigDecimal longitude;

    /**
     * 纬度
     */
    @TableField("latitude")
    private String latitude;

    /**
     * 省
     */
    @TableField("province")
    private String province;

    /**
     * 市
     */
    @TableField("city")
    private String city;

    /**
     * 区县
     */
    @TableField("district")
    private String district;

    /**
     * 街道名称
     */
    @TableField("street")
    private String street;

    /**
     * 门牌号码
     */
    @TableField("streetNumber")
    private String streetNumber;

    /**
     * 状态 0-待配对;1-已配对;2-进行中;3-已完成;4-已取消;5-已投诉
     */
    @TableField("status")
    private String status;

    /**
     * 约单方式
     */
    @TableField("invite_way")
    private String inviteWay;
    
    /**
     * 约单描述
     */
    @TableField("content")
    private String content;
    
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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getInviter() {
        return inviter;
    }

    public void setInviter(Long inviter) {
        this.inviter = inviter;
    }

    public Long getInvitee() {
        return invitee;
    }

    public void setInvitee(Long invitee) {
        this.invitee = invitee;
    }

    public Date getInviteTime() {
        return inviteTime;
    }

    public void setInviteTime(Date inviteTime) {
        this.inviteTime = inviteTime;
    }

    public String getInviteType() {
        return inviteType;
    }

    public void setInviteType(String inviteType) {
        this.inviteType = inviteType;
    }

    public Long getDateTypeId() {
        return dateTypeId;
    }

    public void setDateTypeId(Long dateTypeId) {
        this.dateTypeId = dateTypeId;
    }

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInviteWay() {
		return inviteWay;
	}

	public void setInviteWay(String inviteWay) {
		this.inviteWay = inviteWay;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
    public String toString() {
        return "QxInvite{" +
        "id=" + id +
        ", version=" + version +
        ", createdBy=" + createdBy +
        ", createdTime=" + createdTime +
        ", updatedBy=" + updatedBy +
        ", updatedTime=" + updatedTime +
        ", sn=" + sn +
        ", deleted=" + deleted +
        ", inviter=" + inviter +
        ", invitee=" + invitee +
        ", inviteTime=" + inviteTime +
        ", inviteType=" + inviteType +
        ", dateTypeId=" + dateTypeId +
        ", giftId=" + giftId +
        ", longitude=" + longitude +
        ", latitude=" + latitude +
        ", province=" + province +
        ", city=" + city +
        ", district=" + district +
        ", street=" + street +
        ", streetNumber=" + streetNumber +
        ", status=" + status +
        ", inviteWay=" + inviteWay +
        ", content=" + content +
        "}";
    }
	
	public String getLocation() {
		return province + city + district + street + streetNumber;
	}
}
