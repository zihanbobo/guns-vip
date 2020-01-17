package cn.stylefeng.guns.modular.note.dvo;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.stylefeng.guns.modular.note.entity.QxDateType;
import cn.stylefeng.guns.modular.note.entity.QxGift;
import lombok.Data;

@Data
public class QxInviteVo {
	// 约单信息
	private Long id;
	private String sn;
	private String status;
	private Date createdTime;
	private Long inviter;
	private Long invitee;
	private Date inviteTime;
	private String inviteType;
	private String latitude;
	private String province;
	private String city;
	private String district;
	private String street;
	private String streetNumber;
	private String inviteWay;
	private String content;
	private String distance; // 距离
	private Integer remainDays; // 距离今天还剩几天
	private String shop;

	// 用户信息
	private QxUserVo userVo;
	// 被邀请人信息
	private QxUserVo inviteeUserVo;
	// 礼物信息
	private QxGift gift;
	// 约会类型
	private QxDateType dateType;

	public String getLocation() {
		return Stream.of(province, city, district, street, streetNumber, shop).filter(s -> s != null)
				.collect(Collectors.joining());
	}
}
