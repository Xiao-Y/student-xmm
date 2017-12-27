package org.billow.controller.pay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.GoodsDetail;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.apache.log4j.Logger;
import org.billow.api.orderForm.OrderFormDetailService;
import org.billow.api.orderForm.OrderFormService;
import org.billow.api.pay.PayService;
import org.billow.common.login.LoginHelper;
import org.billow.common.queues.OrderFormTaskQueue;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.OrderFormDetailDto;
import org.billow.model.expand.OrderFormDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.enumType.OrderFormTaskQueueEunm;
import org.billow.utils.enumType.PayStatusEunm;
import org.billow.utils.pay.alipay.AliPayApi;
import org.billow.utils.pay.alipay.AliPayApiConfig;
import org.billow.utils.pay.alipay.AliPayApiConfigKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 支付宝支付
 *
 * @author liuyongtao
 * @create 2017-11-28 20:36
 */
@Controller
@RequestMapping("/aliPay")
public class AliPay {

    private static final Logger logger = Logger.getLogger(AliPay.class);

    @Autowired
    private AliPayApiConfig aliPayApiConfig;
    @Autowired
    private OrderFormService orderFormService;
    @Autowired
    private OrderFormDetailService orderFormDetailService;
    @Resource(name = "alipayServiceImpl")
    private PayService alipayService;
    @Value("${system.domain.name}")
    private String systemDomainName;
    @Value("${shopping.mall.name}")
    private String shoppingMallName;

    /**
     * 打开支付支付页面
     */
    @RequestMapping("/openAliPayPage/{orderFormId}")
    public void openAliPayPage(HttpServletResponse response, HttpServletRequest request,
                               @PathVariable("orderFormId") String orderFormId) throws Exception {
        OrderFormDto orderFormDto = orderFormService.selectByPrimaryKey(new OrderFormDto(orderFormId));
        UserDto loginUser = LoginHelper.getLoginUser(request);
        if (orderFormDto != null) {
            //支付完成回调
            String returnUrl = systemDomainName + "/aliPay/returnResult";
            //String returnUrl = "http:localhost:8099/aliPay/returnResult";
            //支付结果的回调
            String notifyUrl = systemDomainName + "/aliPay/notifyResult";
            //String notifyUrl = "";
            //AlipayTradePayModel model = new AlipayTradePayModel();
            AlipayTradePagePayModel model = new AlipayTradePagePayModel();
            //商户订单号
            model.setOutTradeNo(orderFormId);
            //销售产品码,与支付宝签约的产品码名称。 注：目前仅支持FAST_INSTANT_TRADE_PAY
            model.setProductCode("FAST_INSTANT_TRADE_PAY");
            //订单总金额
            model.setTotalAmount(orderFormDto.getOrderformAmount().setScale(2).toString());
            OrderFormDetailDto orderFormDetailDto = new OrderFormDetailDto();
            orderFormDetailDto.setOrderFormId(orderFormId);
            List<OrderFormDetailDto> orderFormDetailDtos = orderFormDetailService.selectAll(orderFormDetailDto);
            //订单标题
            model.setSubject(shoppingMallName);
            if (orderFormDetailDtos.size() == 1) {
                OrderFormDetailDto detailDto = orderFormDetailDtos.get(0);
                //订单标题
                model.setSubject(detailDto.getCommodityName());
                //订单描述
                model.setBody(detailDto.getCommodityInfo());
            } else {
                //商品列表显示
                List<GoodsDetail> goodsDetails = new ArrayList<>();
                for (OrderFormDetailDto formDetailDto : orderFormDetailDtos) {
                    GoodsDetail detail = new GoodsDetail();
                    detail.setGoodsId(formDetailDto.getCommodityId());
                    detail.setGoodsName(formDetailDto.getCommodityName());
                    detail.setQuantity(new Long(formDetailDto.getCommodityNum()));
                    detail.setPrice(formDetailDto.getUnitPrice().toString());
                    detail.setBody(formDetailDto.getCommodityInfo());
                    goodsDetails.add(detail);
                }
                model.setGoodsDetail(goodsDetails);
            }
            //业务数据：用户信息
            Map<String, String> params = new HashMap<>();
            params.put("userId", loginUser.getUserId().toString());
            params.put("userName", loginUser.getUserName());
            model.setPassbackParams(URLEncoder.encode(JSON.toJSONString(params), "UTF-8"));
            AliPayApiConfigKit.putApiConfig(aliPayApiConfig.build());
            AliPayApi.tradePage(response, model, notifyUrl, returnUrl);
        }
    }

