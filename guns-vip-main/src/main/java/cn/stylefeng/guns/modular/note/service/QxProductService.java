package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxProduct;
import cn.stylefeng.guns.modular.note.model.params.QxProductParam;
import cn.stylefeng.guns.modular.note.model.result.QxProductResult;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author
 * @since 2019-11-18
 */
public interface QxProductService extends IService<QxProduct> {

	/**
	 * 新增
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	void add(QxProductParam param);

	/**
	 * 删除
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	void delete(QxProductParam param);

	/**
	 * 更新
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	void update(QxProductParam param);

	/**
	 * 查询单条数据，Specification模式
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	QxProductResult findBySpec(QxProductParam param);

	/**
	 * 查询列表，Specification模式
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	List<QxProductResult> findListBySpec(QxProductParam param);

	/**
	 * 查询分页数据，Specification模式
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	LayuiPageInfo findPageBySpec(QxProductParam param);

	/**
	 * 兑换商品
	 * 
	 * @param requestUserId
	 * @param productId
	 * @param addressId 
	 */
	void exchange(Long userId, Long productId, Long addressId);

	/**
	 * 我的兑换商品
	 * @param page
	 * @param requestUserId
	 */
	Page myExchange(Page page, Long requestUserId);

}
