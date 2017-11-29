package org.billow.controller.pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.internal.util.AlipaySignature;
import org.apache.log4j.Logger;
import org.billow.api.orderForm.OrderFormService;
import org.billow.common.login.LoginHelper;
import org.billow.controller.orderForm.ShoppingCartController;
import org.billow.model.expand.OrderFormDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.pay.alipay.AliPayApi;
import org.billow.utils.pay.alipay.AliPayApiConfig;
import org.billow.utils.pay.alipay.AliPayApiConfigKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Value("${system.domain.name}")
    private String systemDomainName;

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
            //String returnUrl = systemDomainName + "/aliPay/returnResult";
            String returnUrl = "http:localhost:8099/aliPay/returnResult";
            //支付结果的回调
            String notifyUrl = systemDomainName + "/aliPay/notifyResult";
            //String notifyUrl = "";
            AlipayTradePayModel model = new AlipayTradePayModel();
            //商户订单号
            model.setOutTradeNo(orderFormId);
            //销售产品码,与支付宝签约的产品码名称。 注：目前仅支持FAST_INSTANT_TRADE_PAY
            model.setProductCode("FAST_INSTANT_TRADE_PAY");
            //订单总金额
            model.setTotalAmount(orderFormDto.getOrderformAmount().setScale(2).toString());
            //订单标题
            model.setSubject("phone6 16Gooo");
            //订单描述
            model.setBody("订单生成，订单号：" + orderFormId);
            //用户信息
            model.setBusinessParams(JSON.toJSONString(loginUser));
            AliPayApiConfigKit.putApiConfig(aliPayApiConfig.build());
            AliPayApi.tradePage(response, model, notifyUrl, returnUrl);
        }
    }

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
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                logger.debug(entry.getKey() + ":" + entry.getValue());
            }
            response.getWriter().write("success");
        } else {// TODO 验签失败则记录异常日志，并在response中返回failure.
            response.getWriter().write("failure");
        }
        logger.debug("#############################notifyResult end#######################################");
        response.getWriter().flush();
        response.getWriter().close();
    }

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
    @RequestMapping("/isTradeQuery/{orderFormId}")
    public void isTradeQuery(@PathVariable("orderFormId") String orderFormId) throws AlipayApiException {
        AliPayApiConfigKit.putApiConfig(aliPayApiConfig.build());
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(orderFormId);
        boolean tradeQuery = AliPayApi.isTradeQuery(model);
        System.out.println(tradeQuery);
    }
}
