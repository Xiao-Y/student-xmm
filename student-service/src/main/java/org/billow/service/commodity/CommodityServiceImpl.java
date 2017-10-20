package org.billow.service.commodity;   

import javax.annotation.Resource;

import org.billow.api.commodity.CommodityService;
import org.billow.dao.CommodityDao;
import org.billow.model.expand.CommodityDto;
import org.billow.dao.base.BaseDao;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 * 商品修改实现类<br>
 *
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-19 15:45:38
 */
@Service
public class CommodityServiceImpl extends BaseServiceImpl<CommodityDto> implements CommodityService { 

	@Resource
	private CommodityDao commodityDao;
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<CommodityDto> baseDao) {
		super.baseDao = this.commodityDao;
	}
}    