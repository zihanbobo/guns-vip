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

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.google.code.ssm.Cache;

import cn.stylefeng.guns.base.auth.jwt.JwtTokenUtil;
import cn.stylefeng.guns.base.auth.jwt.payload.JwtPayLoad;
import cn.stylefeng.guns.config.ConfigEntity;
import cn.stylefeng.guns.core.CacheCodeUtil;
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.core.constant.ProjectConstants.SMS_CODE;
import cn.stylefeng.guns.core.util.NoticeHelper;
import cn.stylefeng.guns.modular.note.dto.QxUserTo;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.service.QxUserService;
import lombok.extern.slf4j.Slf4j;

/**
 * api登录接口，获取token
 *
 * @author stylefeng
 * @Date 2018/7/20 23:39
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class ApiUserController extends ApiBaseController {

	@Resource
	private ConfigEntity configEntity;

	@Resource(name = "defaultMemcachedClient")
	protected Cache cache;

	@Resource
	private QxUserService qxUserService;

	@Resource
	private NoticeHelper noticeHelper;

	@RequestMapping("/login")
	public Object login(@RequestParam("mobile") String mobile, @RequestParam("code") String code) {
		validateCode(mobile, code, SMS_CODE.LOGIN_OR_REGISTER, "验证码");
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
		JSONObject result = new JSONObject();
		JwtPayLoad payLoad = new JwtPayLoad(user.getId(), user.getMobile(), null);
		result.put("id", user.getId());
		result.put("token", JwtTokenUtil.generateToken(payLoad));
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
}
