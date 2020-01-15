package cn.stylefeng.guns.modular.note.dto;

import lombok.Data;

@Data
public class QxInviteQueryTo {
	private String longitude;
	private String latitude;
	private String content;
	private String inviteType; // 约单类型 0-主动约；1-被动约
	private Long dateTypeId;	// 约会类型ID
	private Integer sex; // 性别 0-男;1-女
	private Integer inviteRange;
	private String order; // 排序：时间/距离
}
