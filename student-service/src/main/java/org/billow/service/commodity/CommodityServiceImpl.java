package org.billow.service.commodity;

import javax.annotation.Resource;

import org.billow.api.commodity.CommodityService;
import org.billow.dao.CommodityDao;
import org.billow.model.expand.CommodityDto;
import org.billow.dao.base.BaseDao;
import org.billow.service.base.BaseServiceImpl;
import org.billow.utils.ToolsUtils;
import org.billow.utils.image.ImageUtils;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${commodity.img.default}")
    private String defaultImg;
    //商品图片备份路径
    @Value("${commodity.img.upload.copy}")
    private String uploadCopy;

    @Resource
    @Override
    public void setBaseDao(BaseDao<CommodityDto> baseDao) {
        super.baseDao = this.commodityDao;
    }

    @Override
    public void updateCommodityImg(String imgBase64, String path, String commodityId) throws Exception {
        String imgBase64Temp = imgBase64.split(",")[1];
        String fileName = commodityId + ".jpg";
        ImageUtils.decodeBase64ToImage(imgBase64Temp, path, fileName);
        //商品图片备份路径
        ImageUtils.decodeBase64ToImage(imgBase64Temp, uploadCopy, fileName);
        CommodityDto commodityDto = new CommodityDto();
        commodityDto.setId(commodityId);
        commodityDto.setImg(fileName);
        commodityDao.updateByPrimaryKeySelective(commodityDto);
    }

    @Override
    public List<CommodityDto> selectAll(CommodityDto commodityDto) {
        List<CommodityDto> commodityDtos = super.selectAll(commodityDto);
        if (ToolsUtils.isNotEmpty(commodityDtos)) {
            for (CommodityDto dto : commodityDtos) {
                if (ToolsUtils.isEmpty(dto.getImg())) {
                    dto.setImg(defaultImg);
                }
            }
        }
        return commodityDtos;
    }

    @Override
    public CommodityDto selectByPrimaryKey(CommodityDto record) {
        CommodityDto commodityDto = super.selectByPrimaryKey(record);
        if (commodityDto != null && ToolsUtils.isEmpty(commodityDto.getImg())) {
            commodityDto.setImg(defaultImg);
        }
        return commodityDto;
    }
}