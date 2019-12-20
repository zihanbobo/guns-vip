package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxPackage;
import cn.stylefeng.guns.modular.note.model.params.QxPackageParam;
import cn.stylefeng.guns.modular.note.model.result.QxPackageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 充值套餐 服务类
 * </p>
 *
 * @author
 * @since 2019-11-21
 */
public interface QxPackageService extends IService<QxPackage> {

	/**
	 * 新增
	 *
	 * @author
	 * @Date 2019-11-21
	 */
	void add(QxPackageParam param);

	/**
	 * 删除
	 *
	 * @author
	 * @Date 2019-11-21
	 */
	void delete(QxPackageParam param);

	/**
	 * 更新
	 *
	 * @author
	 * @Date 2019-11-21
	 */
	void update(QxPackageParam param);

	/**
	 * 查询单条数据，Specification模式
	 *
	 * @author
	 * @Date 2019-11-21
	 */
	QxPackageResult findBySpec(QxPackageParam param);

	/**
	 * 查询列表，Specification模式
	 *
	 * @author
	 * @Date 2019-11-21
	 */
	List<QxPackageResult> findListBySpec(QxPackageParam param);

	/**
	 * 查询分页数据，Specification模式
	 *
	 * @author
	 * @Date 2019-11-21
	 */
	LayuiPageInfo findPageBySpec(QxPackageParam param);

	/**
	 * 根据内购商品ID获得套餐
	 * 
	 * @param iapId
	 * @return
	 */
	QxPackage getByIapId(String iapId);

}
