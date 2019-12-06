package cn.stylefeng.guns.modular.note.dvo;

import java.util.Date;

import lombok.Data;

@Data
public class QxTweetVo {
	// 推文内容
	private Long id;
	private Date createdTime;
	private String images;
	private String title;
	private String content;
	private Boolean canComment;
	private Integer favoriteCount;
	private Integer commentCount;
	private Integer giftCount;
	// 用户信息
	private QxUserVo userVo;
}
