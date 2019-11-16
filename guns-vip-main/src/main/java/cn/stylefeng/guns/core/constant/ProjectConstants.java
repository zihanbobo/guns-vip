package cn.stylefeng.guns.core.constant;

/**
 * 程序常量配置
 * 
 * @author steven
 *
 */
public interface ProjectConstants {

	/**
	 * 通知类型：0-短信，1-邮箱，2-消息；
	 */
	public interface NOTICE_TYPE {
		int SMS = 0;
		int EMAIL = 1;
		int PUSH = 2;
	}

	/**
	 * 消息读取状态
	 * 0-未读；1-已读
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
	 * @author steven
	 *
	 */
	public interface NOTICE_SEND_STATUS {
		int UNSEND = 0;
		int SEND = 1;
	}

	/**
	 * 手机验证码 0-登录/注册验证码
	 * 
	 * @author steven
	 *
	 */
	public interface SMS_CODE {
		int LOGIN_OR_REGISTER = 0;
	}
}
