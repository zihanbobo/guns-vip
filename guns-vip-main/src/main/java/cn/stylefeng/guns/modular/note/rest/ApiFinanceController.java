package cn.stylefeng.guns.modular.note.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayFundTransUniTransferModel;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.Participant;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransUniTransferRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.binarywang.wxpay.bean.entpay.EntPayRequest;
import com.github.binarywang.wxpay.bean.entpay.EntPayResult;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.constant.WxPayConstants.CheckNameOption;
import com.github.binarywang.wxpay.constant.WxPayConstants.ResultCode;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.EntPayService;
import com.github.binarywang.wxpay.service.WxPayService;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.core.alipay.AlipayProperties;
import cn.stylefeng.guns.core.constant.ProjectConstants.COIN_ORDER_STATUS;
import cn.stylefeng.guns.core.constant.ProjectConstants.WITHDRAW_STATUS;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.modular.note.dto.QxPackageTo;
import cn.stylefeng.guns.modular.note.dvo.QxPayLogVo;
import cn.stylefeng.guns.modular.note.entity.QxCoinOrder;
import cn.stylefeng.guns.modular.note.entity.QxPackage;
import cn.stylefeng.guns.modular.note.entity.QxPayLog;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.entity.QxUserSocial;
import cn.stylefeng.guns.modular.note.entity.QxWithdrawLog;
import cn.stylefeng.guns.modular.note.service.QxCoinOrderService;
import cn.stylefeng.guns.modular.note.service.QxPayLogService;
import cn.stylefeng.guns.modular.note.service.QxWithdrawLogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/finance")
public class ApiFinanceController extends ApiBaseController {

	private WxPayService wxPayService;

	@Resource
	private AlipayProperties alipayProperties;

	@Resource
	private QxCoinOrderService qxCoinOrderService;

	@Resource
	private QxPayLogService qxPayLogService;
	
	@Resource
	private QxWithdrawLogService qxWithdrawLogService;

	/**
	 * 微信购买金币
	 * 
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
	
	/**
	 * 金币和费用转化
	 * @param coinCount
	 * @return
	 */
	public BigDecimal getTranAmount(int coinCount) {
		// TODO: 汇率设置，平台手续费扣除
		return new BigDecimal(coinCount);
	}
	
	public void checkWithdrawLimit(QxUser user, int coinCount) {
		if (user.getBalance() < coinCount) {
			throw new ServiceException("提现金币不能超过金币余额");
		}
	}

	@PostMapping("/wx/payNotify")
	public String wxPayNotify(HttpServletRequest request, HttpServletResponse response) {
		try {
			String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
			WxPayOrderNotifyResult result = wxPayService.parseOrderNotifyResult(xmlResult);
			// 判断订单信息是否正确
			String orderNo = result.getOutTradeNo();
			String totalFee = BaseWxPayResult.fenToYuan(result.getTotalFee());

			QxCoinOrder coinOrder = getCoinOrder(orderNo, new BigDecimal(totalFee), COIN_ORDER_STATUS.WAIT_PAY);
			if (coinOrder == null) {
				log.error("订单记录不存在, /api/finance/wx/payNotify, orderNo=" + orderNo + ", amount=" + totalFee);
				throw new ServiceException("订单记录不存在");
			}
			coinOrder.setStatus(COIN_ORDER_STATUS.PAID);
			qxCoinOrderService.updateById(coinOrder);
			return WxPayNotifyResponse.success("处理成功");
		} catch (Exception e) {
			log.error("/api/finance/wx/payNotify, error=" + e.getMessage());
			return WxPayNotifyResponse.fail(e.getMessage());
		}
	}

	public QxCoinOrder getCoinOrder(String orderNo, BigDecimal totalFee, String status) {
		QueryWrapper<QxCoinOrder> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sn", orderNo).eq("amount", totalFee).eq("status", status);
		return qxCoinOrderService.getOne(queryWrapper);
	}
	
	@PostMapping("/wx/withdraw")
	public Object wxWithdraw(String appId, int coinCount) {
		QxUser user = getUser();
		checkWithdrawLimit(user, coinCount);
		BigDecimal amount = getTranAmount(coinCount);
		QxUserSocial userSocial = qxUserService.getUserSocialByAppId(getRequestUserId(), appId);
		QxWithdrawLog withdrawLog = qxWithdrawLogService.createWithdrawLog(userSocial, amount);
		EntPayService entPayService = wxPayService.getEntPayService();
		EntPayRequest request = new EntPayRequest();
		request.setPartnerTradeNo(withdrawLog.getSn());
		request.setOpenid(withdrawLog.getPayeeAccount());
		request.setCheckName(CheckNameOption.NO_CHECK);
		request.setAmount(BaseWxPayRequest.yuanToFen(amount.toString()));
		request.setDescription("用户提现");
		request.setSpbillCreateIp("192.168.0.1");
		try {
			EntPayResult entPayResult = entPayService.entPay(request);
			if (ResultCode.SUCCESS.equals(entPayResult.getReturnCode()) && ResultCode.SUCCESS.equals(entPayResult.getResultCode())) {
				// 更新提现状态
				withdrawLog.setStatus(WITHDRAW_STATUS.OUT);
				qxWithdrawLogService.updateById(withdrawLog);
				// 更新用户金币余额
				user.setBalance(user.getBalance()-coinCount);
				qxUserService.updateById(user);
				log.info("/api/finance/wx/withdraw");
				return ResultGenerator.genSuccessResult();
			} else {
				log.error(entPayResult.getReturnMsg() + ", /api/finance/wx/withdraw, appId=" + appId + ", amount=" + amount);
				throw new ServiceException(entPayResult.getReturnMsg());
			}
		} catch (WxPayException e) {
			log.error("微信提现出错, error=" + e.getMessage());
			throw new ServiceException("微信提现出错");
		}
	}
	
