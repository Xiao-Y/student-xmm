package org.billow.controller.orderForm;

import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.billow.api.orderForm.OrderFormDetailService;
import org.billow.api.orderForm.OrderFormService;
import org.billow.api.orderForm.ShoppingCartService;
import org.billow.common.email.EmailServer;
import org.billow.common.login.LoginHelper;
import org.billow.common.queues.OrderFormTaskQueue;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.OrderFormDetailDto;
import org.billow.model.expand.OrderFormDto;
import org.billow.model.expand.ShoppingCartDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.PageHelper;
import org.billow.utils.ToolsUtils;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.billow.utils.enumType.OrderFormTaskQueueEunm;
import org.billow.utils.enumType.PayStatusEunm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单信息控制器<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:19:47
 */
@Controller
@RequestMapping("/orderForm")
public class OrderFormController {

    private static final Logger logger = Logger.getLogger(OrderFormController.class);

    @Autowired
    private OrderFormService orderFormService;
    @Autowired
    private OrderFormDetailService orderFormDetailService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private EmailServer emailServer;
    @Value("${mail.auto.send}")
    private boolean emailAutoSend;
    @Value("${mail.auto.default.to}")
    private String toEmails;
    @Value("${mail.auto.customer.cancel.send}")
    private boolean customerCancel;
    @Value("${mail.auto.business.cancel.send}")
    private boolean businessCancel;
    @Value("${pay.isOpen}")
    private boolean isOpen;

