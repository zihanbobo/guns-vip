package cn.stylefeng.guns.core.wechat.pay;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "wx.pay.app")
public class WxAppPayProperties extends WxPayProperties {

}
