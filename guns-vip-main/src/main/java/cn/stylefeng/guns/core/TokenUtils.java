package cn.stylefeng.guns.core;

import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import com.google.code.ssm.Cache;
import com.google.code.ssm.api.format.SerializationType;
import com.google.code.ssm.providers.CacheException;

import cn.stylefeng.guns.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenUtils {
	// token 密钥
	public static final String KEY = "$KEEPMOVING";

	public static void main(String args[]) {
		String token = createToken(1 + "");
		System.out.println("生成 token = " + token);
		System.out.println("解析 token =" + getAccessId(token));
		System.out.println(token.length());
		System.out.println("ffe14dd05bd0dc2d9d10fa6325515202f76611d810c52bf04e07738597b76efae679da1743f9fd9988e160bf1bcc254f9ee7a20b".length());
		System.out.println("解析出 token=" + getAccessId("ffe14dd05bd0dc2d9d10fa6325515202f76611d810c52bf04e07738597b76efae679da1743f9fd9988e160bf1bcc254f9ee7a20b"));
	}
	
	/**
	 * 验证token
	 */
	public static String validate(String token, Cache cache) {
		if (StringUtils.isBlank(token)) {
			throw new ServiceException("认证失败，token为空，token=" + token);
		}
		String accessId = TokenUtils.getAccessId(token);
		String cacheValue = null;
		try {
			cacheValue = (String) cache.get(accessId, SerializationType.JAVA);
		} catch (TimeoutException | CacheException e) {
			throw new RuntimeException("缓存获取token报错！");
		}
		if (StringUtils.isBlank(cacheValue)) {
			throw new ServiceException("未认证，请重新登录!");
		}
		if (!token.equals(cacheValue)) {
			log.info("AccessId=" + accessId + ", token=" + token + ", tokenCache=" + cacheValue);
			throw new ServiceException("认证失效，请重新登录!");
		}
		return accessId;
	}

	/**
	 * 生成用户访问令牌
	 * 
	 * @param userId 访问日志分析需要
	 * @return
	 */
	public static String createToken(String userId) {
		String token = userId + ";" + getUUID();
		byte[] encrypt = XXTEA.encrypt(token.getBytes(), KEY.getBytes());
		return StringUtils.join(new Object[] { new String(Hex.encodeHex(encrypt)) }, "$");
	}

	/**
	 * 通过token 得到账号ID
	 * @param token
	 * @return
	 */
	private static String getAccessId(String token) {
		if (StringUtils.isBlank(token)) {
			throw new ServiceException("request token=" + token);
		}
		byte[] ms = null;
		try {
			ms = Hex.decodeHex(token.toCharArray());
		} catch (DecoderException e) {
			throw new RuntimeException(e);
		}
		byte[] bsource = XXTEA.decrypt(ms, KEY.getBytes());
		return new String(bsource).split(";")[0];
	}

	/**
	 * 产生一个全局唯一的序列标
	 * 
	 * @return 年（2位）＋月（2位）＋日（2痊）＋时（2位）＋分（2位）＋秒（2位）＋毫秒（3位）＋UUID（随机15位）
	 * @since 0.1
	 */
	public static String getUUID() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(calendar.getTime());
		StringBuffer u1 = new StringBuffer(formatNumber(calendar.get(Calendar.YEAR), 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.MONTH) + 1, 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.DAY_OF_MONTH), 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.HOUR_OF_DAY), 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.MINUTE), 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.SECOND), 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.MILLISECOND), 3, '0'));
		String u2 = UUID.randomUUID().toString();
		u2 = u2.replaceAll("-", "");
		return u1.toString() + u2.substring(15);
	}

	/**
	 * 返回整形数的指定长度，指定填充因子的字符串
	 * 
	 * @param number 指定整形数
	 * @param destLength 指定长度
	 * @param paddedChar 指定填充因子
	 * @return 如果该整形数长度大于指定长度。截到一部分，如果小于指定长度，左填充指定填充因子
	 */
	public static String formatNumber(int number, int destLength, char paddedChar) {
		String oldString = String.valueOf(number);
		StringBuffer newString = new StringBuffer("");
		int oldLength = oldString.length();
		if (oldLength > destLength) {
			newString.append(oldString.substring(oldLength - destLength));
		} else if (oldLength == destLength) {
			newString.append(oldString);
		} else {
			for (int i = 0; i < destLength - oldLength; i++) {
				newString.append(paddedChar);
			}
			newString.append(oldString);
		}
		return newString.toString();
	}

}
