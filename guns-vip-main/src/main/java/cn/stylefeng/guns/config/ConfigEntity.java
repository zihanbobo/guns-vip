package cn.stylefeng.guns.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "note")
@PropertySource(value = "classpath:config.properties", encoding = "UTF-8")
@Data
public class ConfigEntity {
	private String secureUrls;
	// 极光推送
	private String jpushMaster;
	private String jpushAppKey;
	// 阿里云
	private String aliyunAccessKeyId;
	private String aliyunSecret;
	// 验证码长度
	private int codeLength;
	// 验证码, 失效时间
	private int codeExpirationMin;
	// 验证码, 可重发时间
	private int codeIntervalMin;
	// 短信
	private String smsDomain;
	private String smsSignName;
	private String smsTemplate;
	// 邮箱
	private String emailAccountName;
	private String emailFromAlias;
	private String eTemplate;

	public String getTagName(Integer tag) {
		return getNoticeConfig(tag, false).get("tagName");
	}

	public String getTemplate(Integer tag, boolean isSms) {
		return getNoticeConfig(tag, isSms).get("template");
	}

	public String getSubject(Integer tag) {
		return "用户" + getTagName(tag);
	}

	public Map<String, String> getNoticeConfig(Integer tag, boolean isSms) {
		Map<String, String> map = new HashMap<>();
		map.put("template", isSms ? smsTemplate : eTemplate);
		map.put("tagName", "验证码");
		return map;
	}
}
