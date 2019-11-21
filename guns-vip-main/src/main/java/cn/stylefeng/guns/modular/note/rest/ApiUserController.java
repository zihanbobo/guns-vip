/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
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

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.HttpsUtils;

import cn.stylefeng.guns.config.ConfigEntity;
import cn.stylefeng.guns.core.CacheCodeUtil;
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.core.TokenUtils;
import cn.stylefeng.guns.core.constant.ProjectConstants.TOKEN;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.core.util.NoticeHelper;
import cn.stylefeng.guns.modular.note.dto.QxUserTo;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.service.QxUserService;
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
	
	@Resource
	private ConfigEntity configEntity;

	@Resource
	private QxUserService qxUserService;

	@Resource
	private NoticeHelper noticeHelper;

	@RequestMapping("/login")
	public Object login(@RequestParam("mobile") String mobile, @RequestParam("code") String code) {
//		validateCode(mobile, code, SMS_CODE.LOGIN_OR_REGISTER, "验证码");
		QxUser user = qxUserService.getUserByAccount(mobile);
		if (user == null) {
			user = performRegister(mobile);
		}
		JSONObject result = generateToken(user);
		return ResultGenerator.genSuccessResult(result);
	}

	private void validateCode(String mobile, String code,  int type, String name) {
		String codeKey = CacheCodeUtil.createCodeKey(mobile, type);
		validateCache(cache, codeKey, code, name);
	}
	
	private QxUser performRegister(String mobile) {
		QxUser user = new QxUser();
		user.setMobile(mobile);
		qxUserService.save(user);
		return user;
	}
	
	private JSONObject generateToken(QxUser user) {
		String accessId = TOKEN.USER + user.getId();
		String token = TokenUtils.createToken(accessId);
		cacheValueSecond(accessId, 60 * 60 * 24 * 30, token);
		JSONObject result = new JSONObject();
		result.put("id", user.getId());
		result.put("token",token);
		return result;
	}

	@RequestMapping(value = "/getCaptcha", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(QxUserTo userTo) {
		QxUser user = getUser();
		BeanUtils.copyProperties(userTo, user);
		qxUserService.updateById(user);
		log.info("/api/user/update, userTo=" + userTo);
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping(value = "/detail")
	public Object detail() {
		QxUser user = getUser();
		log.info("/api/user/detail");
		return ResultGenerator.genSuccessResult(user);
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
	@GetMapping("/wx/mp/oauthInfo")
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
	
	/**
	 * 微信公众号
	 * @param appid
	 * @param code
	 * @return
	 */
	@GetMapping("/wx/mp/getInfo")
    public Object wxMpGetInfo(@PathVariable String appid, @RequestParam String code) {
        if (!this.wxMpService.switchover(appid)) {
            throw new ServiceException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }

        try {
            WxMpOAuth2AccessToken accessToken = wxMpService.oauth2getAccessToken(code);
            WxMpUser wxUser = wxMpService.oauth2getUserInfo(accessToken, null);
            QxUser qxUser = qxUserService.getUserByUnionId(wxUser.getUnionId());
            log.info("/api/wx/mp/getInfo, appid=" + appid + ", code=" + code);
            return ResultGenerator.genSuccessResult(qxUser);
        } catch (WxErrorException e) {
        	log.error("获取用户信息出错，/api/user/getInfo, appid=" + appid + ", code=" + code + ", " + e.getMessage());
            throw new ServiceException("获取用户信息出错");
        }
    }
}
