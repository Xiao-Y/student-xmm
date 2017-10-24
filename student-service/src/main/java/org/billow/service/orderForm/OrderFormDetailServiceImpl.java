package org.billow.service.orderForm;   

import javax.annotation.Resource;

import org.billow.api.orderForm.OrderFormDetailService;
import org.billow.dao.OrderFormDetailDao;
import org.billow.model.expand.OrderFormDetailDto;
import org.billow.dao.base.BaseDao;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 * 订单信息详细实现类<br>
 *
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:21:53
 */
@Service
public class OrderFormDetailServiceImpl extends BaseServiceImpl<OrderFormDetailDto> implements OrderFormDetailService { 

	@Resource
	private OrderFormDetailDao orderFormDetailDao;
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<OrderFormDetailDto> baseDao) {
		super.baseDao = this.orderFormDetailDao;
	}
}    