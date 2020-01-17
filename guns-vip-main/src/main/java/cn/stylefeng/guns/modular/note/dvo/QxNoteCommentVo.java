package cn.stylefeng.guns.modular.note.dvo;

import lombok.Data;

@Data
public class QxNoteCommentVo {
	// 评论信息
	private Long id;
	private String createdTime;
	private String content;
	// 用户信息
	private QxUserVo userVo;
}
