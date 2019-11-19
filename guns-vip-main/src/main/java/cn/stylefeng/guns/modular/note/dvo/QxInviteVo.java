package cn.stylefeng.guns.modular.note.dvo;

import java.util.Date;

import lombok.Data;

@Data
public class QxInviteVo {
	// 约单信息
	private Long id;
	private String sn;
	private String status;
	private Date createdTime;
	private Date inviteTime;
    private String location;
    // 用户信息
    private QxUserVo userVo;
}
