package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxCoinOrder;
import cn.stylefeng.guns.modular.note.model.params.QxCoinOrderParam;
import cn.stylefeng.guns.modular.note.model.result.QxCoinOrderResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户金币订单表 服务类
 * </p>
 *
 * @author
 * @since 2019-11-22
 */
public interface QxCoinOrderService extends IService<QxCoinOrder> {

	/**
	 * 新增
	 *
	 * @author
	 * @Date 2019-11-22
	 */
	void add(QxCoinOrderParam param);

	/**
	 * 删除
	 *
	 * @author
	 * @Date 2019-11-22
	 */
	void delete(QxCoinOrderParam param);

	/**
	 * 更新
	 *
	 * @author
	 * @Date 2019-11-22
	 */
	void update(QxCoinOrderParam param);

	/**
	 * 查询单条数据，Specification模式
	 *
	 * @author
	 * @Date 2019-11-22
	 */
	QxCoinOrderResult findBySpec(QxCoinOrderParam param);

	/**
	 * 查询列表，Specification模式
	 *
	 * @author
	 * @Date 2019-11-22
	 */
	List<QxCoinOrderResult> findListBySpec(QxCoinOrderParam param);

	/**
	 * 查询分页数据，Specification模式
	 *
	 * @author
	 * @Date 2019-11-22
	 */
	LayuiPageInfo findPageBySpec(QxCoinOrderParam param);

	/**
	 * 创建订单
	 * 
	 * @param requestUserId
	 * @param id
	 * @return
	 */
	QxCoinOrder createOrder(Long userId, Long packageId, String type);

}
