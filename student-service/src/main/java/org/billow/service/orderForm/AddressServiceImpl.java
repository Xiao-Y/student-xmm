package org.billow.service.orderForm;   

import javax.annotation.Resource;

import org.billow.api.orderForm.AddressService;
import org.billow.dao.AddressDao;
import org.billow.model.expand.AddressDto;
import org.billow.dao.base.BaseDao;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 * 收货地址实现类<br>
 *
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:32:06
 */
@Service
public class AddressServiceImpl extends BaseServiceImpl<AddressDto> implements AddressService { 

	@Resource
	private AddressDao addressDao;
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<AddressDto> baseDao) {
		super.baseDao = this.addressDao;
	}
}    