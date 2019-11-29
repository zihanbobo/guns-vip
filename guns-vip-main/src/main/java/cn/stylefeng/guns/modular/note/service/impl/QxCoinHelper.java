package cn.stylefeng.guns.modular.note.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.stylefeng.guns.core.constant.ProjectConstants.USER_PAY_LOG_TYPE;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.modular.note.entity.QxGift;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.mapper.QxGiftMapper;
import cn.stylefeng.guns.modular.note.mapper.QxUserMapper;

@Component
public class QxCoinHelper {

	@Resource
	private QxGiftMapper qxGiftMapper;

	@Resource
	private QxUserMapper qxUserMapper;

	@Resource
	private QxPayLogHelper qxPayLogHelper;

	public void payCoin(Long payerId, Long apyeeId, Long giftId) {
		// 检查用户金币是否足够打赏
		QxGift gift = qxGiftMapper.selectById(giftId);
		QxUser payUser = qxUserMapper.selectById(payerId);
		if (payUser.getFreeze() < gift.getPrice()) {
			throw new ServiceException("金币余额不足，请先充值");
		}
		// 金币转账到对方账户
		Integer giftPrice = gift.getPrice();
		QxUser payeeUser = qxUserMapper.selectById(apyeeId);
		payUser.setFreeze(payUser.getFreeze() - giftPrice);
		payeeUser.setBalance(payeeUser.getBalance() + giftPrice);
		qxUserMapper.updateById(payUser);
		qxUserMapper.updateById(payeeUser);
		// 记录流水日志
		qxPayLogHelper.createPayLog(payUser.getId(), giftPrice, USER_PAY_LOG_TYPE.REWARD_OUT);
		qxPayLogHelper.createPayLog(payeeUser.getId(), giftPrice, USER_PAY_LOG_TYPE.REWARD_IN);
	}
}
