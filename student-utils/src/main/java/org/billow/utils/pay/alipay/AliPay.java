package org.billow.utils.pay.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.domain.GoodsDetail;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 支付宝支付工具类
 *
 * @author liuyongtao
 * @create 2017-11-27 10:52
 */
public class AliPay {

    @Autowired
    private AliPayApiConfig aliPayApiConfig;

    /**
     * 统一收单下单并支付页面接口alipay.trade.page.pay
     *
     * @param response
     */
    public void pagePay(HttpServletResponse response) throws Exception {
        String returnUrl = "";
        String notifyUrl = "";
        AlipayTradePayModel model = new AlipayTradePayModel();
        //商户订单号
        model.setOutTradeNo("");
        //销售产品码
        model.setProductCode("");
        //订单总金额
        model.setTotalAmount("");
        //订单标题
        model.setSubject("");
        //订单描述
        model.setBody("");
        AliPayApiConfigKit.putApiConfig(aliPayApiConfig.build());
        AliPayApi.tradePage(response,model,notifyUrl,returnUrl);
        /*//获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        //创建API对应的request
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
        //在公共参数中设置回跳和通知地址
        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101001\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":88.88," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"body\":\"Iphone6 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }" +
                "  }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + CHARSET);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();*/
    }
}
