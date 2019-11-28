package cn.stylefeng.guns.modular.note.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 约单表
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Data
public class QxInviteParam implements Serializable, BaseValidatingParam {

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
     * 约单单号
     */
    private String sn;

    /**
     * 删除标识
     */
    private Boolean deleted;

    /**
     * 邀请人
     */
    private Long inviter;

    /**
     * 被邀请人
     */
    private Long invitee;

    /**
     * 邀请时间
     */
    private Date inviteTime;

    /**
     * 约单类型 0-主动约；1-被动约
     */
    private String inviteType;

    /**
     * 约会类型
     */
    private Long dateTypeId;

    /**
     * 扣款种类 礼物ID
     */
    private Long giftId;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 街道名称
     */
    private String street;

    /**
     * 门牌号码
     */
    private String streetNumber;

    /**
     * 状态 0-待配对;1-已配对;2-进行中;3-已完成;4-已取消;5-已投诉
     */
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

    @Override
    public String checkParam() {
        return null;
    }

}
