package cn.stylefeng.guns.core.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 程序常量配置
 * 
 * @author steven
 *
 */
public final class ProjectConstants {
	/**
	 * 默认礼物ID
	 */
	public static Long DEFAULT_GIFT_ID = 1L;
	
	/**
	 * 用户 token
	 */
	public interface TOKEN {
		String ADMIN = "ADMIN_";
		String USER = "USER_";
	}

	public interface SOCIAL_TYPE {
		String WECHAT = "0";
		String ALIPAY = "1";
	}
	
	/**
	 * 用户状态：0-正常;1-禁用
	 * @author steven
	 *
	 */
	public interface USER_STATUS {
		String ENABLE = "0";
		String DISABLE = "1";
	}
	
	/**
	 * 用户信用分
	 */
	public interface USER_CREDIT {
		Integer INITIAL_SCORE = 500;
	}

	/**
	 * 通知类型：0-短信，1-邮箱，2-消息；
	 */
	public interface NOTICE_TYPE {
		int SMS = 0;
		int EMAIL = 1;
		int PUSH = 2;
	}

	/**
	 * 消息读取状态 0-未读；1-已读
	 * 
	 * @author steven
	 *
	 */
	public interface NOTICE_READ_STATUS {
		int UNREAD = 0;
		int READ = 1;
	}

	/**
	 * 消息发送状态
	 * 
	 * 0-未发送；1-已发送
	 * 
	 * @author steven
	 *
	 */
	public interface NOTICE_SEND_STATUS {
		int UNSEND = 0;
		int SEND = 1;
	}

	/**
	 * 手机验证码: 0-登录/注册验证码;1-约单成功；2-约单失败
	 * 
	 * @author steven
	 *
	 */
	public interface SMS_CODE {
		int LOGIN_OR_REGISTER = 0;
		int INVITE_SUCCESS = 1;
		int INVITE_FAIL = 2;
		int EMERGENCY = 3;
	}

	/**
	 * 约单状态 0-待配对;1-已配对;2-进行中;3-已完成;4-已取消;5-已投诉;6-已拒绝
	 * 
	 * @author steven
	 *
	 */
	public interface INVITE_STATUS {
		String WAIT_MATCH = "0";
		String MATCHED = "1";
		String DATING = "2";
		String FINISH = "3";
		String CANCEl = "4";
		String COMPLAINT = "5";
	}

	/**
	 * 约单报名状态：0-待确定；1-已确定；2-未确定
	 * 
	 * @author steven
	 *
	 */
	public interface INVITE_APPLY_STATUS {
		String UN_SURE = "0";
		String AGREE = "1";
		String REJECT = "2";
	}

	/**
	 * 约单方式：0-单独约；1-报名约
	 * 
	 * @author steven
	 *
	 */
	public interface INVITE_WAY {
		String SINGLE = "0";
		String MULTIPLE = "1";
	}

	/**
	 * 约单类型：0-主动约；1-被动约
	 */
	public interface INVITE_TYPE {
		String ACTIVE = "0";
		String PASSIVE = "1";
	}

	/**
	 * 操作类型：0-确认开始；1-确认结束；2-确认取消；
	 * 
	 * @author steven
	 */
	public interface INVITE_OPERATE_TYPE {
		String CONFIRM_START = "0";
		String CONFIRM_FINISH = "1";
		String CONFIRM_CANCEL = "2";
	}

	/**
	 * 报警状态: 0-未处理；1-已处理
	 */
	public interface ALERT_STATUS {
		String UNHANDLE = "0";
		String HANDLED = "1";
	}

	/**
	 * 用户费用类型： 0-约单支出；1-约单汇入；2-打赏支出；3-打赏汇入；4-兑换商品支出；
	 * 5-购买礼物支出；6-付费日记支出；7-付费日记汇入；8-违约金支出； 9-违约金汇入；10-购买金币支出；11-金币提现汇入；
	 * 12-平台代买金币支出;
	 * @author steven
	 *
	 */
	public interface USER_PAY_LOG_TYPE {
		String INVITE_OUT = "0";
		String INVITE_IN = "1";
		String REWARD_OUT = "2";
		String REWARD_IN = "3";
		String BUY_PRODUCT_OUT = "4";
		String BUY_GIFT_OUT = "5";
		String NOTE_OUT = "6";
		String NOTE_IN = "7";
		String COMPENSATION_OUT = "8";
		String COMPENSATION_IN = "9";
		String BUY_COIN_OUT = "10";
		String WITHDRAW_COIN_IN = "11";
		String PLATFORM_BUY_COIN_OUT = "12";
	}

