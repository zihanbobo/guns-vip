package cn.stylefeng.guns.config;

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
}
