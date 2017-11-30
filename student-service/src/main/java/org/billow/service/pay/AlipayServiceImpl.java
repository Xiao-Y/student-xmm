package org.billow.service.pay;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.billow.api.orderForm.OrderFormPayLogService;
import org.billow.api.orderForm.OrderFormService;
import org.billow.api.pay.AlipayService;
import org.billow.model.expand.OrderFormDto;
import org.billow.model.expand.OrderFormPayLogDto;
import org.billow.utils.enumType.PayEunm;
import org.billow.utils.generator.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

@Service
public class AlipayServiceImpl implements AlipayService {

    private final static Logger logger = Logger.getLogger(AlipayServiceImpl.class);

    @Autowired
    private OrderFormPayLogService orderFormPayLogService;
    @Autowired
    private OrderFormService orderFormService;

    @Override
    public void saveAlipayData(Map<String, String> paramsMap) throws Exception {
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
        logger.debug("########################  保存支付宝返回报文,更新订单状态 start ####################################");
        //操作用户信息
        String passback_params = URLDecoder.decode(paramsMap.get("passback_params"), "UTF-8");
        paramsMap.put("passback_params", passback_params);

        Map<String, String> userMap = JSON.parseObject(passback_params, Map.class);
        Integer userId = new Integer(userMap.get("userId"));
        String userName = userMap.get("userName");
        //支付信息
        String orderFormId = paramsMap.get("out_trade_no");
        String trade_no = paramsMap.get("trade_no");
        String buyer_id = paramsMap.get("buyer_id");
        String trade_status = paramsMap.get("trade_status");
        String total_amount = paramsMap.get("total_amount");
        String info = JSON.toJSONString(paramsMap);
        logger.debug(PayEunm.getName(info));
        OrderFormPayLogDto log = new OrderFormPayLogDto();
        log.setId(UUID.generate());
        log.setCreateTime(new Date());
        log.setLoginUserId(userId);
        log.setLoginUserName(userName);
        log.setOrderFormId(orderFormId);
        log.setBusinessNo(trade_no);
        log.setBuyerId(buyer_id);
        log.setTotalAmount(new BigDecimal(total_amount));
        String status = PayEunm.getStatus(trade_status);
        logger.debug(PayEunm.getName(status));
        log.setStatus(status);
        log.setInfo(info);
        //插入支付宝返回日志
        orderFormPayLogService.insert(log);
        //更新订单状态
        OrderFormDto orderFormDto = new OrderFormDto();
        orderFormDto.setId(orderFormId);
        orderFormDto.setStatus(status);
        orderFormService.updateByPrimaryKeySelective(orderFormDto);
        logger.debug("########################  保存支付宝返回报文,更新订单状态 end ####################################");
    }
}
