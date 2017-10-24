package org.billow.service.orderForm;   

import javax.annotation.Resource;

import org.billow.api.orderForm.OrderFormService;
import org.billow.dao.OrderFormDao;
import org.billow.model.expand.OrderFormDto;
import org.billow.dao.base.BaseDao;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 * 订单信息实现类<br>
 *
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:19:47
 */
@Service
public class OrderFormServiceImpl extends BaseServiceImpl<OrderFormDto> implements OrderFormService { 

	@Resource
	private OrderFormDao orderFormDao;
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<OrderFormDto> baseDao) {
		super.baseDao = this.orderFormDao;
	}
}    