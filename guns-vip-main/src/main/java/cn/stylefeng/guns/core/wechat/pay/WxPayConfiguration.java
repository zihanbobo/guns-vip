package cn.stylefeng.guns.core.wechat.pay;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;

import lombok.AllArgsConstructor;

/**
 * @author Binary Wang
 */
@Configuration
@ConditionalOnClass(WxPayService.class)
@EnableConfigurationProperties({ WxMpPayProperties.class, WxAppPayProperties.class })
@AllArgsConstructor
public class WxPayConfiguration {
	private WxMpPayProperties wxMpPayProperties;

	private WxAppPayProperties wxAppPayProperties;

	@Bean(name = "wxMpPayService")
	@ConditionalOnMissingBean
	public WxPayService wxMpPayService() {
		WxPayService wxPayService = new WxPayServiceImpl();
		WxPayConfig payConfig = createPayConfig(wxMpPayProperties);
		wxPayService.setConfig(payConfig);
		return wxPayService;
	}

	@Bean(name = "wxAppPayService")
	@ConditionalOnMissingBean
	public WxPayService wxAppPayService() {
		WxPayService wxPayService = new WxPayServiceImpl();
		WxPayConfig payConfig = createPayConfig(wxAppPayProperties);
		wxPayService.setConfig(payConfig);
		return wxPayService;
	}

	public WxPayConfig createPayConfig(WxPayProperties properties) {
		WxPayConfig payConfig = new WxPayConfig();
		payConfig.setAppId(StringUtils.trimToNull(properties.getAppId()));
		payConfig.setMchId(StringUtils.trimToNull(properties.getMchId()));
		payConfig.setMchKey(StringUtils.trimToNull(properties.getMchKey()));
		payConfig.setSubAppId(StringUtils.trimToNull(properties.getSubAppId()));
		payConfig.setSubMchId(StringUtils.trimToNull(properties.getSubMchId()));
		payConfig.setKeyPath(StringUtils.trimToNull(properties.getKeyPath()));
		payConfig.setUseSandboxEnv(properties.getUseSandbox());
		return payConfig;
	}
}