	/**
	 * 支付宝购买金币
	 * 
	 * @return
	 */
	@PostMapping("/alipay/pay")
	public Object alipay(QxPackage packageTo) {
		try {
			// 创建订单
			QxCoinOrder coinOrder = qxCoinOrderService.createOrder(getRequestUserId(), packageTo.getId());
			AlipayClient alipayClient = new DefaultAlipayClient(alipayProperties.getGatewayUrl(),
					alipayProperties.getAppID(), alipayProperties.getMerchantPrivateKey(), alipayProperties.getFormat(),
					alipayProperties.getCharset(), alipayProperties.getAlipayPublicKey(),
					alipayProperties.getSignType());
			AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
			model.setSubject("金币充值");
			model.setOutTradeNo(coinOrder.getSn());
			model.setTotalAmount(coinOrder.toString());
			request.setBizModel(model);
			request.setNotifyUrl(alipayProperties.getNotifyUrl());
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			log.info("/api/finance/alipay/pay, packageTo=" + packageTo);
			return ResultGenerator.genSuccessResult(response.getBody());
		} catch (AlipayApiException e) {
			log.error("支付宝支付出错, /api/finance/alipay/pay, packageTo=" + packageTo + ", error=" + e.getMessage());
			throw new ServiceException("支付宝支付出错");
		}
	}

	/**
	 * 支付宝支付回调验证
	 * 
	 * @return
	 */
	@PostMapping("/alipay/payNotify")
	public void alipayNotify(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> params = new HashMap<>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		try {
			boolean flag = AlipaySignature.rsaCheckV1(params, alipayProperties.getAlipayPublicKey(),
					alipayProperties.getCharset(), alipayProperties.getSignType());
			if (!flag) {
				log.error("支付宝验证签名错误, params=" + params);
			}
			String outTradeNo = params.get("out_trade_no");
			BigDecimal tradePrice = new BigDecimal(request.getParameter("total_amount"));
			QxCoinOrder coinOrder = getCoinOrder(outTradeNo, tradePrice, COIN_ORDER_STATUS.WAIT_PAY);
			if (coinOrder == null) {
				log.error("订单记录不存在, /api/finance/wx/payNotify, orderNo=" + outTradeNo + ", amount=" + tradePrice);
				throw new ServiceException("订单记录不存在");
			}
			coinOrder.setStatus(COIN_ORDER_STATUS.PAID);
			qxCoinOrderService.updateById(coinOrder);
			response.getWriter().write("success");
		} catch (AlipayApiException e) {
			log.error("支付宝回调出错, /api/finance/alipay/payNotify, error=" + e.getMessage());
			throw new ServiceException("支付宝回调出错");
		} catch (IOException e) {
			log.error("响应, /api/finance/alipay/payNotify, error=" + e.getMessage());
			throw new ServiceException("响应出错");
		}
	}

	@PostMapping("/alipay/withdraw")
	public Object withdraw(String appId, String name, int coinCount) {
		QxUser user = getUser();
		checkWithdrawLimit(getUser(), coinCount);
		BigDecimal amount = getTranAmount(coinCount);
		QxUserSocial userSocial = qxUserService.getUserSocialByAppId(getRequestUserId(), appId);
		QxWithdrawLog withdrawLog = qxWithdrawLogService.createWithdrawLog(userSocial, amount);
		AlipayClient alipayClient = new DefaultAlipayClient(alipayProperties.getGatewayUrl(),
				alipayProperties.getAppID(), alipayProperties.getMerchantPrivateKey(), alipayProperties.getFormat(),
				alipayProperties.getCharset(), alipayProperties.getAlipayPublicKey(),
				alipayProperties.getSignType());
		AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
		AlipayFundTransUniTransferModel model = new AlipayFundTransUniTransferModel();
		Participant payeeInfo = new Participant();
		payeeInfo.setIdentity(userSocial.getOpenId());
		payeeInfo.setIdentityType("ALIPAY_USER_ID ");
		payeeInfo.setName(name);
		model.setOutBizNo(withdrawLog.getSn());
		model.setTransAmount(amount.toString());
		model.setProductCode("TRANS_ACCOUNT_NO_PWD");
		model.setBizScene("DIRECT_TRANSFER");
		model.setPayeeInfo(payeeInfo);
		model.setOrderTitle("用户提现");
		AlipayFundTransUniTransferResponse response;
		try {
			response = alipayClient.certificateExecute(request);
			if(response.isSuccess()){
				// 更新提现状态
				withdrawLog.setStatus(WITHDRAW_STATUS.OUT);
				qxWithdrawLogService.updateById(withdrawLog);
				// 更新用户金币余额
				user.setBalance(user.getBalance()-coinCount);
				qxUserService.updateById(user);
				log.info("/api/finance/withdraw");
				return ResultGenerator.genSuccessResult();
			} else {
				log.error("支付宝提现失败, error=" + response.getMsg());
				throw new ServiceException("支付宝提现失败");
			}
		} catch (AlipayApiException e) {
			log.error("支付宝提现失败, error=" + e.getMessage());
			throw new ServiceException("支付宝提现失败");
		}
	}

	@PostMapping("/log")
	public Object log() {
		Page page = LayuiPageFactory.defaultPage();
		QueryWrapper<QxPayLog> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", getRequestUserId()).orderByDesc("created_time");
		qxPayLogService.page(page, queryWrapper);
		List<QxPayLogVo> vos = createPayLogVos(page.getRecords());
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