    /**
     [DEBUG][2017/11/29 21:43]###########################notifyResult start#########################################
     [DEBUG][2017/11/30 10:00]gmt_create:2017-11-30 10:00:18
     [DEBUG][2017/11/30 10:00]charset:UTF-8
     [DEBUG][2017/11/30 10:00]gmt_payment:2017-11-30 10:00:30
     [DEBUG][2017/11/30 10:00]notify_time:2017-11-30 10:00:32
     [DEBUG][2017/11/30 10:00]subject:OY特色水煮鱼
     [DEBUG][2017/11/30 10:00]buyer_id:2088102175139669
     [DEBUG][2017/11/30 10:00]passback_params:%7B%22userId%22%3A%22billow%22%7D
     [DEBUG][2017/11/30 10:00]invoice_amount:79.35
     [DEBUG][2017/11/30 10:00]version:1.0
     [DEBUG][2017/11/30 10:00]notify_id:0d2ee9eded503a558457b8378f89082l3e
     [DEBUG][2017/11/30 10:00]fund_bill_list:[{"amount":"79.35","fundChannel":"ALIPAYACCOUNT"}]
     [DEBUG][2017/11/30 10:00]notify_type:trade_status_sync
     [DEBUG][2017/11/30 10:00]out_trade_no:E20171130095858857000
     [DEBUG][2017/11/30 10:00]total_amount:79.35
     [DEBUG][2017/11/30 10:00]trade_status:TRADE_SUCCESS
     [DEBUG][2017/11/30 10:00]trade_no:2017113021001004660200620195
     [DEBUG][2017/11/30 10:00]auth_app_id:2016082500310007
     [DEBUG][2017/11/30 10:00]receipt_amount:79.35
     [DEBUG][2017/11/30 10:00]point_amount:0.00
     [DEBUG][2017/11/30 10:00]app_id:2016082500310007
     [DEBUG][2017/11/30 10:00]buyer_pay_amount:79.35
     [DEBUG][2017/11/30 10:00]seller_id:2088102172949100
     [DEBUG][2017/11/29 21:43]#############################notifyResult end#######################################
     */
    /**
     * 支付结果的异步回调
     *
     * @param response
     * @param request
     */
    @RequestMapping("/notifyResult")
    public void notifyResult(HttpServletResponse response, HttpServletRequest request) throws Exception {
        Map<String, String> paramsMap = AliPayApi.toMap(request);
        AliPayApiConfigKit.putApiConfig(aliPayApiConfig.build());
        AliPayApiConfig aliPayApiConfig = AliPayApiConfigKit.getAliPayApiConfig();
        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, aliPayApiConfig.getAlipayPublicKey(),
                aliPayApiConfig.getCharset(), aliPayApiConfig.getSignType());
        response.setContentType("text/html;charset=" + AliPayApiConfigKit.getAliPayApiConfig().getCharset());
        // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，
        // 校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
        logger.debug("###########################notifyResult start#########################################");
        if (signVerified) {
            //更新订单状态和保存支付宝返回的数据
            String info = JSON.toJSONString(paramsMap);
            paramsMap.put("info", info);
            alipayService.savePayData(paramsMap);
            try {
                //插入自动确认订单队列
                String typeCode = OrderFormTaskQueueEunm.ORDER_FORM_AUTO_CONFIRMATION.getTypeCode();
                String orderFormId = paramsMap.get("out_trade_no");
                new OrderFormTaskQueue(typeCode, orderFormId).putOrderFormTask();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("插入自动确认订单队列错误" + e);
            }
            response.getWriter().write("success");
        } else {
            // TODO 验签失败则记录异常日志，并在response中返回failure.
            response.getWriter().write("failure");
        }
        logger.debug("#############################notifyResult end#######################################");
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     [DEBUG][2017/11/29 21:43]###########################returnResult start#########################################
     [DEBUG][2017/11/30 10:00]charset:UTF-8
     [DEBUG][2017/11/30 10:00]out_trade_no:E20171130095858857000
     [DEBUG][2017/11/30 10:00]method:alipay.trade.page.pay.return
     [DEBUG][2017/11/30 10:00]total_amount:79.35
     [DEBUG][2017/11/30 10:00]trade_no:2017113021001004660200620195
     [DEBUG][2017/11/30 10:00]auth_app_id:2016082500310007
     [DEBUG][2017/11/30 10:00]app_id:2016082500310007
     [DEBUG][2017/11/30 10:00]version:1.0
     [DEBUG][2017/11/30 10:00]seller_id:2088102172949100
     [DEBUG][2017/11/30 10:00]timestamp:2017-11-30 10:00:38
     [DEBUG][2017/11/29 21:43]###########################returnResult end#########################################
     */
    /**
     * 支付结果的同步回调
     *
     * @param response
     * @param request
     */
    @RequestMapping("/returnResult")
    public String returnResult(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String result = "error";
        Map<String, String> paramsMap = AliPayApi.toMap(request);
        AliPayApiConfigKit.putApiConfig(aliPayApiConfig.build());
        AliPayApiConfig aliPayApiConfig = AliPayApiConfigKit.getAliPayApiConfig();
        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, aliPayApiConfig.getAlipayPublicKey(),
                aliPayApiConfig.getCharset(), aliPayApiConfig.getSignType());
        response.setContentType("text/html;charset=" + AliPayApiConfigKit.getAliPayApiConfig().getCharset());
        logger.debug("###########################returnResult start#########################################");
        if (signVerified) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                logger.debug(entry.getKey() + ":" + entry.getValue());
            }
            result = "success";
        }
        logger.debug("###########################returnResult end#########################################");
        return "redirect:/fg/fgHome/shoppingCart?result=" + result;
    }

    /**
     * 根据订单号查询是否支付成功
     *
     * @param orderFormId
     * @throws AlipayApiException
     */
    @Deprecated
    @RequestMapping("/isTradeQuery/{orderFormId}")
    public void isTradeQuery(@PathVariable("orderFormId") String orderFormId) throws AlipayApiException {
        AliPayApiConfigKit.putApiConfig(aliPayApiConfig.build());
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(orderFormId);
        boolean tradeQuery = AliPayApi.isTradeQuery(model);
        logger.debug(tradeQuery);
    }

    /**
     *
     {
     "body": "
     {
     \"alipay_trade_refund_response\":
     {
     \"code\":\"10000\",
     \"msg\":\"Success\",
     \"buyer_logon_id\":\"okn***@sandbox.com\",
     \"buyer_user_id\":\"2088102175139669\",
     \"fund_change\":\"Y\",
     \"gmt_refund_pay\":\"2017-12-06 17:08:47\",
     \"open_id\":\"20880081905521544845773010211066\",
     \"out_trade_no\":\"E20171206094554958001\",
     \"refund_fee\":\"26.31\",
     \"send_back_fee\":\"0.00\",
     \"trade_no\":\"2017120621001004660200622354\"
     },
     \"sign\":\"I0bq8qsPOpYbdHnI+3fkTeZ0Y62ReKN+/BEMeAeAH8BGub4VYJcVq7MAbk1yc0KEwt+1gzeNT5ovMB9spJuER1edkL5Y2NjP39zUnJNnud0NtT98fFN27THTqiyYs16x5jGIu2b+A6wc9QR/vIaPf2ADYm5kF1rssLX6pf1Ktw6K1dLyG97cbS/UUPPqdNif5MXkhMgrKytDY3fw7GMhojXZyCgWx0ER01nRNljSDB/XTAdQi6/5lHMCX27qLJKRplWUvQRoI/Nd20u+hoSzdVEDkdW1uMsz4XSSmZ9OdzcfA/ox4Is3twfg2BzMvRtsSxdLVlkQ9kfiToNpLXkgng==\"
     }",
     "buyerLogonId": "okn***@sandbox.com",
     "buyerUserId": "2088102175139669",
     "code": "10000",
     "errorCode": "10000",
     "fundChange": "Y",
     "gmtRefundPay": 1512551327000,
     "msg": "Success",
     "openId": "20880081905521544845773010211066",
     "outTradeNo": "E20171206094554958001",
     "params": {
     "biz_content": "{
     \"out_trade_no\":\"E20171206094554958001\",
     \"refund_amount\":\"26.31\",
     \"refund_reason\":\"正常退款\"
     }"
     },
     "refundFee": "26.31",
     "sendBackFee": "0.00",
     "success": true,
     "tradeNo": "2017120621001004660200622354"
     }
     */
    /**
     * 退款
     *
     * @param response
     * @param request
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/tradeRefund/{orderFormId}")
    public JsonResult tradeRefund(HttpServletResponse response, HttpServletRequest request,
                                  @PathVariable("orderFormId") String orderFormId) throws Exception {
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        try {
            OrderFormDto orderFormDto = orderFormService.selectByPrimaryKey(new OrderFormDto(orderFormId));
            AlipayTradeRefundModel model = new AlipayTradeRefundModel();
            model.setOutTradeNo(orderFormId);
            model.setRefundAmount(orderFormDto.getOrderformAmount().toString());
            model.setRefundReason("正常退款");

            AliPayApiConfigKit.putApiConfig(aliPayApiConfig.build());
            AlipayTradeRefundResponse alipayTradeRefundResponse = AliPayApi.tradeRefundToResponse(model);
            if (alipayTradeRefundResponse.isSuccess()) {
                Map<String, String> paramsMap = new HashMap<>();

                paramsMap.put("out_trade_no", alipayTradeRefundResponse.getOutTradeNo());//订单号
                paramsMap.put("trade_no", alipayTradeRefundResponse.getTradeNo());//支付宝交易号
                paramsMap.put("buyer_id", alipayTradeRefundResponse.getBuyerUserId());//买家支付宝号
                paramsMap.put("trade_status", PayStatusEunm.REFUND_SUCCESS.getNameCode());//支付状态
                //本次退款是否发生了资金变化
                if ("Y".equals(alipayTradeRefundResponse.getFundChange())) {
                    paramsMap.put("total_amount", "-" + alipayTradeRefundResponse.getRefundFee());//退回金额
                } else {
                    paramsMap.put("total_amount", "0.00");//退回金额
                }
                String info = JSON.toJSONString(alipayTradeRefundResponse);
                paramsMap.put("info", info);
                //更新订单状态和保存支付宝返回的数据
                alipayService.savePayData(paramsMap);
                message = PayStatusEunm.REFUND_SUCCESS.getName();
                type = MessageTipsCst.TYPE_SUCCES;
            } else {
                message = alipayTradeRefundResponse.getSubMsg();
                type = MessageTipsCst.TYPE_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = PayStatusEunm.REFUND_FAILURE.getName();
            type = MessageTipsCst.TYPE_ERROR;
        }
        json.setMessage(message);
        json.setType(type);
        return json;
    }
}
