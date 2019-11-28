package cn.stylefeng.guns.modular.note.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@TableName("qx_user")
public class QxUser implements Serializable {

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
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 身高
     */
    @TableField("height")
    private Integer height;

    /**
     * 性别 0-男;1-女
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 状态 0-正常；1-禁用
     */
    @TableField("status")
    private String status;

    /**
     * 信用分
     */
    @TableField("score")
    private Integer score;

    /**
     * 金币余额
     */
    @TableField("balance")
    private Integer balance;
    
    /**
     * 冻结金币
     */
    @TableField("freeze")
    private Integer freeze;
    
    /**
     * 我的邀请码
     */
    @TableField("invite_code")
    private String inviteCode;
    
    /**
     * 邀请人ID
     */
    @TableField("parent_id")
    private Long parentId;


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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    
    public Integer getFreeze() {
		return freeze;
	}

	public void setFreeze(Integer freeze) {
		this.freeze = freeze;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
    public String toString() {
        return "QxUser{" +
        "id=" + id +
        ", version=" + version +
        ", createdBy=" + createdBy +
        ", createdTime=" + createdTime +
        ", updatedBy=" + updatedBy +
        ", updatedTime=" + updatedTime +
        ", deleted=" + deleted +
        ", mobile=" + mobile +
        ", nickname=" + nickname +
        ", age=" + age +
        ", height=" + height +
        ", sex=" + sex +
        ", avatar=" + avatar +
        ", status=" + status +
        ", score=" + score +
        ", balance=" + balance +
        ", freeze=" + freeze +
        ", inviteCode=" + inviteCode +
        ", parentId=" + parentId +
        "}";
    }
}
