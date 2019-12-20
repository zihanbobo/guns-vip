package cn.stylefeng.guns.modular.note.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.stylefeng.guns.modular.note.mapper.QxCoinOrderMapper;
import cn.stylefeng.guns.modular.note.mapper.QxUserMapper;
import cn.stylefeng.guns.modular.note.mapper.QxWithdrawLogMapper;

@Component
public class QxDataHelper {

	@Resource
	private QxCoinOrderMapper qxCoinOrderMapper;
	
	@Resource
	private QxWithdrawLogMapper qxWithdrawLogMapper;
	
	@Resource
	private QxUserMapper qxUserMapper;
	
	@Resource
	private QxCoinHelper qxCoinHelper;
	
	/**
	 * 平台总额
	 * @return
	 */
	public BigDecimal getTotalAmount() {
		BigDecimal totalCharge = qxCoinOrderMapper.getTotalCharge();
		BigDecimal totalWithdraw = qxWithdrawLogMapper.getTotalWithdraw();
		return totalCharge.subtract(totalWithdraw.setScale(2));
	}

	public BigDecimal canWithdraw() {
		Integer totalBalance = qxUserMapper.getTotalBalance();
		return qxCoinHelper.caculateWithdrawAmount(totalBalance);
	}
}
