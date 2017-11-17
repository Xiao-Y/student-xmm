package org.billow.api.commodity;

import org.billow.api.base.BaseService;
import org.billow.model.expand.CommodityDto;

import java.util.List;

/**
 * 商品修改接口<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-19 15:45:38
 */
public interface CommodityService extends BaseService<CommodityDto> {


    /**
     * 更新商品图片信息
     *
     * @param imgBase64
     * @param path
     * @param commodityId
     */
    void updateCommodityImg(String imgBase64, String path, String commodityId) throws Exception;
}