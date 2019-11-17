package cn.stylefeng.guns.modular.note.rest;

import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.google.code.ssm.Cache;
import com.google.code.ssm.api.format.SerializationType;
import com.google.code.ssm.providers.CacheException;

import cn.stylefeng.guns.config.ConfigEntity;
import cn.stylefeng.guns.core.exception.ServiceException;
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
	
	public static void validateCache(Cache cache, String key, String value, String name) {
		String cacheValue = null;
		if (StringUtils.isBlank(key)) {
			throw new ServiceException(name + "不能为空!");
		}
		try {
			cacheValue = (String) cache.get(key, SerializationType.JAVA);
		} catch (Exception e) {
			throw new RuntimeException("获取缓存" + name + "报错！", e);
		}
		if (cacheValue == null || !cacheValue.equalsIgnoreCase(value)) {
			throw new ServiceException(name + "错误!");
		}
	}
}
