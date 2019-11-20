package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.dto.QxAddressTo;
import cn.stylefeng.guns.modular.note.entity.QxAddress;
import cn.stylefeng.guns.modular.note.model.params.QxAddressParam;
import cn.stylefeng.guns.modular.note.model.result.QxAddressResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 收货地址表 服务类
 * </p>
 *
 * @author
 * @since 2019-11-18
 */
public interface QxAddressService extends IService<QxAddress> {

	/**
	 * 新增
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	void add(QxAddressParam param);

	/**
	 * 删除
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	void delete(QxAddressParam param);

	/**
	 * 更新
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	void update(QxAddressParam param);

	/**
	 * 查询单条数据，Specification模式
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	QxAddressResult findBySpec(QxAddressParam param);

	/**
	 * 查询列表，Specification模式
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	List<QxAddressResult> findListBySpec(QxAddressParam param);

	/**
	 * 查询分页数据，Specification模式
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	LayuiPageInfo findPageBySpec(QxAddressParam param);

	/**
	 * 更新地址
	 * @param userId 
	 * 
	 * @param addressTo
	 */
	void updateAddress(Long userId, QxAddressTo addressTo);

	/**
	 * 删除地址
	 * @param id
	 */
	void deleteAddress(Long id);

	/**
	 * 添加地址
	 * @param userId
	 * @param addressTo
	 */
	void addAddress(Long userId, QxAddressTo addressTo);

}
