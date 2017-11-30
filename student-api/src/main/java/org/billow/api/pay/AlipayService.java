package org.billow.api.pay;

import java.util.Map;

/**
 * 支付宝支付业务操作接口
 */
public interface AlipayService {

    /**
     * 更新订单状态和保存支付宝返回的数据
     *
     * @param paramsMap
     */
    void saveAlipayData(Map<String, String> paramsMap) throws Exception;
}
