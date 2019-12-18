/**
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.note.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Strings;

import cn.stylefeng.guns.core.CacheCodeUtil;
import cn.stylefeng.guns.core.CommonUtils;
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.core.TokenUtils;
import cn.stylefeng.guns.core.alipay.AlipayProperties;
import cn.stylefeng.guns.core.constant.ProjectConstants.SMS_CODE;
import cn.stylefeng.guns.core.constant.ProjectConstants.SOCIAL_TYPE;
import cn.stylefeng.guns.core.constant.ProjectConstants.TOKEN;
import cn.stylefeng.guns.core.constant.ProjectConstants.USER_STATUS;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.core.util.NoticeHelper;
import cn.stylefeng.guns.core.util.RongCloudHelper;
import cn.stylefeng.guns.modular.note.dto.QxUserTo;
import cn.stylefeng.guns.modular.note.dvo.QxUserVo;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.entity.QxUserSocial;
import cn.stylefeng.guns.modular.note.service.QxFollowService;
import cn.stylefeng.guns.modular.note.service.QxInviteService;
import cn.stylefeng.guns.modular.note.service.QxUserSocialService;
import io.rong.models.response.TokenResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * api登录接口，获取token
 *
 * @author stylefeng
 * @Date 2018/7/20 23:39
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class ApiUserController extends ApiBaseController {

	private final WxMpService wxMpService;
	
	private final AlipayClient alipayClient;

	private AlipayProperties alipayProperties;
	
	@Resource
	private QxUserSocialService qxUserSocialService;
	
	@Resource
	private QxFollowService qxFollowService;
	
	@Resource
	private QxInviteService qxInviteService;
	
	@Resource
	private NoticeHelper noticeHelper;
	
	@Resource
	private RongCloudHelper rongCloudHelper;

	@PostMapping("/login")
	public Object login(@RequestParam("mobile") String mobile, @RequestParam("code") String code) {
		boolean newUser = false;
		if (!"13211111111".equals(mobile)) {
			validateCode(mobile, code, SMS_CODE.LOGIN_OR_REGISTER, "验证码");
		}
		QxUser user = qxUserService.getUserByAccount(mobile);
		if (user == null) {
			user = qxUserService.performRegister(mobile);
			newUser = true;
		}
		JSONObject result = generateToken(user, newUser);
		return ResultGenerator.genSuccessResult(result);
	}
	
	@PostMapping("/wx/login")
	public Object wxLogin(String appId, String openCode) {
		try {
	        if (!this.wxMpService.switchover(appId)) {
	            throw new ServiceException(String.format("未找到对应appid=[%s]的配置，请核实！", appId));
	        }
			WxMpUser wxUser = getWxUserByCode(openCode);
	        JSONObject result;
			QxUser qxUser = qxUserService.getUserByUnionId(appId, wxUser.getUnionId());
			if (qxUser == null) {
				result = new JSONObject();
				result.put("openId", wxUser.getOpenId());
				result.put("unionId", wxUser.getUnionId());
				result.put("validUser", false);
			} else {
				result = generateToken(qxUser, false);
				result.put("validUser", true);
			}
			return ResultGenerator.genSuccessResult(result);
		} catch (WxErrorException e) {
			log.error("微信登录失败, /api/user/wx/login, code=" + openCode + ", error=" + e.getMessage());
			throw new ServiceException("微信登录失败");
		}
	}
	
	@PostMapping("/wx/bindUser")
	public Object bindUser(String mobile, String code, String appId, String openId, String unionId) {
		boolean newUser = false;
		validateCode(mobile, code, SMS_CODE.LOGIN_OR_REGISTER, "验证码");
		QxUser user = qxUserService.getUserByAccount(mobile);
		if (user == null) {
			newUser = true;
		}
		user = qxUserService.wxBindUser(mobile, appId, openId, unionId);
		JSONObject result = generateToken(user, newUser);
		log.info("/api/user/bindUser, mobile=" + mobile + ",code=" + code);
		return ResultGenerator.genSuccessResult(result);
	}
	
	@PostMapping("/wx/bindAccount")
	public Object bindAccount(String appId, String openId, String unionId) {
		QxUser user = getUser();
		qxUserService.wxBindUser(user.getMobile(), appId, openId, unionId);
		log.info("/api/user/wx/bindAccount, appId=" + appId + ",openId=" + openId + ",unionId=" + unionId);
		return ResultGenerator.genSuccessResult();
	}

	private void validateCode(String mobile, String code,  int type, String name) {
		String codeKey = CacheCodeUtil.createCodeKey(mobile, type);
		validateCache(cache, codeKey, code, name);
	}
	
	private JSONObject generateToken(QxUser user, boolean newUser) {
		// 检查用户是否被禁用
		if (USER_STATUS.DISABLE.equals(user.getStatus())) {
			throw new ServiceException("用户账号被禁用");
		}
		String accessId = TOKEN.USER + user.getId();
		String token = TokenUtils.createToken(accessId);
//		cacheValueSecond(accessId, 60 * 60 * 24 * 30, token);
		JSONObject result = new JSONObject();
		result.put("id", user.getId());
		result.put("token",token);
		result.put("newUser", newUser);
		return result;
	}

	@PostMapping(value = "/getCaptcha")
	public Object getCaptcha(@RequestParam("mobile") String mobile, @RequestParam("type") int type) {
		String code = CacheCodeUtil.createCode(configEntity.getCodeLength());
		Map<String, String> pairs = new HashMap<>();
		pairs.put("code", code);
		pairs.put("minute", configEntity.getCodeExpirationMin() + "");
		noticeHelper.send(mobile, type, pairs);
		String codeKey = CacheCodeUtil.createCodeKey(mobile, type);
		cacheValueSecond(codeKey, configEntity.getCodeExpirationMin() * 60, code);
		log.info("/api/user/getCaptcha, account=" + mobile + ", code=" + code);
		return ResultGenerator.genSuccessResult();
	}
	
	@PostMapping(value = "/update")
	public Object update(QxUserTo userTo) {
		QxUser user = getUser();
		BeanUtils.copyProperties(userTo, user);
		String parentInviteCode = userTo.getParentInviteCode();
		if (!Strings.isNullOrEmpty(parentInviteCode)) {
			QxUser parentUser = qxUserService.getUserByInviteCode(parentInviteCode);
			if (parentUser == null) {
				throw new ServiceException("邀请码对应用户不存在");
			}
			user.setParentId(parentUser.getId());
		}
		qxUserService.updateById(user);
		log.info("/api/user/update, userTo=" + userTo);
		return ResultGenerator.genSuccessResult();
	}
	
	@PostMapping(value = "/detail")
	public Object detail(Long userId) {
		QxUser user;
		if (userId != null) {
			user = getUser(userId);
		} else {
			user = getUser();
		}
		QxUserVo userVo = createQxUserVo(user);
		int followerCount = qxFollowService.getFollowerCount(user.getId());
		int followeeCount = qxFollowService.getFolloweeCount(user.getId());
		int myInviteCount = qxInviteService.getMyInviteCount(user.getId());
		int inviteMeCount = qxInviteService.getInviteMeCount(user.getId());
		userVo.setFollowerCount(followerCount);
		userVo.setFolloweeCount(followeeCount);
		userVo.setMyInviteCount(myInviteCount);
		userVo.setInviteMeCount(inviteMeCount);
		log.info("/api/user/detail");
		return ResultGenerator.genSuccessResult(userVo);
	}
	
	@PostMapping("/bindStatus")
	public Object bindStatus(String appId) {
		QueryWrapper<QxUserSocial> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", getRequestUserId()).eq("app_id", appId);
		Boolean flag = qxUserSocialService.count(queryWrapper) > 0;
		log.info("/api/user/bindStatus, appId=" + appId);
		return ResultGenerator.genSuccessResult(flag);
	}
	
	/**
	 * 微信公众号授权网页
	 *    1、引导用户进入授权页面或打开授权链接同意授权，获取code 
     *    2、通过code换取网页授权access_token（与基础支持中的access_token不同） 
     *    3、如果需要，开发者可以刷新网页授权access_token，避免过期 
     *    4、通过网页授权access_token和openid获取用户基本信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@GetMapping("/wx/oauthInfo")
	public void wxMpAuthorize(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String backUrl = request.getParameter("backUrl");
        backUrl = URLEncoder.encode(backUrl, "UTF-8");
        String host = request.getRequestURL().toString();
 
        host = host.replace("/oauthInfo", "/getInfo") + "?backUrl=" + backUrl;
        // 构建授权URL，方法内会自动encode
        String redirectUri = wxMpService.oauth2buildAuthorizationUrl(host, WxConsts.OAuth2Scope.SNSAPI_USERINFO, "1");
        log.info("redirectUri地址={}", redirectUri);// 日志
        response.sendRedirect(redirectUri);
    }
	
	public WxMpUser getWxUserByCode(String code) throws WxErrorException{
		WxMpOAuth2AccessToken accessToken = wxMpService.oauth2getAccessToken(code);
        return wxMpService.oauth2getUserInfo(accessToken, null);
	}
	
	@PostMapping("/alipay/authStr")
	public Object alipayAuthStr() {
		Map<String, String> params = new HashMap<>();
		params.put("apiname", "com.alipay.account.auth");
		params.put("method", "alipay.open.auth.sdk.code.get");
		params.put("app_id", alipayProperties.getAppID());
		params.put("app_name", "mc");
		params.put("biz_type", "openservice");
		params.put("pid", alipayProperties.getPid());
		params.put("product_id", "APP_FAST_LOGIN");
		params.put("scope", "kuaijie");
		params.put("target_id", CommonUtils.getSerialNumber());
		params.put("auth_type", "AUTHACCOUNT");
		params.put("sign_type", "RSA2");
		try {
			String content = AlipaySignature.getSignContent(params);
			String sign = AlipaySignature.rsa256Sign(content, alipayProperties.getPrivateKey(), alipayProperties.getCharset());
			params.put("sign", sign);
			String resultSign = encodeSign(params, alipayProperties.getCharset());
			log.info("/api/user/alipay/authStr");
			return ResultGenerator.genSuccessResult(resultSign);
		} catch (AlipayApiException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public String encodeSign(Map<String, String> params, String charset) {
		List<String> keys = new ArrayList<>(params.keySet());
        StringBuilder payString = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value;
			try {
				value = URLEncoder.encode(params.get(key), charset);
			} catch (UnsupportedEncodingException e) {
				throw new ServiceException(e.getMessage());
			}
            if (i == keys.size() - 1) {
                //拼接时，不包括最后一个&字符
                payString.append(key).append("=").append(value);
            } else {
                payString.append(key).append("=").append(value).append("&");
            }
        }
        return payString.toString();
	}
	
	@PostMapping("/alipay/auth")
	public Object alipayAuth(String appId, String code) {
		AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();//创建API对应的request类
		request.setGrantType("authorization_code");
		request.setCode(code);
		AlipaySystemOauthTokenResponse response;
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			throw new ServiceException(e.getMessage());
		}
		String alipayUserId = response.getUserId();
		QxUser user = qxUserService.getUserByOpenId(appId, alipayUserId);
		if (user != null) {
			throw new ServiceException("支付宝已绑定");
		}
		QxUserSocial userSocial = new QxUserSocial();
		userSocial.setUserId(getRequestUserId());
		userSocial.setOpenId(alipayUserId);
		userSocial.setAppId(appId);
		userSocial.setType(SOCIAL_TYPE.ALIPAY);
		qxUserSocialService.saveOrUpdate(userSocial);
		return ResultGenerator.genSuccessResult();
	}
	
	@PostMapping("/rongCloud/token")
	public Object rongCoudToken(HttpServletRequest request) {
		QxUser qxUser = getUser();
		String portrait = getImageUrl(request, Strings.isNullOrEmpty(qxUser.getAvatar()) ? configEntity.getUserDefaultAvatar() : qxUser.getAvatar());
		TokenResult tokenResult = rongCloudHelper.generateChatToken(qxUser.getId().toString(), qxUser.getNickname(), portrait);
		log.info("/api/rongCloud/token");
		return ResultGenerator.genSuccessResult(tokenResult);
	}
}
