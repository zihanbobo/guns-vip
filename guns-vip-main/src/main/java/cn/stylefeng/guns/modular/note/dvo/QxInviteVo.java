package cn.stylefeng.guns.modular.note.dvo;

import java.util.Date;

import lombok.Data;

@Data
public class QxInviteVo {
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
    
	public String getLocation() {
		return province + city + district + street + streetNumber;
	}
}
