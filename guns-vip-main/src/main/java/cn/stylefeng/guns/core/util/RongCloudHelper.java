package cn.stylefeng.guns.core.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.stylefeng.guns.config.ConfigEntity;
import cn.stylefeng.guns.core.exception.ServiceException;
import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;

@Component
public class RongCloudHelper {

	@Resource
	private ConfigEntity configEntity;
	
	public TokenResult generateChatToken(String userId, String name, String portrait) {
		RongCloud rongCloud = RongCloud.getInstance(configEntity.getRongCloudAppKey(), configEntity.getRongCloudAppSecret());
		User user = rongCloud.user;
		UserModel userModel = new UserModel()
				.setId(userId).setName(name).setPortrait(portrait);
		try {
			TokenResult result = user.register(userModel);
			return result;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
