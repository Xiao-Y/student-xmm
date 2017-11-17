package org.billow.service.commodity;

import javax.annotation.Resource;

import org.billow.api.commodity.CommodityService;
import org.billow.dao.CommodityDao;
import org.billow.model.expand.CommodityDto;
import org.billow.dao.base.BaseDao;
import org.billow.service.base.BaseServiceImpl;
import org.billow.utils.image.ImageUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品修改实现类<br>
 *
 * @author billow<br>
 * @version 1.0
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

    @Override
    public void updateCommodityImg(String imgBase64, String path, String commodityId) throws Exception {
        imgBase64 = imgBase64.split(",")[1];
        String fileName = commodityId + ".png";
        ImageUtils.decodeBase64ToImage(imgBase64, path, fileName);
        CommodityDto commodityDto = new CommodityDto();
        commodityDto.setId(commodityId);
        commodityDto.setImg(fileName);
        commodityDao.updateByPrimaryKeySelective(commodityDto);
    }
}