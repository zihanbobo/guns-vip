package cn.stylefeng.guns.modular.note.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class NoticeEmailDto {
	private Map<String, String> pairs = new HashMap<>();
	private String account;
	private Integer tag;
	
	public NoticeEmailDto(String account, Integer tag, Map<String, String> pairs) {
		this.account = account;
		this.tag = tag;
		this.pairs = pairs;
	}
	
	public String getTextBody(String template) {
		for (String key : pairs.keySet()) {
			template = template.replace("${" + key + "}", pairs.get(key));
		}
		return template;
	}
}	
