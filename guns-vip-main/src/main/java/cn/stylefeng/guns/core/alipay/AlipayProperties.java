package cn.stylefeng.guns.core.alipay;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "alipay")
@Data
public class AlipayProperties {
	/**
	 * 网关
	 */
	private String gateway;

	/**
	 * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	 */
	private String appID;

	/**
	 * 签约支付宝ID
	 */
	private String pid;
	
	/**
	 * 商户私钥，您的PKCS8格式RSA2私钥
	 */
	private String privateKey;

	/**
	 * 类型
	 */
	private String format = "json";

	/**
	 * 编码
	 */
	private String charset = "utf-8";

	/**
	 * 签名方式
	 */
	private String signType;

	/**
	 * 应用公钥路径
	 */
	private String appCertPath;

	/**
	 * 支付宝证书路径
	 */
	private String alipayCertPath;

	/**
	 * 支付宝根证书路径
	 */
	private String alipayRootCertPath;
	
	/**
	 * 支付回调地址
	 */
	private String alipayNotifyUrl;
	
	/**
	 * 提现用户证件类型
	 */
	private String withdrawIdentityType;
	
	/**
	 * 提现商品Code
	 */
	private String withdrawProductCode;
	
	/**
	 * 提现业务场景
	 */
	private String withdrawBizScene;
}
