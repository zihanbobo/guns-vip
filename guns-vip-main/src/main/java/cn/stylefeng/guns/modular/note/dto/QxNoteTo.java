package cn.stylefeng.guns.modular.note.dto;

import lombok.Data;

@Data
public class QxNoteTo {
	private String images;
	private String title;
	private String content;
	private Boolean canComment;
	private Boolean isPrivate;
	private Long giftId;
}
