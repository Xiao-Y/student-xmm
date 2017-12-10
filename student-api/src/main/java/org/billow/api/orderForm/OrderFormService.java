package org.billow.api.orderForm;

import org.billow.api.base.BaseService;
import org.billow.model.expand.OrderFormDto;
import org.billow.model.expand.UserDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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
     * @return 邮件发送内容
     */
    Map<String, String> saveOrderForm(HttpServletResponse response, UserDto loginUser, String addressId,
                                      String[] commodityIds, String[] commodityNums) throws Exception;

    /**
     * 获取订单信息和可以操作的buttton
     *
     * @param orderFormDto
     * @return
     */
    List<OrderFormDto> selectAllAndOptionButton(OrderFormDto orderFormDto);

    /**
     * 修改订单状态,并记录操作日志
     *
     * @param orderFormDto
     */
    void updateOrderForm(OrderFormDto orderFormDto) throws Exception;

    /**
     * 更新订单状态自动任务<p/>
     * 1.退款成功-97-支付完成后全额退款，关闭交易<br/>
     * 2.申请退款-不同意-99-交易结束，不可退款<br/>
     * 3.确认收货-15天-98-交易完成”<br/>
     *
     * @throws Exception
     */
    void updateOrderFormAutoTradeClosed() throws Exception;

    /**
     * 自动将发货中7天修改为确认收货
     *
     * @throws Exception
     */
    void updateOrderFormAutoConfirmReceipt() throws Exception;

    /**
     * 获取订单的所有状态
     *
     * @return
     */
    Map<String, String> getQueryStatus();
}