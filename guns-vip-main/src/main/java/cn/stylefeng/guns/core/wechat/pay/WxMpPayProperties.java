package cn.stylefeng.guns.core.wechat.pay;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * wxpay pay properties.
 *
 * @author Binary Wang
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ConfigurationProperties(prefix = "wx.pay.mp")
public class WxMpPayProperties extends WxPayProperties{

}
