package org.billow.controller.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradePayModel;
import org.billow.api.orderForm.OrderFormService;
import org.billow.model.expand.OrderFormDto;
import org.billow.utils.pay.alipay.AliPayApi;
import org.billow.utils.pay.alipay.AliPayApiConfig;
import org.billow.utils.pay.alipay.AliPayApiConfigKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 支付宝支付
 *
 * @author liuyongtao
 * @create 2017-11-28 20:36
 */
@Controller
@RequestMapping("/aliPay")
public class AliPay {

    @Autowired
    private AliPayApiConfig aliPayApiConfig;
    @Autowired
    private OrderFormService orderFormService;

    /**
     * 打开支付支付页面
     */
    @RequestMapping("/openAliPayPage/{orderFormId}")
    public void openAliPayPage(HttpServletResponse response,
                               @PathVariable("orderFormId") String orderFormId) throws Exception {
        OrderFormDto orderFormDto = orderFormService.selectByPrimaryKey(new OrderFormDto(orderFormId));
        if (orderFormDto != null) {
            //打开支付宝支付页面
            String returnUrl = "http://localhost:8099/fg/fgHome/shoppingCart";
            String notifyUrl = "";
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
            AliPayApiConfigKit.putApiConfig(aliPayApiConfig.build());
            AliPayApi.tradePage(response, model, notifyUrl, returnUrl);
        }
    }
}
