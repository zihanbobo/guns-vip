package cn.stylefeng.guns.sys.core.auth.cache.impl;

import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.sys.core.auth.cache.SessionManager;

/**
 * 基于redis的会话管理
 *
 * @author fengshuonan
 * @date 2019-09-28-14:43
 */
public class RedisSessionManager implements SessionManager {

    @Override
    public void createSession(String token, LoginUser loginUser) {
    }

    @Override
    public LoginUser getSession(String token) {
        return null;
    }

    @Override
    public void removeSession(String token) {

    }

    @Override
    public boolean haveSession(String token) {
        return false;
    }

}
