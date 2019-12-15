package cn.stylefeng.guns.modular.note.dto;

import java.util.Date;

import lombok.Data;

@Data
public class QxInviteTo {
	private Date inviteTime;
	private String inviteType;
	private String inviteWay;
	private Long invitee;
	private Long dateTypeId;
	private Long giftId;
    private String longitude;
    private String latitude;
    private String province;
    private String city;
    private String district;
    private String street;
    private String streetNumber;
    private String content;
    private String shop;
}
