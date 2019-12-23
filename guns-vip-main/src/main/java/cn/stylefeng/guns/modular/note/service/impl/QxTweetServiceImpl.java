package cn.stylefeng.guns.modular.note.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.core.constant.ProjectConstants.USER_PAY_LOG_TYPE;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.modular.note.dto.QxPayResult;
import cn.stylefeng.guns.modular.note.entity.QxTweet;
import cn.stylefeng.guns.modular.note.entity.QxTweetLike;
import cn.stylefeng.guns.modular.note.mapper.QxTweetLikeMapper;
import cn.stylefeng.guns.modular.note.mapper.QxTweetMapper;
import cn.stylefeng.guns.modular.note.model.params.QxTweetParam;
import cn.stylefeng.guns.modular.note.model.result.QxTweetResult;
import cn.stylefeng.guns.modular.note.service.QxTweetService;
import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 推文表 服务实现类
 * </p>
 *
 * @author
 * @since 2019-11-18
 */
@Service
public class QxTweetServiceImpl extends ServiceImpl<QxTweetMapper, QxTweet> implements QxTweetService {

	@Resource
	private QxCoinHelper qxCoinHelper;

	@Resource
	private QxPayLogHelper qxPayLogHelper;
	
	@Resource
	private QxTweetLikeMapper qxTweetLikeMapper;
	
	@Override
	public void add(QxTweetParam param) {
		QxTweet entity = getEntity(param);
		this.save(entity);
	}

	@Override
	public void delete(QxTweetParam param) {
		this.removeById(getKey(param));
	}

	@Override
	public void update(QxTweetParam param) {
		QxTweet oldEntity = getOldEntity(param);
		QxTweet newEntity = getEntity(param);
		ToolUtil.copyProperties(newEntity, oldEntity);
		this.updateById(newEntity);
	}

	@Override
	public QxTweetResult findBySpec(QxTweetParam param) {
		return null;
	}

	@Override
	public List<QxTweetResult> findListBySpec(QxTweetParam param) {
		return null;
	}

	@Override
	public LayuiPageInfo findPageBySpec(QxTweetParam param) {
		Page pageContext = getPageContext();
		IPage page = this.baseMapper.customPageList(pageContext, param);
		return LayuiPageFactory.createPageInfo(page);
	}

	private Serializable getKey(QxTweetParam param) {
		return param.getId();
	}

	private Page getPageContext() {
		return LayuiPageFactory.defaultPage();
	}

	private QxTweet getOldEntity(QxTweetParam param) {
		return this.getById(getKey(param));
	}

	private QxTweet getEntity(QxTweetParam param) {
		QxTweet entity = new QxTweet();
		ToolUtil.copyProperties(param, entity);
		return entity;
	}

	@Override
	public Page<List<QxTweet>> followList(Page page, Long userId) {
		return this.baseMapper.followList(page, userId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void rewardTweet(Long requestUserId, Long userId, Long tweetId, Long giftId) {
		QxPayResult payResult = qxCoinHelper.payCoin(requestUserId, userId, giftId);
		qxPayLogHelper.createPayLog(payResult.getPayerId(), payResult.getPrice(), USER_PAY_LOG_TYPE.REWARD_OUT);
		qxPayLogHelper.createPayLog(payResult.getPayeeId(), payResult.getPrice(), USER_PAY_LOG_TYPE.REWARD_IN);
		qxPayLogHelper.rewardTweetLog(userId, tweetId, giftId);
		QxTweet tweet = this.getById(tweetId);
		tweet.setGiftCount(tweet.getGiftCount() + 1);
		this.updateById(tweet);
	}

	@Override
	public void like(Long requestUserId, Long id) {
		QueryWrapper<QxTweetLike> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("tweet_id", id).eq("created_by", requestUserId);
		int count = qxTweetLikeMapper.selectCount(queryWrapper);
		if (count > 0) {
			throw new ServiceException("不能重复点赞");
		}
		QxTweetLike like = new QxTweetLike();
		like.setCreatedBy(requestUserId);
		like.setTweetId(id);
		qxTweetLikeMapper.insert(like);
		
		QxTweet tweet = this.getById(id);
		tweet.setFavoriteCount(tweet.getFavoriteCount()+1);
		this.updateById(tweet);
	}

	@Override
	public Page rewardUsers(Page page, Long tweetId) {
		return this.baseMapper.rewardUsers(page, tweetId);
	}
}
