package cn.stylefeng.guns.modular.note.dvo;

import lombok.Data;

@Data
public class QxUserVo {
	private Long id;
	private String nickname;
	private String avatar;
	private Integer sex;
	private Integer age;
	private Integer height;
	private Integer score;
	private Integer balance;
	private Integer freeze;
	private Integer followerCount; // 关注数
	private Integer followeeCount; // 粉丝数
	private Integer myInviteCount; // 我发起的约单
	private Integer inviteMeCount; // 邀请我的约单
}
