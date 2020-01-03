package cn.stylefeng.guns.modular.note.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.stylefeng.guns.core.CommonUtils;
import cn.stylefeng.guns.core.constant.ProjectConstants.COST_RATE_TYPE;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.modular.note.dto.QxPayResult;
import cn.stylefeng.guns.modular.note.entity.QxCostRate;
import cn.stylefeng.guns.modular.note.entity.QxGift;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.mapper.QxCostRateMapper;
import cn.stylefeng.guns.modular.note.mapper.QxGiftMapper;
import cn.stylefeng.guns.modular.note.mapper.QxUserMapper;

@Component
public class QxCoinHelper {

	@Resource
	private QxGiftMapper qxGiftMapper;

	@Resource
	private QxUserMapper qxUserMapper;
	
	@Resource
	private QxCostRateMapper qxCostRateMapper;

	@Resource
	private QxPayLogHelper qxPayLogHelper;

	public QxPayResult payCoin(Long payerId, Long payeeId, Long giftId) {
		// 检查用户金币是否足够打赏
		QxGift gift = qxGiftMapper.selectById(giftId);
		QxUser payUser = qxUserMapper.selectById(payerId);
		if (payUser.getFreeze() < gift.getPrice()) {
			throw new ServiceException("金币余额不足，请先充值");
		}
		// 金币转账到对方账户
		Integer giftPrice = gift.getPrice();
		QxUser payeeUser = qxUserMapper.selectById(payeeId);
		payUser.setFreeze(payUser.getFreeze() - giftPrice);
		payeeUser.setBalance(payeeUser.getBalance() + giftPrice);
		qxUserMapper.updateById(payUser);
		qxUserMapper.updateById(payeeUser);
		
		QxPayResult payResult = new QxPayResult();
		payResult.setSn(CommonUtils.getSerialNumber());
		payResult.setPayerId(payerId);
		payResult.setPayeeId(payeeId);
		payResult.setPrice(giftPrice);
		return payResult;
	}
	
	/**
	 * 货币和现金转换
	 * @param coinCount
	 * @return
	 */
	public BigDecimal caculateWithdrawAmount(int coinCount) {
		BigDecimal withdrawRate = getRateByType(COST_RATE_TYPE.WITHDRAW_RATE);
		BigDecimal coinRate = getRateByType(COST_RATE_TYPE.COIN_RATE);
		BigDecimal realAmount = new BigDecimal(coinCount).multiply(coinRate); // 金币兑换成现金
		BigDecimal withdrawAmount = (realAmount.multiply(BigDecimal.ONE.subtract(withdrawRate))).setScale(0, BigDecimal.ROUND_DOWN);
		if (withdrawAmount.compareTo(BigDecimal.ONE) < 0) {
			throw new ServiceException("提现金额必须大于1元");
		}
		return withdrawAmount;
	}
	
	BigDecimal getRateByType(String type) {
		QueryWrapper<QxCostRate> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("type", type);
		QxCostRate costRate = qxCostRateMapper.selectOne(queryWrapper);
		return costRate.getRate();
	}
}
