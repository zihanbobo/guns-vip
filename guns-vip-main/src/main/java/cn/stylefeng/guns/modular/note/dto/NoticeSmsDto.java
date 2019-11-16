package cn.stylefeng.guns.modular.note.dto;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;

@Data
public class NoticeSmsDto {
	private Map<String, String> pairs = new HashMap<>();
	private String account;
	private Integer tag;
	private static int PARAMETER_SIZE = 20;
	
	public NoticeSmsDto(String account, Integer tag, Map<String, String> pairs) {
		this.account = account;
		this.tag = tag;
		this.pairs = pairs;
	}
	
	public String getParameters () {
		return JSONObject.toJSONString(this.pairs);
	}
}
