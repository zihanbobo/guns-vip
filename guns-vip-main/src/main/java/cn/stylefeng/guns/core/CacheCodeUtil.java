package cn.stylefeng.guns.core;

import java.util.Random;

public class CacheCodeUtil {
	
	public static String createCode(int length) {
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	public static String createCodeKey(String phone, int type) {
		return "CODE" + "_" + type + "_" + phone;
	}
	
	public static String createCodeKey(String phone, int type, int userRole) {
		return "CODE" + "_" + userRole + "_" + type + "_" + phone;
	}
}
