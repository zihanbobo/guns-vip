package cn.stylefeng.guns.modular.note.rest;

import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import com.google.code.ssm.Cache;
import com.google.code.ssm.api.format.SerializationType;
import com.google.code.ssm.providers.CacheException;

import cn.stylefeng.guns.config.ConfigEntity;
import cn.stylefeng.roses.core.base.controller.BaseController;

public class ApiBaseController extends BaseController {
	@Resource
	protected ConfigEntity configEntity;

	@Resource(name = "defaultMemcachedClient")
	protected Cache cache;

	protected void cacheValueSecond(String key, int expiration, Object value) {
		try {
			cache.set(key, expiration, value, SerializationType.JAVA);
		} catch (TimeoutException | CacheException e) {
			throw new RuntimeException("缓存" + key + "异常！", e);
		}
	}
}
