package cn.stylefeng.guns.modular.note.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.entity.QxUserSocial;
import cn.stylefeng.guns.modular.note.model.params.QxUserParam;
import cn.stylefeng.guns.modular.note.model.result.QxUserResult;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author
 * @since 2019-11-14
 */
public interface QxUserService extends IService<QxUser> {

	/**
	 * 新增
	 *
	 * @author
	 * @Date 2019-11-14
	 */
	void add(QxUserParam param);

	/**
	 * 删除
	 *
	 * @author
	 * @Date 2019-11-14
	 */
	void delete(QxUserParam param);

	/**
	 * 更新
	 *
	 * @author
	 * @Date 2019-11-14
	 */
	void update(QxUserParam param);

	/**
	 * 查询单条数据，Specification模式
	 *
	 * @author
	 * @Date 2019-11-14
	 */
	QxUserResult findBySpec(QxUserParam param);

	/**
	 * 查询列表，Specification模式
	 *
	 * @author
	 * @Date 2019-11-14
	 */
	List<QxUserResult> findListBySpec(QxUserParam param);

	/**
	 * 查询分页数据，Specification模式
	 *
	 * @author
	 * @Date 2019-11-14
	 */
	LayuiPageInfo findPageBySpec(QxUserParam param);

	/**
	 * 通过账号获取用户
	 * 
	 * @param account
	 * @return
	 */
	QxUser getUserByAccount(String account);

	/**
	 * 获得商品已兑换的用户列表
	 * 
	 * @param page
	 * @param id
	 * @return
	 */
	List<QxUser> getProductUsers(Page page, Long id);

	/**
	 * 根据unionId获取用户
	 * @param appId
	 * @param unionId
	 * @return
	 */
	QxUser getUserByUnionId(String appId, String unionId);

	/**
	 * 根据手机号创建用户
	 * @param mobile
	 * @return
	 */
	QxUser performRegister(String mobile);

	/**
	 * 绑定用户微信
	 * @param mobile
	 * @param openId
	 * @param unionId
	 * @return
	 */
	QxUser wxBindUser(String mobile, String appId, String openId, String unionId);

	/**
	 * 根据用户ID、appID获取用户三方信息
	 * @param requestUserId
	 * @param appId
	 * @return
	 */
	QxUserSocial getUserSocialByAppId(Long userId, String appId);

	/**
	 * 根据邀请码获取用户
	 * @param parentInviteCode
	 * @return
	 */
	QxUser getUserByInviteCode(String parentInviteCode);
	
	/**
	 * 根据openId获取用户
	 * @param appId
	 * @param openId
	 * @return
	 */
	QxUser getUserByOpenId(String appId, String openId);
}
