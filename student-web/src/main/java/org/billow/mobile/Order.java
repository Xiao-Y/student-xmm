package org.billow.mobile;

import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.billow.api.orderForm.OrderFormService;
import org.billow.common.login.LoginHelper;
import org.billow.controller.orderForm.OrderFormController;
import org.billow.controller.orderForm.OrderFormPayLogController;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.OrderFormDetailDto;
import org.billow.model.expand.OrderFormDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.PageHelper;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.enumType.PayStatusEunm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单操作
 *
 * @author liuyongtao
 * @create 2017-12-22 17:14
 */
@Controller
@RequestMapping("/mb/order")
public class Order {

    private static final Logger logger = Logger.getLogger(Order.class);

    @Autowired
    private OrderFormService orderFormService;
    @Autowired
    private OrderFormController orderFormController;
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
     * 订单查询
     *
     * @param request
     * @param orderFormDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryOrderFormList")
    public PageInfo<OrderFormDto> queryOrderFormList(HttpServletRequest request, OrderFormDto orderFormDto) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        PageHelper.startPage();
        String status = orderFormDto.getStatus();
        if ("all".equals(status)) {
            orderFormDto.setStatus(null);
        } else {
            orderFormDto.setStatus(PayStatusEunm.getStatus(status));
        }
        orderFormDto.setUserId(loginUser.getUserId());
        orderFormDto.setDelFlag("0");
        orderFormDto.setIsCustomer(true);
        orderFormDto.setQueryOrderFormDetail(true);
        List<OrderFormDto> list = orderFormService.selectAllAndOptionButton(orderFormDto);
        PageInfo<OrderFormDto> page = new PageInfo<>(list);
        return page;
    }

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
    public JsonResult saveOrderForm(HttpServletRequest request, HttpServletResponse response, @RequestParam String addressId,
                                    @RequestBody List<OrderFormDetailDto> orderFormDetailDtos) {
        JsonResult json = new JsonResult();
        HttpSession session = request.getSession();
        UserDto loginUser = LoginHelper.getLoginUser(session);
        String message = "";
        boolean success = true;
        try {
            Map<String, String> map = orderFormService.saveOrderForm(response, loginUser, addressId, orderFormDetailDtos);

            Map<String, Object> data = new HashMap<>();
            data.put("orderFormId", map.get("orderFormId"));
            json.setData(data);

            message = MessageTipsCst.ORDERFORM_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.ORDERFORM_FAILURE;
            success = false;
        }
        json.setMessage(message);
        json.setSuccess(success);
        return json;
    }

    /**
     * 修改订单状态，订单号，订单状态
     *
     * @param request
     * @param orderFormDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateOrderForm")
    public JsonResult updateOrderForm(HttpServletRequest request, OrderFormDto orderFormDto) {
        request.setAttribute("orderFormService", orderFormService);
        JsonResult jsonResult = orderFormController.updateOrderForm(request, orderFormDto);
        return jsonResult;
    }
}
