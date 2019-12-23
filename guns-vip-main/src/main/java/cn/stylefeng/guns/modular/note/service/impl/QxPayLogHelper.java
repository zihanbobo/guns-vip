package cn.stylefeng.guns.modular.note.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.stylefeng.guns.modular.note.entity.QxGift;
import cn.stylefeng.guns.modular.note.entity.QxNoteReward;
import cn.stylefeng.guns.modular.note.entity.QxPayLog;
import cn.stylefeng.guns.modular.note.entity.QxTweetReward;
import cn.stylefeng.guns.modular.note.mapper.QxGiftMapper;
import cn.stylefeng.guns.modular.note.mapper.QxNoteRewardMapper;
import cn.stylefeng.guns.modular.note.mapper.QxPayLogMapper;
import cn.stylefeng.guns.modular.note.mapper.QxTweetRewardMapper;

@Component
public class QxPayLogHelper {

	@Resource
	private QxPayLogMapper qxPayLogMapper;
	
	@Resource
	private QxTweetRewardMapper qxTweetRewardMapper;
	
	@Resource
	private QxNoteRewardMapper qxNoteRewardMapper;
	
	@Resource
	private QxGiftMapper qxGiftMapper;
	
	/**
	 * 支付日志
	 * @param userId
	 * @param amount
	 * @param type
	 */
	public void createPayLog(Long userId, Integer amount, String type) {
		QxPayLog entity = new QxPayLog();
		entity.setUserId(userId);
		entity.setAmount(amount);
		entity.setType(type);
		qxPayLogMapper.insert(entity);
	}
	
	/**
	 * 记录推文打赏记录
	 * @param userId
	 * @param tweetId
	 * @param gift
	 */
	public void rewardTweetLog(Long userId, Long tweetId, Long giftId) {
		QxGift gift = qxGiftMapper.selectById(giftId);
		QxTweetReward entity = new QxTweetReward();
		entity.setUserId(userId);
		entity.setTweetId(tweetId);
		entity.setGiftName(gift.getName());
		entity.setGiftPrice(gift.getPrice());
		entity.setGiftImage(gift.getImage());
		qxTweetRewardMapper.insert(entity);
	}
	
	/**
	 * 记录日记记录
	 * @param userId
	 * @param noteId
	 * @param gift
	 */
	public void rewardNoteLog(Long userId, Long noteId, Long giftId) {
		QxGift gift = qxGiftMapper.selectById(giftId);
		QxNoteReward entity = new QxNoteReward();
		entity.setUserId(userId);
		entity.setNoteId(noteId);
		entity.setGiftName(gift.getName());
		entity.setGiftPrice(gift.getPrice());
		entity.setGiftImage(gift.getImage());
		qxNoteRewardMapper.insert(entity);
	}
}
