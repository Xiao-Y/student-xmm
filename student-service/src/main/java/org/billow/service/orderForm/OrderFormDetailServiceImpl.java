package org.billow.service.orderForm;

import javax.annotation.Resource;

import org.billow.api.orderForm.OrderFormDetailService;
import org.billow.dao.OrderFormDetailDao;
import org.billow.model.expand.CommodityDto;
import org.billow.model.expand.OrderFormDetailDto;
import org.billow.dao.base.BaseDao;
import org.billow.service.base.BaseServiceImpl;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单信息详细实现类<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:21:53
 */
@Service
public class OrderFormDetailServiceImpl extends BaseServiceImpl<OrderFormDetailDto> implements OrderFormDetailService {

    @Resource
    private OrderFormDetailDao orderFormDetailDao;
    @Value("${commodiity.img.default}")
    private String defaultImg;

    @Resource
    @Override
    public void setBaseDao(BaseDao<OrderFormDetailDto> baseDao) {
        super.baseDao = this.orderFormDetailDao;
    }

    @Override
    public List<OrderFormDetailDto> selectAll(OrderFormDetailDto orderFormDetailDto) {
        List<OrderFormDetailDto> orderFormDetailDtos = super.selectAll(orderFormDetailDto);
        if (ToolsUtils.isNotEmpty(orderFormDetailDtos)) {
            for (OrderFormDetailDto dto : orderFormDetailDtos) {
                if (ToolsUtils.isEmpty(dto.getCommodityImg())) {
                    dto.setCommodityImg(defaultImg);
                }
            }
        }
        return orderFormDetailDtos;
    }
}