package cn.stylefeng.guns.modular.note.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.service.WxPayService;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.core.constant.ProjectConstants.COIN_ORDER_STATUS;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.modular.note.dto.QxPackageTo;
import cn.stylefeng.guns.modular.note.dvo.QxPayLogVo;
import cn.stylefeng.guns.modular.note.entity.QxCoinOrder;
import cn.stylefeng.guns.modular.note.entity.QxPayLog;
import cn.stylefeng.guns.modular.note.service.QxCoinOrderService;
import cn.stylefeng.guns.modular.note.service.QxPayLogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/finance")
public class ApiFinanceController extends ApiBaseController {

	private WxPayService wxPayService;
	
	@Resource
	private QxCoinOrderService qxCoinOrderService;
	
	@Resource
	private QxPayLogService qxPayLogService;
	
	/**
	 * 微信购买金币
	 * @param packageTo
	 * @return
	 */
	@PostMapping("/wx/pay")
	public Object wxPay(QxPackageTo packageTo) {
		try {
			// 创建订单
			QxCoinOrder coinOrder = qxCoinOrderService.createOrder(getRequestUserId(), packageTo.getId());
			WxPayUnifiedOrderRequest request = createWxPayOrderRequest(packageTo.getTradeType(), coinOrder);
			log.info("/api/finance/wx/pay");
			return ResultGenerator.genSuccessResult(wxPayService.createOrder(request));
		} catch (Exception e) {
			log.error("/api/finance/wx/pay, packageTo=" + packageTo + ", error=" + e.getMessage());
			throw new ServiceException("微信支付失败，请联系管理员");
		}
	}
	
	public WxPayUnifiedOrderRequest createWxPayOrderRequest(String tradeType, QxCoinOrder coinOrder) {
		WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
		orderRequest.setBody("金币充值");
		orderRequest.setOutTradeNo(coinOrder.getSn());
		orderRequest.setTotalFee(BaseWxPayRequest.yuanToFen(coinOrder.getAmount().toString()));
		orderRequest.setTradeType(tradeType);
		return orderRequest;
	}
	
	@PostMapping("/wx/payNotify")
	public String wxPayNotify(HttpServletRequest request, HttpServletResponse response) {
		try {
			String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
			WxPayOrderNotifyResult result = wxPayService.parseOrderNotifyResult(xmlResult);
			// 判断订单信息是否正确
			String orderNo = result.getOutTradeNo();
			String totalFee = BaseWxPayResult.fenToYuan(result.getTotalFee());
			QueryWrapper<QxCoinOrder> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("sn", orderNo).eq("amount", new BigDecimal(totalFee));
			QxCoinOrder coinOrder = qxCoinOrderService.getOne(queryWrapper);
			if (coinOrder == null) {
				log.error("订单记录不存在, /api/finance/wx/payNotify, orderNo=" + orderNo + ", amount=" + totalFee);
				throw new Exception("订单记录不存在");
			}
			coinOrder.setStatus(COIN_ORDER_STATUS.PAID);
			qxCoinOrderService.updateById(coinOrder);
			return WxPayNotifyResponse.success("处理成功");
		} catch (Exception e) {
			log.error("/api/finance/wx/payNotify, error=" + e.getMessage());
			return WxPayNotifyResponse.fail(e.getMessage());
		}
	}
	
	@PostMapping("/withdraw")
	public Object withdraw() {
		// TODO: 提现操作
		log.info("/api/finance/withdraw");
		return ResultGenerator.genSuccessResult();
	}
	
	@PostMapping("/log")
	public Object log() {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxPayLog> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", getRequestUserId()).orderByDesc("created_time");
		qxPayLogService.page(page, queryWrapper);
		List<QxPayLogVo> vos =  createPayLogVos(page.getRecords());
		page.setRecords(vos);
		return ResultGenerator.genSuccessResult(page);
	}
	
	public List<QxPayLogVo> createPayLogVos(List<QxPayLog> list) {
		List<QxPayLogVo> vos = new ArrayList<>();
		for (QxPayLog log : list) {
			QxPayLogVo vo = new QxPayLogVo();
			BeanUtils.copyProperties(log, vo);
			vos.add(vo);
		}
		return vos;
	}
}