    /**
     * 保存订单信息
     *
     * @param request
     * @param addressId     收货地址id
     * @param commodityIds  商品id
     * @param commodityNums 商品数量
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveOrderForm")
    public JsonResult saveOrderForm(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "addressId") String addressId,
                                    @RequestParam(value = "commodityIds[]") String[] commodityIds,
                                    @RequestParam(value = "commodityNums[]") String[] commodityNums) {
        JsonResult json = new JsonResult();
        HttpSession session = request.getSession();
        UserDto loginUser = LoginHelper.getLoginUser(session);
        String message = "";
        String type = "";
        try {
            Map<String, String> map = orderFormService.saveOrderForm(response, loginUser, addressId, commodityIds, commodityNums);
            //更新页面购物车显示的数量
            ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
            shoppingCartDto.setId(loginUser.getUserId().toString());
            int shoppingCount = 0;
            List<ShoppingCartDto> shoppingCartDtos = shoppingCartService.selectAll(shoppingCartDto);
            if (ToolsUtils.isNotEmpty(shoppingCartDtos)) {
                for (ShoppingCartDto cartDto : shoppingCartDtos) {
                    shoppingCount += cartDto.getCommodityNum();
                }
            }
            LoginHelper.setShoppingCount(request, shoppingCount);
            // 4、邮件通知商家
            if (emailAutoSend) {
                try {
                    emailServer.singleMailSend(toEmails, "您有新订单，请及时处理", map.get("mailContent"));
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e);
                }
            }

            Map<String, Object> data = new HashMap<>();
            data.put("orderFormId", map.get("orderFormId"));
            data.put("isOpen", isOpen);
            json.setData(data);

            message = MessageTipsCst.ORDERFORM_SUCCESS;
            type = MessageTipsCst.TYPE_SUCCES;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.ORDERFORM_FAILURE;
            type = MessageTipsCst.TYPE_ERROR;
        }
        json.setMessage(message);
        json.setType(type);
        return json;
    }

    /**
     * 订单查询
     *
     * @param request
     * @param orderFormDto
     * @return
     */
    @Deprecated
    @RequestMapping("/queryOrderFormList")
    public ModelAndView queryOrderFormList(HttpServletRequest request, OrderFormDto orderFormDto) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        PageHelper.startPage();
        orderFormDto.setUserId(loginUser.getUserId());
        orderFormDto.setDelFlag("0");
        List<OrderFormDto> list = orderFormService.selectAll(orderFormDto);
        PageInfo<OrderFormDto> page = new PageInfo<>(list);
        ModelAndView av = new ModelAndView();
        av.addObject("page", page);
        av.setViewName(PagePathCst.BASEPATH_ORDER_FORM + "queryOrderFormList");
        //查询条件--start
        av.addObject("id", orderFormDto.getId());
        av.addObject("status", orderFormDto.getStatus());
        //查询条件--end
        return av;
    }

    /**
     * 查询订单详细
     *
     * @param request
     * @param orderFormDetailDto
     * @return
     */
    @RequestMapping("/queryOrderFormDetailList")
    public ModelAndView queryOrderFormDetailList(HttpServletRequest request, OrderFormDetailDto orderFormDetailDto) {
        List<OrderFormDetailDto> orderFormDetailDtos = orderFormDetailService.selectAll(orderFormDetailDto);
        OrderFormDto orderFormDto = orderFormService.selectByPrimaryKey(new OrderFormDto(orderFormDetailDto.getOrderFormId()));
        ModelAndView av = new ModelAndView();
        av.addObject("orderFormId", orderFormDetailDto.getOrderFormId());
        av.addObject("handleType", orderFormDetailDto.getHandleType());
        av.addObject("pageNo", orderFormDetailDto.getPageNo());
        av.addObject("list", orderFormDetailDtos);
        av.addObject("orderForm", orderFormDto);
        av.setViewName(PagePathCst.BASEPATH_ORDER_FORM + "queryOrderFormDetailList");
        return av;
    }

    /**
     * 更新订单状态
     *
     * @param orderFormDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateOrderForm")
    public JsonResult updateOrderForm(HttpServletRequest request, OrderFormDto orderFormDto) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        String statusCode = orderFormDto.getStatusCode();
        String orderFormId = orderFormDto.getId();
        try {
            String status = PayStatusEunm.getStatus(statusCode);
            orderFormDto.setStatus(status);
            if (PayStatusEunm.DELETE_ORDER_FORM.getNameCode().equals(statusCode)) {//删除订单记录
                orderFormDto.setStatus(null);
                orderFormDto.setDelFlag("1");
            }
            orderFormDto.setUpdateDate(new Date());
            orderFormService.updateOrderForm(orderFormDto);
            if (PayStatusEunm.DELETE_ORDER_FORM.getNameCode().equals(statusCode)) {//删除订单记录
                message = MessageTipsCst.DELETE_SUCCESS;
            } else if (PayStatusEunm.BUSINESS_CONFIRMATION.getNameCode().equals(statusCode)) {//确认订单
                message = "确认订单成功！";
                //移除自动确认订单队列中的任务
                String typeCode = OrderFormTaskQueueEunm.ORDER_FORM_AUTO_CONFIRMATION.getTypeCode();
                new OrderFormTaskQueue(typeCode, orderFormId).endOrderFormTask();
            } else if (PayStatusEunm.CONFIRMATION_GOODS_RECEIPT.getNameCode().equals(statusCode)) {//确认收货
                message = "确认收货成功！";
            } else if (PayStatusEunm.CONSIGNMENT.getNameCode().equals(statusCode)) {//发货中
                //移除自动发货中队列中的任务
                String typeCode = OrderFormTaskQueueEunm.ORDER_FORM_AUTO_CONSIGNMENT.getTypeCode();
                new OrderFormTaskQueue(typeCode, orderFormId).endOrderFormTask();
            } else if (PayStatusEunm.CUSTOMER_CANCELLATION.getNameCode().equals(statusCode)) {//客户取消
                message = MessageTipsCst.ORDERFORM_CANCEL_SUCCESS;
                if (businessCancel) {
                    //邮件通知
                    try {
                        StringBuilder mailContent = new StringBuilder();
                        mailContent.append("客户：");
                        mailContent.append(loginUser.getUserName());
                        mailContent.append("<br>");
                        mailContent.append("手机：");
                        mailContent.append(loginUser.getPhoneNumber());
                        mailContent.append("<br>");
                        mailContent.append("订单号：");
                        mailContent.append(orderFormDto.getId());
                        mailContent.append("<br>");
                        mailContent.append("如果想要了解详细情况请与客户联系！");
                        mailContent.append("<br><br><br>");
                        emailServer.singleMailSend(toEmails, "客户取消了您的订单！", mailContent.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error(e);
                    }
                }
            } else if (PayStatusEunm.BUSINESS_CANCELLATION.getNameCode().equals(statusCode)) {//商家取消
                message = MessageTipsCst.ORDERFORM_CANCEL_SUCCESS;
                //邮件通知
                try {
                    if (customerCancel) {
                        String mailContent = "订单号：" + orderFormDto.getId()
                                + "<br> 非常抱歉你的订单被商家取消了，如果有疑问请与商家联系!<br><br><br>";
                        emailServer.singleMailSend(loginUser.getMail(), "商家取消了您的订单！", mailContent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e);
                }
            } else {
                message = PayStatusEunm.getNameByNameCode(orderFormDto.getStatusCode());
            }
            type = MessageTipsCst.TYPE_SUCCES;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (PayStatusEunm.DELETE_ORDER_FORM.getNameCode().equals(statusCode)) {//删除订单记录
                message = MessageTipsCst.DELETE_FAILURE;
            } else {
                message = "订单状态修改失败";
            }
            type = MessageTipsCst.TYPE_ERROR;
        }
        json.setMessage(message);
        json.setType(type);
        return json;
    }

    /**
     * 订单处理查询
     *
     * @param request
     * @param orderFormDto
     * @return
     */
    @RequestMapping("/queryOrderFormHandleList")
    public ModelAndView queryOrderFormHandleList(HttpServletRequest request, OrderFormDto orderFormDto) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        PageHelper.startPage();
        //orderFormDto.setUserId(loginUser.getUserId());
        //orderFormDto.setDelFlag("0");
        orderFormDto.setIsCustomer(false);
        List<OrderFormDto> list = orderFormService.selectAllAndOptionButton(orderFormDto);
        PageInfo<OrderFormDto> page = new PageInfo<>(list);
        ModelAndView av = new ModelAndView();
        av.addObject("page", page);
        av.setViewName(PagePathCst.BASEPATH_ORDER_FORM + "queryOrderFormHandleList");
        //查询条件--start
        av.addObject("id", orderFormDto.getId());
        av.addObject("status", orderFormDto.getStatus());
        //查询条件--end
        return av;
    }
}