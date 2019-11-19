package cn.stylefeng.guns.core.constant;

/**
 * 程序常量配置
 * 
 * @author steven
 *
 */
public interface ProjectConstants {

	/**
	 * 用户 token
	 */
	public interface TOKEN {
		String ADMIN = "ADMIN_";
		String USER = "USER_";
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
}
