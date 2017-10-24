package org.billow.api.orderForm;

import org.billow.api.base.BaseService;
import org.billow.model.expand.OrderFormDto;
import org.billow.model.expand.UserDto;

/**
 * 订单信息接口<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:19:47
 */
public interface OrderFormService extends BaseService<OrderFormDto> {


    /**
     * 保存订单信息<br/>
     * 1、保存订单信息
     * 2、保存订单详细信息
     * 3、删除购物车中已经购买的商品
     * 4、邮件通知商家
     *
     * @param addressId     收货地址id
     * @param commodityIds  商品id
     * @param commodityNums 商品数量
     * @return
     */
    void saveOrderForm(UserDto loginUser, String addressId, String[] commodityIds, Integer[] commodityNums) throws Exception;
}