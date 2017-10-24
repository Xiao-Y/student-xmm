package org.billow.controller.orderForm;

import org.billow.utils.constant.PagePathCst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 我的收货地址
 *
 * @author liuyongtao
 * @create 2017-10-24 14:49
 */
@Controller
@RequestMapping("/myAddress")
public class MyAddressController {

    @RequestMapping("/myAddressView")
    public String myAddressView(HttpServletRequest request) {
        return PagePathCst.BASEPATH_ORDER_FORM + "myAddressView";
    }
}
