package cn.stylefeng.guns.modular.note.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.stylefeng.guns.core.DateUtils;
import cn.stylefeng.guns.modular.note.service.impl.QxDataHelper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;

@Controller
@RequestMapping("/qxData")
public class QxDataController extends BaseController {

	private String PREFIX = "/note/qxData";
	
	@Autowired
	private QxDataHelper qxDataHelper;
	
	/**
	 * 跳转到页面
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "/qxData.html";
	}
	
	/**
	 * 平台总额
	 * @return
	 */
	@RequestMapping("/totalAmount")
	@ResponseBody
	public ResponseData totalAmount() {
		BigDecimal totalAmount = qxDataHelper.getTotalAmount();
		return ResponseData.success(totalAmount);
	}
	
	/**
	 * 月充值总额
	 */
	@RequestMapping("/totalMonthCharge")
	@ResponseBody
	public ResponseData totalMonthCharge() {
		Date today = new Date();
		Date startDate = DateUtils.getFirstDayOfMonth(today);
		Date endDate = DateUtils.getLastDayOfMonth(today);
		BigDecimal totalCharge = qxDataHelper.getTotalCharge(startDate, endDate);
		return ResponseData.success(totalCharge);
	}
	/**
	 * 可提现金额
	 */
	@RequestMapping("/canWithdraw")
	@ResponseBody
	public ResponseData canWithdraw() {
		BigDecimal canWithdraw = qxDataHelper.canWithdraw();
		return ResponseData.success(canWithdraw);
	}
}
