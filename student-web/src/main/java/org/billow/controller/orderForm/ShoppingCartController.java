package org.billow.controller.orderForm;

import org.apache.log4j.Logger;
import org.billow.api.orderForm.ShoppingCartService;
import org.billow.common.login.LoginHelper;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.ShoppingCartDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 购物车控制器<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-20 15:35:00
 */
@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    private static final Logger logger = Logger.getLogger(ShoppingCartController.class);

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 商品添加到购物车,{id}商品id,购物车主键为用户id
     *
     * @param request
     * @param id      商品id
     * @return
     */
    @ResponseBody
    @RequestMapping("/addShoppingCart/{id}")
    public JsonResult addShoppingCart(HttpServletRequest request, @PathVariable("id") String id) {
        HttpSession session = request.getSession();
        UserDto userDto = LoginHelper.getLoginUser(session);
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        if (userDto == null) {
            json.setMessage("您还没有登陆,请登陆!");
            json.setType(MessageTipsCst.TYPE_ERROR);
            return json;
        }
        try {
            shoppingCartService.addShoppingCart(id, userDto);
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

    /**
     * 查看我的购物车
     *
     * @param request
     * @return
     */
    @RequestMapping("/myShoppingCart")
    public ModelAndView myShoppingCart(HttpServletRequest request) {
        ModelAndView av = new ModelAndView();
        HttpSession session = request.getSession();
        UserDto userDto = LoginHelper.getLoginUser(session);
        List<ShoppingCartDto> list = shoppingCartService.myShoppingCart(userDto);
        av.addObject("list", list);
        av.setViewName(PagePathCst.BASEPATH_ORDER_FORM + "myShoppingCart");
        return av;
    }
}