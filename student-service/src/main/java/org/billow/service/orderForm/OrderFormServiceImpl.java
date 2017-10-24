package org.billow.service.orderForm;

import javax.annotation.Resource;

import org.billow.api.orderForm.OrderFormService;
import org.billow.dao.AddressDao;
import org.billow.dao.CommodityDao;
import org.billow.dao.OrderFormDao;
import org.billow.dao.OrderFormDetailDao;
import org.billow.dao.ShoppingCartDao;
import org.billow.model.expand.AddressDto;
import org.billow.model.expand.CommodityDto;
import org.billow.model.expand.OrderFormDetailDto;
import org.billow.model.expand.OrderFormDto;
import org.billow.dao.base.BaseDao;
import org.billow.model.expand.ShoppingCartDto;
import org.billow.model.expand.UserDto;
import org.billow.service.base.BaseServiceImpl;
import org.billow.utils.date.DateTime;
import org.billow.utils.generator.OrderNumUtil;
import org.billow.utils.generator.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

/**
 * 订单信息实现类<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:19:47
 */
@Service
public class OrderFormServiceImpl extends BaseServiceImpl<OrderFormDto> implements OrderFormService {

    @Resource
    private OrderFormDao orderFormDao;
    @Autowired
    private OrderFormDetailDao orderFormDetailDao;
    @Autowired
    private CommodityDao commodityDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Resource
    @Override
    public void setBaseDao(BaseDao<OrderFormDto> baseDao) {
        super.baseDao = this.orderFormDao;
    }

    @Override
    public void saveOrderForm(UserDto loginUser, String addressId, String[] commodityIds, Integer[] commodityNums) throws Exception {
        //订单金额
        BigDecimal orderFormAmount = new BigDecimal(0.00);
        //订单id
        String orderFormId = OrderNumUtil.makeOrderNum();
        // 2、保存订单详细信息
        for (int i = 0; i < commodityIds.length; i++) {
            CommodityDto commodityDto = new CommodityDto();
            commodityDto.setId(commodityIds[i]);
            //查询商品信息
            CommodityDto dto = commodityDao.selectByPrimaryKey(commodityDto);
            OrderFormDetailDto orderFormDetailDto = new OrderFormDetailDto();
            BeanUtils.copyProperties(dto, orderFormDetailDto);
            orderFormDetailDto.setOrderFormId(orderFormId);
            orderFormDetailDto.setId(UUID.generate());
            orderFormDetailDto.setCommodityNum(commodityNums[i]);
            //计算订单金额
            orderFormAmount = orderFormAmount.add(new BigDecimal(commodityNums[i]).multiply(dto.getUnitPrice()));
            orderFormDetailDao.insert(orderFormDetailDto);
            // 3、删除购物车中已经购买的商品
            ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
            shoppingCartDto.setId(loginUser.getUserId().toString());
            shoppingCartDto.setCommodityId(dto.getId());
            shoppingCartDao.deleteByPrimaryKey(shoppingCartDto);
        }
        // 1、保存订单信息
        //查询地址信息
        AddressDto addressDto = new AddressDto();
        addressDto.setId(addressId);
        AddressDto dto = addressDao.selectByPrimaryKey(addressDto);
        OrderFormDto orderFormDto = new OrderFormDto();
        orderFormDto.setId(orderFormId);
        orderFormDto.setStatus("1");
        orderFormDto.setConsignee(dto.getConsignee());
        orderFormDto.setConsigneePhone(dto.getConsigneePhone());
        orderFormDto.setOrderformAmount(orderFormAmount.setScale(2));
        orderFormDto.setCreateDate(new DateTime(new Date(), DateTime.YEAR_TO_SECOND));
        orderFormDto.setUpdateDate(new DateTime(new Date(), DateTime.YEAR_TO_SECOND));
        orderFormDto.setUserId(loginUser.getUserId());
        orderFormDao.insert(orderFormDto);

        // 4、邮件通知商家
    }
}