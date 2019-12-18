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
package cn.stylefeng.guns.core.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.google.code.ssm.Cache;

import cn.stylefeng.guns.core.TokenUtils;
import cn.stylefeng.guns.core.constant.JwtConstants;
import cn.stylefeng.guns.core.constant.ProjectConstants.TOKEN;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import lombok.extern.slf4j.Slf4j;


/**
 * Rest Api接口鉴权
 *
 * @author stylefeng
 * @Date 2018/7/20 23:11
 */
@Slf4j
public class RestApiInteceptor extends HandlerInterceptorAdapter {

	private Cache cache;
	
	public RestApiInteceptor(Cache cache) {
		this.cache = cache;
	}
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);
		setCrossOrigin(request, response);
		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpStatus.NO_CONTENT.value());
			return true;
		}
    	try {
			String accessId = TokenUtils.getAccessId(request.getHeader(JwtConstants.AUTH_HEADER));
			String userId = accessId.replaceAll(TOKEN.USER, "");
			request.setAttribute("userId", userId);
			log.info("UserID = " + userId);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		ErrorResponseData result = new ErrorResponseData("认证失败！");
		response.getWriter().write(JSON.toJSONString(result));
		return false;
    }
    
    private void setCrossOrigin(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, Authorization, token");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "3600");
	}
}
