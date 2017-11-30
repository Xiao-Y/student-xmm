package org.billow.service.orderForm;   

import javax.annotation.Resource;

import org.billow.api.orderForm.OrderFormPayLogService;
import org.billow.dao.OrderFormPayLogDao;
import org.billow.model.expand.OrderFormPayLogDto;
import org.billow.dao.base.BaseDao;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 * 订单支付日志表（支付、退款）实现类<br>
 *
 * @version 2.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-11-30 17:00:17
 */
@Service
public class OrderFormPayLogServiceImpl extends BaseServiceImpl<OrderFormPayLogDto> implements OrderFormPayLogService { 

	@Resource
	private OrderFormPayLogDao orderFormPayLogDao;
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<OrderFormPayLogDto> baseDao) {
		super.baseDao = this.orderFormPayLogDao;
	}
}    