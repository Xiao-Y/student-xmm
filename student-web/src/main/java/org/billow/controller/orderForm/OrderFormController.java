package org.billow.controller.orderForm;

import org.apache.log4j.Logger;
import org.billow.api.orderForm.OrderFormService;
import org.billow.common.login.LoginHelper;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.UserDto;
import org.billow.utils.constant.MessageTipsCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public JsonResult saveOrderForm(HttpServletRequest request, @RequestParam(value = "addressId") String addressId,
                                    @RequestParam(value = "commodityIds[]") String[] commodityIds,
                                    @RequestParam(value = "commodityNums[]") String[] commodityNums) {
        JsonResult json = new JsonResult();
        HttpSession session = request.getSession();
        UserDto loginUser = LoginHelper.getLoginUser(session);
        String message = "";
        String type = "";
        try {
            orderFormService.saveOrderForm(loginUser, addressId,commodityIds, commodityNums);
            message = MessageTipsCst.COMMODITY_SUCCESS;
            type = MessageTipsCst.TYPE_SUCCES;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.COMMODITY_FAILURE;
            type = MessageTipsCst.TYPE_ERROR;
        }
        json.setMessage(message);
        json.setType(type);
        return json;
    }
}  