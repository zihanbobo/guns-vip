package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxGift;
import cn.stylefeng.guns.modular.note.model.params.QxGiftParam;
import cn.stylefeng.guns.modular.note.model.result.QxGiftResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 礼物表 服务类
 * </p>
 *
 * @author
 * @since 2019-11-18
 */
public interface QxGiftService extends IService<QxGift> {

	/**
	 * 新增
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	void add(QxGiftParam param);

	/**
	 * 删除
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	void delete(QxGiftParam param);

	/**
	 * 更新
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	void update(QxGiftParam param);

	/**
	 * 查询单条数据，Specification模式
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	QxGiftResult findBySpec(QxGiftParam param);

	/**
	 * 查询列表，Specification模式
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	List<QxGiftResult> findListBySpec(QxGiftParam param);

	/**
	 * 查询分页数据，Specification模式
	 *
	 * @author
	 * @Date 2019-11-18
	 */
	LayuiPageInfo findPageBySpec(QxGiftParam param);

	/**
	 * 打赏
	 * 
	 * @param requestUserId
	 * @param userId
	 * @param tweetId
	 * @param giftId
	 */
	void rewardTweet(Long requestUserId, Long userId, Long tweetId, Long giftId);

}
