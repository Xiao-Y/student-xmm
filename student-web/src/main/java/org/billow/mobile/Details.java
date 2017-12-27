package org.billow.mobile;

import org.apache.log4j.Logger;
import org.billow.api.commodity.CommodityService;
import org.billow.api.orderForm.ShoppingCartService;
import org.billow.common.login.LoginHelper;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.CommodityDto;
import org.billow.model.expand.ShoppingCartDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.ToolsUtils;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.image.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 商品详细
 *
 * @author liuyongtao
 * @create 2017-12-15 14:43
 */
@Controller
@RequestMapping("/mb/details")
public class Details {
    private static final Logger logger = Logger.getLogger(Details.class);

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private CommodityService commodityService;

    /**
     * 获取单个商品
     *
     * @param commodity
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCommodityById")
    public CommodityDto getCommodityById(HttpServletRequest request, CommodityDto commodity) {
        String contextPath = request.getSession().getServletContext().getContextPath();
        CommodityDto commodityDto = commodityService.selectByPrimaryKey(commodity);
        if (commodityDto != null) {
            commodityDto.setImg(ImageUtils.getImgPath(commodityDto.getImg()));
        }
        return commodityDto;
    }

    /**
     * 获取购物车商品的数量
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/shoppingCount")
    public int shoppingCount(HttpServletRequest request) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        int shoppingCount = 0;
        //查询购物车总数量
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setId(loginUser.getUserId().toString());
        List<ShoppingCartDto> shoppingCartDtos = shoppingCartService.selectAll(shoppingCartDto);
        if (ToolsUtils.isNotEmpty(shoppingCartDtos)) {
            for (ShoppingCartDto cartDto : shoppingCartDtos) {
                shoppingCount += cartDto.getCommodityNum();
            }
        }
        return shoppingCount;
    }

    /**
     * 商品添加到购物车,{id}商品id,购物车主键为用户id
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/addShoppingCart")
    public JsonResult addShoppingCart(HttpServletRequest request, ShoppingCartDto shoppingCartDto) {
        HttpSession session = request.getSession();
        UserDto userDto = LoginHelper.getLoginUser(session);
        JsonResult json = new JsonResult();
        String message = "";
        boolean success = true;
        try {
            int shoppingCount = shoppingCartService.addShoppingCart(shoppingCartDto, userDto);
            LoginHelper.setShoppingCount(request, shoppingCount);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.COMMODITY_FAILURE;
            success = false;
        }
        json.setMessage(message);
        json.setSuccess(success);
        return json;
    }
}
