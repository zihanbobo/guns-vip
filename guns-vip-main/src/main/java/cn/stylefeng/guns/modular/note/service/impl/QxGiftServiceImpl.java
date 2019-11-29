package cn.stylefeng.guns.modular.note.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.core.constant.ProjectConstants.USER_PAY_LOG_TYPE;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.modular.note.entity.QxGift;
import cn.stylefeng.guns.modular.note.entity.QxNote;
import cn.stylefeng.guns.modular.note.entity.QxTweet;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.mapper.QxGiftMapper;
import cn.stylefeng.guns.modular.note.mapper.QxNoteMapper;
import cn.stylefeng.guns.modular.note.mapper.QxTweetMapper;
import cn.stylefeng.guns.modular.note.mapper.QxUserMapper;
import cn.stylefeng.guns.modular.note.model.params.QxGiftParam;
import cn.stylefeng.guns.modular.note.model.result.QxGiftResult;
import cn.stylefeng.guns.modular.note.service.QxGiftService;
import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 礼物表 服务实现类
 * </p>
 *
 * @author
 * @since 2019-11-18
 */
@Service
public class QxGiftServiceImpl extends ServiceImpl<QxGiftMapper, QxGift> implements QxGiftService {

	@Resource
	private QxGiftMapper qxGiftMapper;

	@Resource
	private QxUserMapper qxUserMapper;

	@Resource
	private QxPayLogHelper qxPayLogHelper;

	@Resource
	private QxTweetMapper qxTweetMapper;

	@Resource
	private QxNoteMapper qxNoteMapper;

	@Override
	public void add(QxGiftParam param) {
		QxGift entity = getEntity(param);
		this.save(entity);
	}

	@Override
	public void delete(QxGiftParam param) {
		this.removeById(getKey(param));
	}

	@Override
	public void update(QxGiftParam param) {
		QxGift oldEntity = getOldEntity(param);
		QxGift newEntity = getEntity(param);
		ToolUtil.copyProperties(newEntity, oldEntity);
		this.updateById(newEntity);
	}

	@Override
	public QxGiftResult findBySpec(QxGiftParam param) {
		return null;
	}

	@Override
	public List<QxGiftResult> findListBySpec(QxGiftParam param) {
		return null;
	}

	@Override
	public LayuiPageInfo findPageBySpec(QxGiftParam param) {
		Page pageContext = getPageContext();
		IPage page = this.baseMapper.customPageList(pageContext, param);
		return LayuiPageFactory.createPageInfo(page);
	}

	private Serializable getKey(QxGiftParam param) {
		return param.getId();
	}

	private Page getPageContext() {
		return LayuiPageFactory.defaultPage();
	}

	private QxGift getOldEntity(QxGiftParam param) {
		return this.getById(getKey(param));
	}

	private QxGift getEntity(QxGiftParam param) {
		QxGift entity = new QxGift();
		ToolUtil.copyProperties(param, entity);
		return entity;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void rewardTweet(Long requestUserId, Long userId, Long tweetId, Long giftId) {
		reward(requestUserId, userId, giftId);
		QxTweet tweet = qxTweetMapper.selectById(tweetId);
		tweet.setGiftCount(tweet.getGiftCount() + 1);
		qxTweetMapper.updateById(tweet);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void rewardNote(Long requestUserId, Long userId, Long noteId, Long giftId) {
		reward(requestUserId, userId, giftId);
		QxNote note = qxNoteMapper.selectById(noteId);
		note.setGiftCount(note.getGiftCount() + 1);
		qxNoteMapper.updateById(note);
	}

	public void reward(Long requestUserId, Long userId, Long giftId) {
		// 检查用户金币是否足够打赏
		QxGift gift = qxGiftMapper.selectById(giftId);
		QxUser currentUser = qxUserMapper.selectById(requestUserId);
		if (currentUser.getFreeze() < gift.getPrice()) {
			throw new ServiceException("用户金币余额不足，请先充值");
		}
		// 金币转账到对方账户
		Integer giftPrice = gift.getPrice();
		QxUser rewardUser = qxUserMapper.selectById(userId);
		currentUser.setFreeze(currentUser.getFreeze() - giftPrice);
		rewardUser.setBalance(rewardUser.getBalance() + giftPrice);
		qxUserMapper.updateById(currentUser);
		qxUserMapper.updateById(rewardUser);
		// 记录流水日志
		qxPayLogHelper.createPayLog(currentUser.getId(), giftPrice, USER_PAY_LOG_TYPE.REWARD_OUT);
		qxPayLogHelper.createPayLog(rewardUser.getId(), giftPrice, USER_PAY_LOG_TYPE.REWARD_IN);
	}

	@Override
	public void unlockNote(Long requestUserId, Long userId, Long noteId, Long giftId) {
		reward(requestUserId, userId, giftId);
		QxNote note = qxNoteMapper.selectById(noteId);
		note.setWatchCount(note.getWatchCount()+1);
		qxNoteMapper.updateById(note);
	}
}
