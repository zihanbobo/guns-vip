package cn.stylefeng.guns.modular.note.dvo;

import java.util.Date;

import lombok.Data;

@Data
public class QxTweetCommentVo {
	// 评论信息
	private Long id;
	private Date createdTime;
	private String content;
	// 用户信息
	private QxUserVo userVo;
}
