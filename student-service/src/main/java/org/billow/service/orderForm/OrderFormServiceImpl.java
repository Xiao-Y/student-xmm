package org.billow.service.orderForm;

import org.apache.ibatis.annotations.Case;
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
import org.billow.utils.ToolsUtils;
import org.billow.utils.date.DateTime;
import org.billow.utils.enumType.PayStatusEunm;
import org.billow.utils.generator.OrderNumUtil;
import org.billow.utils.generator.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    public Map<String, String> saveOrderForm(HttpServletResponse response, UserDto loginUser, String addressId,
                                             String[] commodityIds, String[] commodityNums) throws Exception {
        //订单金额
        BigDecimal orderFormAmount = new BigDecimal(0.00);
        //订单id
        String orderFormId = OrderNumUtil.makeOrderNum();
        // 2、保存订单详细信息
        //用于邮件发送
        List<OrderFormDetailDto> detailes = new ArrayList<>();
        for (int i = 0; i < commodityIds.length; i++) {
            CommodityDto commodityDto = new CommodityDto();
            commodityDto.setId(commodityIds[i]);
            //查询商品信息
            CommodityDto dto = commodityDao.selectByPrimaryKey(commodityDto);
            //如果是无货或者已下架商品不出单
            if ("0".equals(dto.getStatus()) || "0".equals(dto.getValid())) {
                continue;
            }
            OrderFormDetailDto orderFormDetailDto = new OrderFormDetailDto();
            BeanUtils.copyProperties(dto, orderFormDetailDto);
            orderFormDetailDto.setOrderFormId(orderFormId);
            orderFormDetailDto.setId(UUID.generate());
            orderFormDetailDto.setCommodityNum(new Integer(commodityNums[i]));
            orderFormDetailDto.setCommodityId(dto.getId());
            orderFormDetailDto.setCommodityImg(dto.getImg());
            //计算订单金额
            orderFormAmount = orderFormAmount.add(new BigDecimal(commodityNums[i]).multiply(dto.getUnitPrice()));
            detailes.add(orderFormDetailDto);
            orderFormDetailDao.insert(orderFormDetailDto);
            // 3、删除购物车中已经购买的商品
            ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
            shoppingCartDto.setId(loginUser.getUserId().toString());
            shoppingCartDto.setCommodityId(dto.getId());
            //shoppingCartDao.deleteByPrimaryKey(shoppingCartDto);
            //4更新商品销售数量
            dto.setQuantity(dto.getQuantity() + new Integer(commodityNums[i]));
            commodityDao.updateByPrimaryKeySelective(dto);
        }
        // 1、保存订单信息
        //查询地址信息
        AddressDto addressDto = new AddressDto();
        addressDto.setId(addressId);
        AddressDto dto = addressDao.selectByPrimaryKey(addressDto);
        OrderFormDto orderFormDto = new OrderFormDto();
        orderFormDto.setId(orderFormId);
        orderFormDto.setStatus(PayStatusEunm.UNPAID.getStatus());
        orderFormDto.setDelFlag("0");
        orderFormDto.setConsignee(dto.getConsignee());
        orderFormDto.setConsigneePhone(dto.getConsigneePhone());
        orderFormDto.setConsigneeAddress(dto.getConsigneeAddress());
        orderFormDto.setOrderformAmount(orderFormAmount.setScale(2));
        orderFormDto.setCreateDate(new DateTime(new Date(), DateTime.YEAR_TO_SECOND));
        orderFormDto.setUpdateDate(new DateTime(new Date(), DateTime.YEAR_TO_SECOND));
        orderFormDto.setUserId(loginUser.getUserId());
        orderFormDao.insert(orderFormDto);
        logger.info("订单生成，订单号：" + orderFormId);
        //邮件发送内容
        Map<String, String> map = this.mailSendContent(orderFormId, orderFormAmount, detailes, dto);
        map.put("orderFormId", orderFormId);
        return map;
    }

    @Override
    public List<OrderFormDto> selectAllAndOptionButton(OrderFormDto orderFormDto) {
        boolean isCustomer = orderFormDto.getIsCustomer();
        List<OrderFormDto> orderFormDtos = orderFormDao.selectAll(orderFormDto);
        if (ToolsUtils.isNotEmpty(orderFormDtos)) {
            for (OrderFormDto dto : orderFormDtos) {
                this.setOptionButton(dto, isCustomer);
            }
        }
        return orderFormDtos;
    }

    @Override
    public void updateOrderForm(OrderFormDto orderFormDto) throws Exception {
        orderFormDao.updateByPrimaryKeySelective(orderFormDto);
    }

    /**
     * 构建订单操作的button
     *
     * @param dto
     * @param isCustomer 是否从客户端进入
     */
    private void setOptionButton(OrderFormDto dto, boolean isCustomer) {
        Map<String, String> map = new HashMap<>();
        String status = dto.getStatus();
        dto.setStatusName(PayStatusEunm.getNameByStatus(status));
        if (isCustomer) {
            if (PayStatusEunm.UNPAID.getStatus().equals(status)) {//未支付
                PayStatusEunm customerCancellation = PayStatusEunm.CUSTOMER_CANCELLATION;
                map.put(customerCancellation.getNameCode(), "取消订单");
                PayStatusEunm agpaid = PayStatusEunm.AGPAID;
                map.put(agpaid.getNameCode(), agpaid.getName());
            } else if (PayStatusEunm.TRADE_FAILURE.getStatus().equals(status)) {//支付失败
                PayStatusEunm agpaid2 = PayStatusEunm.AGPAID;
                map.put(agpaid2.getNameCode(), agpaid2.getName());
            } else if (PayStatusEunm.CUSTOMER_CANCELLATION.getStatus().equals(status)) {//取消订单
                PayStatusEunm deleteOrderform = PayStatusEunm.DELETE_ORDER_FORM;
                map.put(deleteOrderform.getNameCode(), deleteOrderform.getName());
            } else if (PayStatusEunm.CONFIRMATION_GOODS_RECEIPT.getStatus().equals(status)) {//确认收货
                PayStatusEunm appRefundPro = PayStatusEunm.APPLICATION_REFUND_PROCESSING;
                map.put(appRefundPro.getNameCode(), "申请退款");
            } else if (PayStatusEunm.TRADE_FINISHED.getStatus().equals(status)//交易结束，不可退款
                    //发货中
                    || PayStatusEunm.CONSIGNMENT.getStatus().equals(status)
                    //支付完成后全额退款，关闭交易
                    || PayStatusEunm.TRADE_CLOSED.getStatus().equals(status)
                    //交易完成
                    || PayStatusEunm.TRANSACTION_COMPLETION.getStatus().equals(status)) {
                PayStatusEunm confirmationGoodsReceipt = PayStatusEunm.CONFIRMATION_GOODS_RECEIPT;
                map.put(confirmationGoodsReceipt.getNameCode(), confirmationGoodsReceipt.getName());
            } else if (PayStatusEunm.REFUND_FAILURE.getStatus().equals(status)) {//退款失败
                map.put("xianxia", "线下协商");
            } else if (PayStatusEunm.APPLICATION_REFUND_DISAGREE.getStatus().equals(status)) {//申请退款-不同意
                map.put("xianxia", "线下协商");
            }
        } else {
            if (PayStatusEunm.TRADE_SUCCESS.getStatus().equals(status)) {//支付成功
                PayStatusEunm businessCancellation = PayStatusEunm.BUSINESS_CANCELLATION;
                map.put(businessCancellation.getNameCode(), "取消订单");
                PayStatusEunm businessConfirmation = PayStatusEunm.BUSINESS_CONFIRMATION;
                map.put(businessConfirmation.getNameCode(), "确认订单");
            } else if (PayStatusEunm.BUSINESS_CANCELLATION.getStatus().equals(status)) {//取消订单
                map.put("delete", "删除订单");
            } else if (PayStatusEunm.BUSINESS_CONFIRMATION.getStatus().equals(status)) {//确认订单
                PayStatusEunm consignment = PayStatusEunm.CONSIGNMENT;
                map.put(consignment.getNameCode(), consignment.getName());
            } else if (PayStatusEunm.APPLICATION_REFUND_PROCESSING.getStatus().equals(status)) {//申请退款-处理中
                PayStatusEunm applicationRefundAgree = PayStatusEunm.APPLICATION_REFUND_AGREE;
                map.put(applicationRefundAgree.getNameCode(), "同意");
                PayStatusEunm applicationRefundDisagree = PayStatusEunm.APPLICATION_REFUND_DISAGREE;
                map.put(applicationRefundDisagree.getNameCode(), "不同意");
            }
        }
        dto.setOptionButton(map);
    }

    /**
     * 邮件发送内容
     *
     * @param orderFormId     订单号
     * @param orderFormAmount 订单金额
     * @param detailes        订单详细信息
     * @param address         收货地址
     * @return key-mailContent
     */
    private Map<String, String> mailSendContent(String orderFormId, BigDecimal orderFormAmount,
                                                List<OrderFormDetailDto> detailes, AddressDto address) {
        StringBuilder detailContent = new StringBuilder();
        detailContent.append("<table width=\"500\" rules=\"rows\"> ");
        detailContent.append("<tr>");
        detailContent.append("<td width=\"200\">商品名称</td>");
        detailContent.append("<td width=\"100\">商品数量</td>");
        detailContent.append("<td width=\"100\">单价</td>");
        detailContent.append("<td width=\"100\">小计</td>");
        detailContent.append("</tr>");
        for (OrderFormDetailDto orderFormDetailDto : detailes) {
            detailContent.append("<tr>");
            detailContent.append("<td>");
            detailContent.append(orderFormDetailDto.getCommodityName());
            detailContent.append("</td>");
            detailContent.append("<td>");
            detailContent.append(orderFormDetailDto.getCommodityNum());
            detailContent.append("</td>");
            detailContent.append("<td>");
            detailContent.append(orderFormDetailDto.getUnitPrice());
            detailContent.append("/");
            detailContent.append(orderFormDetailDto.getSpec());
            detailContent.append("</td>");
            detailContent.append("<td>");
            //小计
            BigDecimal decimal = new BigDecimal(orderFormDetailDto.getCommodityNum())
                    .multiply(orderFormDetailDto.getUnitPrice());
            detailContent.append(decimal);
            detailContent.append("</tr>");
        }
        detailContent.append("</table>");
        //邮件发送内容
        StringBuilder content = new StringBuilder();
        content.append("订单号：");
        content.append(orderFormId);
        content.append("<br/>");
        content.append("收货人：");
        content.append(address.getConsignee());
        content.append("<br/>");
        content.append("收货人电话：");
        content.append(address.getConsigneePhone());
        content.append("<br/>");
        content.append("收货人地址：");
        content.append(address.getConsigneeAddress());
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