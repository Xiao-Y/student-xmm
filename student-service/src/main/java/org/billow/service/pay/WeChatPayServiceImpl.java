package org.billow.service.pay;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.billow.api.orderForm.OrderFormPayLogService;
import org.billow.api.orderForm.OrderFormService;
import org.billow.api.pay.PayService;
import org.billow.model.expand.OrderFormDto;
import org.billow.model.expand.OrderFormPayLogDto;
import org.billow.utils.ToolsUtils;
import org.billow.utils.generator.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

/**
 * 微信支付
 *
 * @author liuyongtao
 * @create 2017-12-26 16:43
 */
@Service
public class WeChatPayServiceImpl implements PayService {

    private final static Logger logger = Logger.getLogger(WeChatPayServiceImpl.class);

    @Autowired
    private OrderFormPayLogService orderFormPayLogService;
    @Autowired
    private OrderFormService orderFormService;

    @Override
    public void savePayData(Map<String, String> paramsMap) throws Exception{
        logger.debug("########################  保存微信返回报文,更新订单状态 start ####################################");
        //操作用户信息
        Integer userId = null;
        String userName = null;
        String passbackParams = paramsMap.get("passback_params");
        if (ToolsUtils.isNotEmpty(passbackParams)) {
            String passback_params = URLDecoder.decode(passbackParams, "UTF-8");
            paramsMap.put("passback_params", passback_params);

            Map<String, String> userMap = JSON.parseObject(passback_params, Map.class);
            userId = new Integer(userMap.get("userId"));
            userName = userMap.get("userName");
        }
        //支付信息
        String orderFormId = paramsMap.get("out_trade_no");//订单号
        String trade_no = paramsMap.get("transaction_id");//微信交易号
        String buyer_id = paramsMap.get("openid");//买家微信唯一标识
        String result_code = paramsMap.get("result_code");//支付状态,SUCCESS/FAIL
        String total_amount = paramsMap.get("total_fee");//支付金额
        String info = paramsMap.get("info");
        //微信返回报文
        String status = "SUCCESS".equals(result_code) ? "3" : "2";
        logger.info(result_code);
        logger.info(info);
        try {
            OrderFormPayLogDto log = new OrderFormPayLogDto();
            log.setId(UUID.generate());
            log.setCreateTime(new Date());
            log.setLoginUserId(userId);
            log.setLoginUserName(userName);
            log.setOrderFormId(orderFormId);
            log.setBusinessNo(trade_no);
            log.setBuyerId(buyer_id);
            log.setTotalAmount(new BigDecimal(total_amount));
            log.setStatus(status);
            log.setInfo(info);
            //插入微信返回日志
            orderFormPayLogService.insert(log);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        //更新订单状态
        OrderFormDto orderFormDto = new OrderFormDto();
        orderFormDto.setId(orderFormId);
        orderFormDto.setStatus(status);
        orderFormService.updateByPrimaryKeySelective(orderFormDto);
        logger.debug("########################  保存微信返回报文,更新订单状态 end ####################################");
    }
}
