package org.billow.controller.orderForm;

import org.apache.log4j.Logger;
import org.billow.api.orderForm.AddressService;
import org.billow.api.orderForm.ShoppingCartService;
import org.billow.common.login.LoginHelper;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.AddressDto;
import org.billow.model.expand.ShoppingCartDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.ToolsUtils;
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
    @Autowired
    private AddressService addressService;

    /**
     * 商品添加到购物车,{id}商品id,购物车主键为用户id
     *
     * @param request
     * @param id      商品id
     * @return
     */
    @ResponseBody
    @RequestMapping("/addShoppingCart/{id}")
    public JsonResult addShoppingCart(HttpServletRequest request, @PathVariable("id") String id, ShoppingCartDto shoppingCartDto) {
        HttpSession session = request.getSession();
        UserDto userDto = LoginHelper.getLoginUser(session);
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        Long total = 0L;
        if (userDto == null) {
            json.setMessage("您还没有登陆,请登陆!");
            json.setType(MessageTipsCst.TYPE_ERROR);
            return json;
        }
        try {
            shoppingCartDto.setCommodityId(id);
            int shoppingCount = shoppingCartService.addShoppingCart(shoppingCartDto, userDto);
            LoginHelper.setShoppingCount(request, shoppingCount);
            total = new Long(shoppingCount);
            message = MessageTipsCst.COMMODITY_SUCCESS;
            type = MessageTipsCst.TYPE_SUCCES;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.COMMODITY_FAILURE;
            type = MessageTipsCst.TYPE_ERROR;
        }
        json.setTotal(total);
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
        AddressDto address = null;
        AddressDto addressDto = new AddressDto();
        addressDto.setUserId(userDto.getUserId());
        List<AddressDto> addressDtos = addressService.selectAll(addressDto);
        if (ToolsUtils.isNotEmpty(addressDtos)) {
            for (AddressDto dto : addressDtos) {
                if ("1".equals(dto.getStatus())) {
                    address = dto;
                    break;
                }
            }
            if (address == null) {
                address = addressDtos.get(0);
            }
        }
        av.addObject("list", list);
        av.addObject("address", address);
        av.setViewName(PagePathCst.BASEPATH_ORDER_FORM + "myShoppingCart");
        return av;
    }

    /**
     * 删除购物车信息
     *
     * @param commodityId 商品id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteShoppingCart/{commodityId}")
    public JsonResult deleteShoppingCart(HttpServletRequest request, @PathVariable("commodityId") String commodityId) {
        HttpSession session = request.getSession();
        UserDto userDto = LoginHelper.getLoginUser(session);
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        try {
            ShoppingCartDto dto = new ShoppingCartDto();
            dto.setId(userDto.getUserId().toString());
            dto.setCommodityId(commodityId);
            shoppingCartService.deleteByPrimaryKey(dto);
            message = MessageTipsCst.DELETE_SUCCESS;
            type = MessageTipsCst.TYPE_SUCCES;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.DELETE_FAILURE;
            type = MessageTipsCst.TYPE_ERROR;
        }
        json.setMessage(message);
        json.setType(type);
        return json;
    }

    /**
     * 更新购物车中的商品数量
     *
     * @param request
     * @param commodityId  商品id
     * @param commodityNum 商品数量
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateShoppingCartComNum/{commodityId}/{commodityNum}")
    public String updateShoppingCartComNum(HttpServletRequest request, @PathVariable("commodityId") String commodityId,
                                           @PathVariable("commodityNum") Integer commodityNum) {
        HttpSession session = request.getSession();
        UserDto userDto = LoginHelper.getLoginUser(session);
        try {
            ShoppingCartDto dto = new ShoppingCartDto();
            dto.setId(userDto.getUserId().toString());
            dto.setCommodityId(commodityId);
            dto.setCommodityNum(commodityNum);
            shoppingCartService.updateByPrimaryKeySelective(dto);
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return "1";
    }
}