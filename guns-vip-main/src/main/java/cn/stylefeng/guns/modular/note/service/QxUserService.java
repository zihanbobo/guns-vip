package cn.stylefeng.guns.modular.note.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxUser;
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
	 * @param unionId
	 * @return
	 */
	QxUser getUserByUnionId(String unionId);
}
