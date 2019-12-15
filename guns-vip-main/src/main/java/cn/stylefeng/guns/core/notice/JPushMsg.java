package cn.stylefeng.guns.core.notice;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import cn.stylefeng.guns.config.ConfigEntity;
import cn.stylefeng.guns.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JPushMsg {
	@Resource
	private ConfigEntity configEntity;
	
	public void sendPush(Long userId, String context) {
		sendPush(userId, context, new HashMap<>());
	}
	
	public void sendPush(Long userId, String context, Map<String, String> extras) {
		ClientConfig clientConfig = ClientConfig.getInstance();
		JPushClient jpushClient = new JPushClient(configEntity.getJpushMaster(), configEntity.getJpushAppKey(), null, clientConfig);
		PushPayload payload = buildPushObject_all_alias_alert(userId, context, extras);
		try {
			PushResult result = jpushClient.sendPush(payload);
			int status = result.getResponseCode();
			if (result.isResultOK()) {
				
			} else {
				String logMessage = "推送响应码错误: " + status;
				throw new ServiceException(logMessage);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	public static PushPayload buildPushObject_all_alias_alert(Long userId, String context, Map<String, String> extras) {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(userId.toString()))
				.setNotification(Notification.alert(context))
				.setMessage(Message.newBuilder()
						.setMsgContent(context)
						.addExtra("isMessage", true)
						.addExtras(extras)
						.build())
				.setOptions(Options.newBuilder().setApnsProduction(true).setTimeToLive(86000).build())
				.build();
	}
}