	public static Map<String, String> FINANCE_LOG_MAP = new HashMap<>();
	static {
		FINANCE_LOG_MAP.put(USER_PAY_LOG_TYPE.INVITE_OUT, "约单支出");
		FINANCE_LOG_MAP.put(USER_PAY_LOG_TYPE.INVITE_IN, "约单汇入");
		FINANCE_LOG_MAP.put(USER_PAY_LOG_TYPE.REWARD_OUT, "打赏支出");
		FINANCE_LOG_MAP.put(USER_PAY_LOG_TYPE.REWARD_IN, "打赏汇入");
		FINANCE_LOG_MAP.put(USER_PAY_LOG_TYPE.BUY_PRODUCT_OUT, "兑换商品支出");
		FINANCE_LOG_MAP.put(USER_PAY_LOG_TYPE.BUY_GIFT_OUT, "购买礼物支出");
		FINANCE_LOG_MAP.put(USER_PAY_LOG_TYPE.NOTE_OUT, "付费日记支出");
		FINANCE_LOG_MAP.put(USER_PAY_LOG_TYPE.NOTE_IN, "付费日记汇入");
		FINANCE_LOG_MAP.put(USER_PAY_LOG_TYPE.COMPENSATION_OUT, "违约金支出");
		FINANCE_LOG_MAP.put(USER_PAY_LOG_TYPE.COMPENSATION_IN, "违约金汇入");
		FINANCE_LOG_MAP.put(USER_PAY_LOG_TYPE.BUY_COIN_OUT, "购买金币支出");
		FINANCE_LOG_MAP.put(USER_PAY_LOG_TYPE.WITHDRAW_COIN_IN, "金币提现汇入");
		FINANCE_LOG_MAP.put(USER_PAY_LOG_TYPE.PLATFORM_BUY_COIN_OUT, "平台代买金币支出");
	}

	/**
	 * 反馈处理状态
	 * 
	 * @author steven
	 *
	 */
	public interface FEEDBACK_STATUS {
		String UNHANDLE = "0";
		String HANDLED = "1";
	}

	/**
	 * 合作处理状态
	 * 
	 * @author steven
	 *
	 */
	public interface COOPERATE_STATUS {
		String UNHANDLE = "0";
		String HANDLED = "1";
	}

	/**
	 * 充值订单状态
	 * 
	 * @author steven
	 *
	 */
	public interface COIN_ORDER_STATUS {
		String WAIT_PAY = "0";
		String PAID = "1";
		String CANCEL = "2";
	}

	/**
	 * 充值方式：0-微信；1-支付宝；2-平台；3-苹果内购
	 */
	public interface COIN_ORDER_PAY_TYPE {
		String WECHAT = "0";
		String ALIPAY = "1";
		String PLATFORM = "2";
		String APPLE_IAP = "3";
	}

	/**
	 * 提现方式：0-微信；1-支付宝
	 */
	public interface WITHDRAW_PAY_WAY {
		String WECHAT = "0";
		String ALIPAY = "1";
	}

	/**
	 * 提现状态 0-已申请；1-已提现;2-已取消
	 * 
	 * @author steven
	 */
	public interface WITHDRAW_STATUS {
		String WAIT_OUT = "0";
		String OUT = "1";
		String CANCEL = "2";
	}
	
	/**
	 * 平台比例类型：0-金币兑换比例;1-提现手续费
	 */
	public interface COST_RATE_TYPE {
		String COIN_RATE = "0";
		String WITHDRAW_RATE = "1";
	}
	
	/**
	 * 状态 0-未处理；1-已处理
	 */
	public interface USER_PRODUCT_STATUS {
		String UN_HANDLE = "0";
		String HANDLED = "1";
	}
	
	/**
	 * 约单结果
	 */
	public interface INVITE_APPLY_RESULT {
		String SUCCESS = "success";
		String FAIL = "fail";
	}
	
	/**
	 * 通知业务类型
	 * 0-选择拼单用户通知
	 */
	public interface NOTIFICATION_TYPE {
		String INVITE_CHOOSE_NOTIFY = "0";
	}
	
	/**
	 * 苹果内购验证服务地址
	 * @author steven
	 *
	 */
	public interface APPLE_IAP_URL {
		String SANDBOX = "https://sandbox.itunes.apple.com/verifyReceipt";
		String PRODUCTION = "https://buy.itunes.apple.com/verifyReceipt";
	}
	
	/**
	 * 苹果内购环境
	 * @author steven
	 *
	 */
	public interface APPLE_IAP_ENV {
		int SANDBOX = 0;
		int PRODUCTION = 1;
	}
	
	/**
	 * 举报类型
	 * @author steven
	 *
	 */
	public interface REPORT_TYPE {
		String TWEET = "0";
		String NOTE = "1";
	}
}
