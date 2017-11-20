package org.billow.controller.fg;

import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.billow.api.commodity.CommodityService;
import org.billow.api.orderForm.AddressService;
import org.billow.api.orderForm.OrderFormDetailService;
import org.billow.api.orderForm.OrderFormService;
import org.billow.api.orderForm.ShoppingCartService;
import org.billow.api.user.UserService;
import org.billow.common.login.LoginHelper;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.AddressDto;
import org.billow.model.expand.CommodityDto;
import org.billow.model.expand.OrderFormDetailDto;
import org.billow.model.expand.OrderFormDto;
import org.billow.model.expand.ShoppingCartDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.PageHelper;
import org.billow.utils.ToolsUtils;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.generator.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/fg/fgHome")
public class FgHome {

    private static final Logger logger = Logger.getLogger(FgHome.class);

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
    @Autowired
    private UserService userService;
    @Value("${commodity.shop.pageSize}")
    private int shopPageSize;

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
        commodityDto.setValid("1");

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
        commodityDto.setValid("1");
        ModelAndView av = new ModelAndView();
        PageHelper.startPage(shopPageSize);
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
        PageHelper.startPage(6);
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

    /**
     * 查询订单详细
     *
     * @param request
     * @param orderFormDetailDto
     * @return
     */
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

    /**
     * 获取当前登陆用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/userInfo")
    public ModelAndView userInfo(HttpServletRequest request) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        UserDto userDto = userService.findUserById(loginUser.getUserId());
        ModelAndView av = new ModelAndView();
        av.addObject("user", userDto);
        av.setViewName("/page/fg/user_info");
        return av;
    }

    /**
     * 更新用户信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUserInfo")
    public JsonResult updateUserInfo(HttpServletRequest request, UserDto userDto) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        try {
            userDto.setUserId(loginUser.getUserId());
            userService.updateByPrimaryKeySelective(userDto);
            message = MessageTipsCst.UPDATE_SUCCESS;
            type = MessageTipsCst.TYPE_SUCCES;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.UPDATE_FAILURE;
            type = MessageTipsCst.TYPE_ERROR;
        }
        json.setMessage(message);
        json.setType(type);
        return json;
    }

    /**
     * 进入更新密码页面
     *
     * @return
     */
    @RequestMapping("/editPassword")
    public String editPassword() {
        return "/page/fg/password_edit";
    }

    /**
     * 更新密码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updatePassword")
    public JsonResult updatePassword(HttpServletRequest request, UserDto userDto) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        try {
            UserDto query = new UserDto();
            query.setUserName(loginUser.getUserName());
            query.setPassword(userDto.getOldPassword());
            //校验密码是否正确
            UserDto temp = userService.findUserByUserNameAndPwd(query);
            if (temp != null) {
                userDto.setUserId(loginUser.getUserId());
                userService.updateByPrimaryKeySelective(userDto);
                message = MessageTipsCst.HOME_AGAIN_LOGIN;
                type = MessageTipsCst.TYPE_SUCCES;
            } else {
                message = MessageTipsCst.UPDATE_PASSWORD_ERROR;
                type = MessageTipsCst.TYPE_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.UPDATE_FAILURE;
            type = MessageTipsCst.TYPE_ERROR;
        }
        json.setMessage(message);
        json.setType(type);
        json.setRoot("/home/login");
        return json;
    }

    /**
     * 收货地址列表
     *
     * @param request
     * @param addressDto
     * @return
     */
    @RequestMapping("/address")
    public ModelAndView address(HttpServletRequest request, AddressDto addressDto) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        addressDto.setUserId(loginUser.getUserId());
        List<AddressDto> list = addressService.selectAll(addressDto);
        ModelAndView av = new ModelAndView();
        av.addObject("addressDtos", list);
        av.setViewName("/page/fg/address");
        return av;
    }

    /**
     * 进入收货地址编辑/添加页面
     *
     * @param request
     * @param addressDto
     * @return
     */
    @RequestMapping("/editAddress")
    public ModelAndView editAddress(HttpServletRequest request, AddressDto addressDto) {
        ModelAndView av = new ModelAndView();
        String viewName = "/page/fg/address_edit";
        UserDto loginUser = LoginHelper.getLoginUser(request);
        addressDto.setUserId(loginUser.getUserId());
        String type = addressDto.getType();
        if ("add".equals(type)) {
            int count = addressService.selectAllCount(addressDto);
            if (count >= 4) {
                viewName = "redirect:/fg/fgHome/address";
            }
        } else {
            AddressDto dto = addressService.selectByPrimaryKey(addressDto);
            av.addObject("address", dto);
        }
        av.setViewName(viewName);
        av.addObject("type", type);
        return av;
    }

    /**
     * 保存/更新收货地址
     *
     * @param request
     * @param addressDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveAddress")
    public JsonResult saveAddress(HttpServletRequest request, AddressDto addressDto) {
        String type = addressDto.getType();
        JsonResult json = new JsonResult();
        String message = "";
        String typeJ = "";
        try {
            UserDto loginUser = LoginHelper.getLoginUser(request);
            addressDto.setUserId(loginUser.getUserId());
            if ("add".equals(type)) {
                addressDto.setId(UUID.generate());
                addressDto.setUpdateTime(new Date());
                addressDto.setCreateTime(new Date());
                addressService.insert(addressDto);
                message = MessageTipsCst.SAVE_SUCCESS;
            } else {
                addressDto.setUpdateTime(new Date());
                addressService.updateByPrimaryKeySelective(addressDto);
                message = MessageTipsCst.UPDATE_SUCCESS;
            }
            typeJ = MessageTipsCst.TYPE_SUCCES;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if ("add".equals(type)) {
                message = MessageTipsCst.SAVE_FAILURE;
            } else {
                message = MessageTipsCst.UPDATE_FAILURE;
            }
            typeJ = MessageTipsCst.TYPE_ERROR;
        }
        json.setMessage(message);
        json.setType(typeJ);
        json.setRoot("/fg/fgHome/address");
        return json;
    }

    /**
     * 删除收货地址
     *
     * @param request
     * @param addressDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteAddress")
    public JsonResult deleteAddress(HttpServletRequest request, AddressDto addressDto) {
        JsonResult json = new JsonResult();
        String message = "";
        String typeJ = "";
        try {
            addressService.deleteByPrimaryKey(addressDto);
            message = MessageTipsCst.DELETE_SUCCESS;
            typeJ = MessageTipsCst.TYPE_SUCCES;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.DELETE_FAILURE;
            typeJ = MessageTipsCst.TYPE_ERROR;
        }
        json.setMessage(message);
        json.setType(typeJ);
        return json;
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
        List<CommodityDto> commodityDtos = commodityService.selectAll(commodity);
        if (ToolsUtils.isNotEmpty(commodityDtos)) {
            commodityDto = commodityDtos.get(0);
        }
        return commodityDto;
    }
}
