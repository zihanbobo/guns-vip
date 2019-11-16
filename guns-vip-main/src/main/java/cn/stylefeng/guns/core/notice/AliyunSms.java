package cn.stylefeng.guns.core.notice;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

import cn.stylefeng.guns.config.ConfigEntity;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AliyunSms {
	@Resource
	private ConfigEntity configEntity;
	
	public static void main(String[] args) {
//		String accessKeyId = "LTAIgYLh2FivBaVU";
//		String secret = "XKYzXeb5pTpJTHDsx00WH7x0l0Lcl5";
//		
//		String domain = "dysmsapi.aliyuncs.com";
//		String signName = "天际线网络";
//		String template = "SMS_171875747";
//		
//		JSONObject o = new JSONObject();
//		o.put("code", "123456");
//		sendSms("13395115273", template, o.toJSONString());
	}
	
	public void sendSms(String mobile, String template, String code) {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", configEntity.getAliyunAccessKeyId(), configEntity.getAliyunSecret());
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", configEntity.getSmsDomain());
		} catch (ClientException e) {
			throw new ServiceException(400, e.getMessage());
		}
        IAcsClient client = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(mobile);
        request.setSignName(configEntity.getSmsSignName());
        request.setTemplateCode(template);
        request.setTemplateParam(code);
        try {
        	 SendSmsResponse response = client.getAcsResponse(request);
        	 if (!"OK".equals(response.getCode())) {
        		 throw new ServiceException(400, "短信返回失败！template=" + template + ", code=" + response.getCode()); 
        	 }
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
			throw new ServiceException(400, e.getMessage());
		}
	}
}
