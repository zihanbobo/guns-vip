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
    private String province;
    private String city;
    private String district;
    private String street;
    private String streetNumber;
    // 用户信息
    private QxUserVo userVo;
   
	public String getLocation() {
		return province + city + district + street + streetNumber;
	}
}
