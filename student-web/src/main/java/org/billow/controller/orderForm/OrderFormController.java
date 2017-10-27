package org.billow.controller.orderForm;

import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.billow.api.orderForm.OrderFormDetailService;
import org.billow.api.orderForm.OrderFormService;
import org.billow.common.email.EmailServer;
import org.billow.common.login.LoginHelper;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.AddressDto;
import org.billow.model.expand.CommodityDto;
import org.billow.model.expand.OrderFormDetailDto;
import org.billow.model.expand.OrderFormDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.PageHelper;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private EmailServer emailServer;
    @Value("${mail.auto.send}")
    private boolean emailAutoSend;
    @Value("${mail.auto.default.to}")
    private String toEmails;

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
            Map<String, String> map = orderFormService.saveOrderForm(loginUser, addressId, commodityIds, commodityNums);
            // 4、邮件通知商家
            if (emailAutoSend) {
                try {
                    emailServer.singleMailSend(toEmails, "您有新订单，请及时处理", map.get("mailContent"));
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e);
                }
            }
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
        ModelAndView av = new ModelAndView();
        av.addObject("orderFormId", orderFormDetailDto.getOrderFormId());
        av.addObject("list", orderFormDetailDtos);
        av.setViewName(PagePathCst.BASEPATH_ORDER_FORM + "queryOrderFormDetailList");
        return av;
    }

    @ResponseBody
    @RequestMapping("/updateOrderForm")
    public JsonResult updateOrderForm(OrderFormDto orderFormDto) {
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        try {
            orderFormService.updateByPrimaryKeySelective(orderFormDto);
            if ("1".equals(orderFormDto.getDelFlag())) {
                message = MessageTipsCst.DELETE_SUCCESS;
            } else if ("3".equals(orderFormDto.getStatus())) {
                message = MessageTipsCst.ORDERFORM_CANCEL_SUCCESS;
            }
            type = MessageTipsCst.TYPE_SUCCES;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if ("1".equals(orderFormDto.getDelFlag())) {
                message = MessageTipsCst.DELETE_FAILURE;
            } else if ("3".equals(orderFormDto.getStatus())) {
                message = MessageTipsCst.ORDERFORM_CANCEL_FAILURE;
            }
            type = MessageTipsCst.TYPE_ERROR;
        }
        json.setMessage(message);
        json.setType(type);
        return json;
    }
}  