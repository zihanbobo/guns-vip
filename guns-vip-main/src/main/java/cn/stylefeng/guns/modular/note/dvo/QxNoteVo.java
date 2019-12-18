package cn.stylefeng.guns.modular.note.dvo;

import java.util.Date;

import cn.stylefeng.guns.modular.note.entity.QxGift;
import lombok.Data;

@Data
public class QxNoteVo {
	// 日记信息
	private Long id;
	private Date createdTime;
	private Long userId;
	private String images;
	private String title;
	private String content;
	private Boolean canComment;
	private Integer favoriteCount;
	private Integer commentCount;
	private Integer giftCount;
	private Boolean isPrivate;
	private Integer watchCount;
	private Boolean isLock;
	// 用户信息
	private QxUserVo userVo;
	// 礼物
	private QxGift gift;
}
