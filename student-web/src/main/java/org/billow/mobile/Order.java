package org.billow.mobile;

import com.github.pagehelper.PageInfo;
import org.billow.api.orderForm.OrderFormService;
import org.billow.common.login.LoginHelper;
import org.billow.model.expand.OrderFormDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.PageHelper;
import org.billow.utils.enumType.PayStatusEunm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private OrderFormService orderFormService;

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
        orderFormDto.setIsCustomer(false);
        orderFormDto.setQueryOrderFormDetail(true);
        List<OrderFormDto> list = orderFormService.selectAllAndOptionButton(orderFormDto);
        PageInfo<OrderFormDto> page = new PageInfo<>(list);
        return page;
    }
}
