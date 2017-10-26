package org.billow.service.orderForm;

import org.apache.log4j.Logger;
import org.billow.api.orderForm.OrderFormService;
import org.billow.dao.AddressDao;
import org.billow.dao.CommodityDao;
import org.billow.dao.OrderFormDao;
import org.billow.dao.OrderFormDetailDao;
import org.billow.dao.ShoppingCartDao;
import org.billow.dao.base.BaseDao;
import org.billow.model.expand.AddressDto;
import org.billow.model.expand.CommodityDto;
import org.billow.model.expand.OrderFormDetailDto;
import org.billow.model.expand.OrderFormDto;
import org.billow.model.expand.ShoppingCartDto;
import org.billow.model.expand.UserDto;
import org.billow.service.base.BaseServiceImpl;
import org.billow.utils.date.DateTime;
import org.billow.utils.generator.OrderNumUtil;
import org.billow.utils.generator.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    private static final Logger logger = Logger.getLogger(OrderFormServiceImpl.class);

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
    public Map<String, String> saveOrderForm(UserDto loginUser, String addressId, String[] commodityIds, String[] commodityNums) throws Exception {
        //订单金额
        BigDecimal orderFormAmount = new BigDecimal(0.00);
        //订单id
        String orderFormId = OrderNumUtil.makeOrderNum();
        // 2、保存订单详细信息
        StringBuilder detailContent = new StringBuilder();
        detailContent.append("<table width=\"600\" rules=\"rows\"> ");
        detailContent.append("<tr>");
        detailContent.append("<td width=\"200\">商品名称</td>");
        detailContent.append("<td width=\"100\">商品数量</td>");
        detailContent.append("<td width=\"100\">单价</td>");
        detailContent.append("<td width=\"100\">规格</td>");
        detailContent.append("<td width=\"100\">小计</td>");
        detailContent.append("</tr>");
        for (int i = 0; i < commodityIds.length; i++) {
            CommodityDto commodityDto = new CommodityDto();
            commodityDto.setId(commodityIds[i]);
            //查询商品信息
            CommodityDto dto = commodityDao.selectByPrimaryKey(commodityDto);
            OrderFormDetailDto orderFormDetailDto = new OrderFormDetailDto();
            BeanUtils.copyProperties(dto, orderFormDetailDto);
            orderFormDetailDto.setOrderFormId(orderFormId);
            orderFormDetailDto.setId(UUID.generate());
            orderFormDetailDto.setCommodityNum(new Integer(commodityNums[i]));
            //小计
            BigDecimal decimal = new BigDecimal(commodityNums[i]).multiply(dto.getUnitPrice());
            //计算订单金额
            orderFormAmount = orderFormAmount.add(decimal);
            orderFormDetailDao.insert(orderFormDetailDto);
            //商品详细,用于发送邮件
            detailContent.append("<tr>");
            detailContent.append("<td>");
            detailContent.append(orderFormDetailDto.getCommodityName());
            detailContent.append("</td>");
            detailContent.append("<td>");
            detailContent.append(orderFormDetailDto.getCommodityNum());
            detailContent.append("</td>");
            detailContent.append("<td>");
            detailContent.append(orderFormDetailDto.getUnitPrice());
            detailContent.append("</td>");
            detailContent.append("<td>");
            detailContent.append(orderFormDetailDto.getSpec());
            detailContent.append("</td>");
            detailContent.append("<td>");
            detailContent.append(decimal);
            detailContent.append("</tr>");

            // 3、删除购物车中已经购买的商品
            ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
            shoppingCartDto.setId(loginUser.getUserId().toString());
            shoppingCartDto.setCommodityId(dto.getId());
            shoppingCartDao.deleteByPrimaryKey(shoppingCartDto);
        }
        detailContent.append("</table>");
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
        //邮件发送内容
        StringBuilder content = new StringBuilder();
        content.append("订单号：");
        content.append(orderFormId);
        content.append("<br/>");
        content.append("收货人：");
        content.append(dto.getConsignee());
        content.append("<br/>");
        content.append("收货人电话：");
        content.append(dto.getConsigneePhone());
        content.append("<br/>");
        content.append("收货人地址：");
        content.append(dto.getConsigneeAddress());
        content.append("<br/>");
        content.append("订单金额：");
        content.append(orderFormAmount.setScale(2));
        content.append("<br/>");
        content.append("<br/>");
        content.append("<br/>");
        content.append("******************************详细订单信息***********************");
        content.append("<br/>");
        content.append("<br/>");
        content.append(detailContent);
        content.append("<br/>");
        content.append("<br/>");
        content.append("<br/>");
        Map<String, String> map = new HashMap<>();
        map.put("mailContent", content.toString());
        return map;
    }
}