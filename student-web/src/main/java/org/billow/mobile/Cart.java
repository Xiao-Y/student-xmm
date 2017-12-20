package org.billow.mobile;

import org.apache.log4j.Logger;
import org.billow.api.orderForm.ShoppingCartService;
import org.billow.common.login.LoginHelper;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.CommodityDto;
import org.billow.model.expand.ShoppingCartDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.ToolsUtils;
import org.billow.utils.constant.MessageTipsCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 购物车
 *
 * @author liuyongtao
 * @create 2017-12-15 15:50
 */
@Controller
@RequestMapping("/mb/cart")
public class Cart {
    private static final Logger logger = Logger.getLogger(Cart.class);

    //商品图片路径
    @Value("${commodity.img.upload}")
    private String upload;
    @Value("${system.domain.name}")
    private String systemDomainName;

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 查看我的购物车
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/myShoppingCart")
    public List<ShoppingCartDto> myShoppingCart(HttpServletRequest request) {
        String contextPath = request.getSession().getServletContext().getContextPath();
        UserDto userDto = LoginHelper.getLoginUser(request);
        List<ShoppingCartDto> list = shoppingCartService.myShoppingCart(userDto);
        if (ToolsUtils.isNotEmpty(list)) {
            for (ShoppingCartDto dto : list) {
                dto.setSelected(true);
                CommodityDto commodityDto = dto.getCommodityDto();
                //获取商品图片的名称
                String img = systemDomainName + contextPath + "/" + upload + "/" + commodityDto.getImg();
                commodityDto.setImg(img);
            }
        }
        return list;
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
        boolean success = true;
        try {
            ShoppingCartDto dto = new ShoppingCartDto();
            dto.setId(userDto.getUserId().toString());
            dto.setCommodityId(commodityId);
            shoppingCartService.deleteByPrimaryKey(dto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.DELETE_FAILURE;
            success = false;
        }
        json.setMessage(message);
        json.setSuccess(success);
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
    public JsonResult updateShoppingCartComNum(HttpServletRequest request, @PathVariable("commodityId") String commodityId,
                                               @PathVariable("commodityNum") Integer commodityNum) {
        JsonResult json = new JsonResult();
        HttpSession session = request.getSession();
        UserDto userDto = LoginHelper.getLoginUser(session);
        String message = "";
        boolean success = true;
        try {
            ShoppingCartDto dto = new ShoppingCartDto();
            dto.setId(userDto.getUserId().toString());
            dto.setCommodityId(commodityId);
            dto.setCommodityNum(commodityNum);
            shoppingCartService.updateByPrimaryKeySelective(dto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.UPDATE_FAILURE;
            success = false;
        }
        json.setMessage(message);
        json.setSuccess(success);
        return json;
    }
}
