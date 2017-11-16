package org.billow.controller.fg;

import com.github.pagehelper.PageInfo;
import org.billow.api.commodity.CommodityService;
import org.billow.api.orderForm.AddressService;
import org.billow.api.orderForm.OrderFormDetailService;
import org.billow.api.orderForm.OrderFormService;
import org.billow.api.orderForm.ShoppingCartService;
import org.billow.common.login.LoginHelper;
import org.billow.model.expand.AddressDto;
import org.billow.model.expand.CommodityDto;
import org.billow.model.expand.OrderFormDetailDto;
import org.billow.model.expand.OrderFormDto;
import org.billow.model.expand.ShoppingCartDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.PageHelper;
import org.billow.utils.ToolsUtils;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/fg/fgHome")
public class FgHome {

    @Autowired
    private CommodityService commodityService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderFormService orderFormService;
    @Autowired
    private OrderFormDetailService orderFormDetailService;

    /**
     * 商城首页
     *
     * @param commodityDto
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, CommodityDto commodityDto) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        HttpSession session = request.getSession();

        commodityDto.setDeleFlag("1");
        commodityDto.setStatus("1");
        //首页显示
        ModelAndView av = new ModelAndView();
        //最新商品
        PageHelper.startPage(8);
        commodityDto.setObjectOrderBy("new");
        List<CommodityDto> newList = commodityService.selectAll(commodityDto);
        av.addObject("newList", newList);
        //热销商品
        PageHelper.startPage(8);
        commodityDto.setObjectOrderBy("hot");
        List<CommodityDto> hotList = commodityService.selectAll(commodityDto);
        av.addObject("hotList", hotList);

        //查询购物车总数量
        int shoppingCount = 0;
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setId(loginUser.getUserId().toString());
        List<ShoppingCartDto> shoppingCartDtos = shoppingCartService.selectAll(shoppingCartDto);
        if (ToolsUtils.isNotEmpty(shoppingCartDtos)) {
            for (ShoppingCartDto cartDto : shoppingCartDtos) {
                shoppingCount += cartDto.getCommodityNum();
            }
        }
        LoginHelper.setShoppingCount(request, shoppingCount);
        av.setViewName("/page/fg/index");
        return av;
    }

    /**
     * 商品列表页面
     *
     * @param commodityDto
     * @return
     */
    @RequestMapping("/shop")
    public ModelAndView shop(CommodityDto commodityDto) {
        commodityDto.setDeleFlag("1");
        commodityDto.setStatus("1");
        ModelAndView av = new ModelAndView();
        PageHelper.startPage(12);
        List<CommodityDto> commodityList = commodityService.selectAll(commodityDto);
        PageInfo<CommodityDto> page = new PageInfo<>(commodityList);
        av.addObject("page", page);
        av.addObject("commodityDto", commodityDto);
        av.setViewName("/page/fg/shop");
        return av;
    }

    /**
     * 查看购物车
     *
     * @param request
     * @return
     */
    @RequestMapping("/shoppingCart")
    public ModelAndView shoppingCart(HttpServletRequest request) {
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
        av.addObject("addressDtos", addressDtos);
        av.setViewName("/page/fg/shopping_cart");
        return av;
    }

    /**
     * 查看订单信息
     *
     * @param request
     * @param orderFormDto
     * @return
     */
    @RequestMapping("/order")
    public ModelAndView order(HttpServletRequest request, OrderFormDto orderFormDto) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        PageHelper.startPage(3);
        orderFormDto.setUserId(loginUser.getUserId());
        orderFormDto.setDelFlag("0");
        List<OrderFormDto> list = orderFormService.selectAll(orderFormDto);
        PageInfo<OrderFormDto> page = new PageInfo<>(list);
        ModelAndView av = new ModelAndView();
        av.addObject("page", page);
        av.setViewName("/page/fg/order");
        //查询条件--start
        av.addObject("id", orderFormDto.getId());
        av.addObject("status", orderFormDto.getStatus());
        //查询条件--end
        return av;
    }

    @RequestMapping("/orderDetail")
    public ModelAndView orderDetail(HttpServletRequest request, OrderFormDetailDto orderFormDetailDto) {
        List<OrderFormDetailDto> orderFormDetailDtos = orderFormDetailService.selectAll(orderFormDetailDto);
        OrderFormDto orderFormDto = orderFormService.selectByPrimaryKey(new OrderFormDto(orderFormDetailDto.getOrderFormId()));
        ModelAndView av = new ModelAndView();
        av.addObject("orderFormId", orderFormDetailDto.getOrderFormId());
        av.addObject("handleType", orderFormDetailDto.getHandleType());
        av.addObject("pageNo", orderFormDetailDto.getPageNo());
        av.addObject("list", orderFormDetailDtos);
        av.addObject("orderForm", orderFormDto);
        av.setViewName("/page/fg/order_detail");
        return av;
    }

    @RequestMapping("/userInfo")
    public String userInfo(HttpServletRequest request, UserDto userDto) {
        return "/page/fg/user_info";
    }

    @RequestMapping("/address")
    public String address(HttpServletRequest request, AddressDto addressDto) {
        return "/page/fg/address";
    }

    @RequestMapping("/addressEdit")
    public String addressEdit(HttpServletRequest request, AddressDto addressDto) {
        return "/page/fg/address_edit";
    }

    /**
     * 进入商品Modal
     *
     * @param commodity
     * @return
     */
    @ResponseBody
    @RequestMapping("/procuctModal")
    public CommodityDto procuctModal(CommodityDto commodity) {
        CommodityDto commodityDto = new CommodityDto();
        commodity.setValid("1");
        commodity.setStatus("1");
        List<CommodityDto> commodityDtos = commodityService.selectAll(commodity);
        if (ToolsUtils.isNotEmpty(commodityDtos)) {
            commodityDto = commodityDtos.get(0);
        }
        return commodityDto;
    }
}
