package org.billow.controller.fg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fg/fgHome")
public class FgHome {

    @RequestMapping("/index")
    public String index() {
        return "/page/fg/index";
    }

    @RequestMapping("/shop")
    public String shop() {
        return "/page/fg/shop";
    }

    @RequestMapping("/shoppingCart")
    public String shoppingCart() {
        return "/page/fg/shopping_cart";
    }

    @RequestMapping("/order")
    public String order() {
        return "/page/fg/order";
    }

    @RequestMapping("/orderDetail")
    public String orderDetail() {
        return "/page/fg/order_detail";
    }

    @RequestMapping("/userInfo")
    public String userInfo() {
        return "/page/fg/user_info";
    }

    @RequestMapping("/address")
    public String address() {
        return "/page/fg/address";
    }

    @RequestMapping("/addressEdit")
    public String addressEdit() {
        return "/page/fg/address_edit";
    }
}
