package org.billow.dao;

import org.billow.dao.base.BaseDao;
import org.billow.model.expand.OrderFormDto;

import java.util.List;

/**
 * 订单信息接口<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:19:47
 */
public interface OrderFormDao extends BaseDao<OrderFormDto> {

    /**
     * 查询需要自动任务相关的订单
     *
     * @return
     */
    List<OrderFormDto> findAutoOrderForm(OrderFormDto orderFormDto);
}