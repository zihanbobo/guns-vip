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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.stylefeng.guns.base.auth.jwt.JwtTokenUtil;
import cn.stylefeng.guns.core.constant.JwtConstants;
import cn.stylefeng.guns.sys.modular.system.mapper.UserMapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;

/**
 * api登录接口，获取token
 *
 * @author stylefeng
 * @Date 2018/7/20 23:39
 */
@RestController
@RequestMapping("/api")
public class ApiLoginController extends BaseController {

    @Autowired
    private UserMapper userMapper;

    /**
     * api登录接口，通过账号密码获取token
     */
    @RequestMapping("/auth")
    public Object auth(@RequestParam("username") String username,
                       @RequestParam("password") String password) {

//        //封装请求账号密码为shiro可验证的token
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password.toCharArray());
//
//        //获取数据库中的账号密码，准备比对
//        User user = userMapper.getByAccount(username);
//
//        String credentials = user.getPassword();
//        String salt = user.getSalt();
//        ByteSource credentialsSalt = new Md5Hash(salt);
//        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
//                new ShiroUser(), credentials, credentialsSalt, "");
//
//        //校验用户账号密码
//        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
//        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
//        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
//        boolean passwordTrueFlag = md5CredentialsMatcher.doCredentialsMatch(
//                usernamePasswordToken, simpleAuthenticationInfo);
//
//        if (passwordTrueFlag) {
            HashMap<String, Object> result = new HashMap<>();
            Map<String, Object> claims = new HashMap<>();
            final Date createdDate = new Date();
            final Date expirationDate = new Date(createdDate.getTime() + JwtConstants.EXPIRATION * 1000);
            result.put("token", JwtTokenUtil.generateToken("1", expirationDate, claims));
            return result;
//        } else {
//            return new ErrorResponseData(500, "账号密码错误！");
//        }
    }
    
    @RequestMapping("/test")
    public Object test() {
    	return SUCCESS_TIP;
    }
    
    @RequestMapping("/secret")
    public ResponseData secret() {
    	return SUCCESS_TIP;
    }

}

