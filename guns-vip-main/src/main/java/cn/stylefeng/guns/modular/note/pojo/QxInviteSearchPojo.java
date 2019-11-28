package cn.stylefeng.guns.modular.note.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class QxInviteSearchPojo{
    private Long id;
    private Integer version;
    private Long createdBy;
    private Date createdTime;
    private Long updatedBy;
    private Date updatedTime;
    private String sn;
    private Boolean deleted;
    private Long inviter;
    private Long invitee;
    private Date inviteTime;
    private String inviteType;
    private Long dateTypeId;
    private Long giftId;
    private String longitude;
    private String latitude;
    private String province;
    private String city;
    private String district;
    private String street;
    private String streetNumber;
    private String status;
    private String inviteWay;
    private String content;
	private String distance; // 距离
}
