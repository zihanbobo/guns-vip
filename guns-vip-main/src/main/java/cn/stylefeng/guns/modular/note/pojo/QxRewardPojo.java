package cn.stylefeng.guns.modular.note.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class QxRewardPojo {
	private Long userId;
	private String nickname;
	private String avatar;
	private String giftName;
	private String giftPrice;
	private String giftImage;
	private Date createdTime;
}
