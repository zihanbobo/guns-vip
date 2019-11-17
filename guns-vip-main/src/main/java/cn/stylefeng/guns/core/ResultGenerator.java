package cn.stylefeng.guns.core;

import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
	public static SuccessResponseData genSuccessResult() {
		return new SuccessResponseData();
	}

	public static SuccessResponseData genSuccessResult(Object data) {
		return new SuccessResponseData(data);
	}
}
