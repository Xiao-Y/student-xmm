package org.billow.controller.pay;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.billow.api.orderForm.OrderFormService;
import org.billow.api.pay.PayService;
import org.billow.common.queues.OrderFormTaskQueue;
import org.billow.model.expand.OrderFormDto;
import org.billow.utils.enumType.OrderFormTaskQueueEunm;
import org.billow.utils.enumType.PayStatusEunm;
import org.billow.utils.pay.wechat.MessageUtil;
import org.billow.utils.pay.wechat.WeChatPayApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付
 *
 * @author liuyongtao
 * @create 2017-12-26 10:47
 */
@Controller
@RequestMapping("mb/weChatPay")
public class WeChatPayController {

    private static final Logger logger = Logger.getLogger(WeChatPayController.class);

    @Autowired
    private OrderFormService orderFormService;
    @Resource(name = "weChatPayServiceImpl")
    private PayService weChatPayService;

    @ResponseBody
    @RequestMapping("/weChatPay")
    public Map<String, String> weChatPay(HttpServletRequest request, OrderFormDto orderFormDto) throws Exception {
        OrderFormDto formDto = orderFormService.selectByPrimaryKey(orderFormDto);
        Map<String, String> map = new HashMap<>();
        if (formDto != null) {
            map = WeChatPayApi.unifiedOrder(orderFormDto.getJsCode(), formDto.getId(), formDto.getOrderformAmount());
        }
        return map;
    }

    /**
     * 异步支付状态通知
     *
     * @param response
     * @param request
     * @throws Exception
     */
    @RequestMapping("/notifyResult")
    public void notifyResult(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String return_code = request.getParameter("return_code");
        if ("SUCCESS".equals(return_code)) {//响应成功
            Map<String, String> paramsMap = MessageUtil.parseXML(request);
            String orderFormId = paramsMap.get("out_trade_no");
            OrderFormDto orderFormDto = new OrderFormDto();
            orderFormDto.setId(orderFormId);
            OrderFormDto formDto = orderFormService.selectByPrimaryKey(orderFormDto);
            String status = formDto.getStatus();
            //2-交易支付失败，4-继续支付,6-客户取消
            if (formDto != null && (
                    PayStatusEunm.TRADE_FAILURE.getStatus().equals(status)
                            || PayStatusEunm.AGPAID.getStatus().equals(status)
                            || PayStatusEunm.CUSTOMER_CANCELLATION.getStatus().equals(status))) {
                //更新订单状态和保存wechat返回的数据
                String info = JSON.toJSONString(paramsMap);
                paramsMap.put("info", info);
                weChatPayService.savePayData(paramsMap);
                try {
                    //插入自动确认订单队列
                    String typeCode = OrderFormTaskQueueEunm.ORDER_FORM_AUTO_CONFIRMATION.getTypeCode();
                    new OrderFormTaskQueue(typeCode, orderFormId).putOrderFormTask();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("插入自动确认订单队列错误" + e);
                }
                Map<String, String> result = new HashMap<>();
                result.put("return_code", "SUCCESS");
                result.put("return_msg", "OK");
                String message = MessageUtil.messageToXML(result);
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(message);
            }
        }
    }
}
