package cn.stylefeng.guns.core.notice;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import cn.stylefeng.guns.config.ConfigEntity;
import cn.stylefeng.guns.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AliyunEmail {
	@Resource
	private ConfigEntity configEntity;
	
	public static void main(String[] args) {
//		String accessKeyId = "LTAIgYLh2FivBaVU";
//		String secret = "XKYzXeb5pTpJTHDsx00WH7x0l0Lcl5";
//		String subject = "验证码";
//		String textTemplate = "您的验证码是${code}，该验证码5分钟内有效，请勿泄漏于他人！";
//		String fromAlias = "天际线网络";
//		String tagName = "验证码";
//		String accountName = "service@email.skyline-auto.cn";
//		String text = textTemplate.replace("${code}", "123456");
//		sendText("yulai_li@163.com", tagName, subject, text);
	}
	
	public void sendText(String address, String tagName, String subject, String textBody) {
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", configEntity.getAliyunAccessKeyId(), configEntity.getAliyunSecret());
		IAcsClient client = new DefaultAcsClient(profile);
		SingleSendMailRequest request = new SingleSendMailRequest();
		try {
			request.setAccountName(configEntity.getEmailAccountName());
			request.setFromAlias(configEntity.getEmailFromAlias());
			request.setAddressType(1);
			request.setTagName(tagName);
			request.setReplyToAddress(true);
			request.setToAddress(address);
			request.setSubject(subject);
			request.setTextBody(textBody);
			client.getAcsResponse(request);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage());
		}
	}
}
