package cn.stylefeng.guns.config.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * 默认的string to date的转化
 *
 * @author fengshuonan
 * @Date 2019/2/12 20:09
 */
@Configuration
public class String2DateConfig {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    /**
     * 默认时间转化器
     */
    @PostConstruct
    public void addConversionConfig() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        if ((initializer != null ? initializer.getConversionService() : null) != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
            genericConversionService.addConverter(new StringToDateConverter());
        }
    }

    public class StringToDateConverter implements Converter<String, Date> {
    	private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    	private static final String shortDateFormat = "yyyy-MM-dd";

    	@Override
    	public Date convert(String value) {
    		if (StringUtils.isEmpty(value)) {
    			return null;
    		}
    		value = value.trim();
    		try {
    			if (value.contains("-")) {
    				SimpleDateFormat formatter = null;
    				if (value.contains(":")) {
    					formatter = new SimpleDateFormat(dateFormat);
    				} else {
    					formatter = new SimpleDateFormat(shortDateFormat);
    				}
    				return formatter.parse(value);
    			} else if (value.matches("^\\d+$")) {
    				return new Date(new Long(value));
    			}
    		} catch (Exception e) {
    			throw new RuntimeException(String.format("parser %s to Date fail", value));
    		}
    		throw new RuntimeException(String.format("parser %s to Date fail", value));
    	}
    }

}
