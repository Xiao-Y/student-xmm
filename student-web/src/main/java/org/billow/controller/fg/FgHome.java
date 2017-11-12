package org.billow.controller.fg;

import com.github.pagehelper.PageInfo;
import org.billow.api.commodity.CommodityService;
import org.billow.model.expand.CommodityDto;
import org.billow.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/fg/fgHome")
public class FgHome {

    @Autowired
    private CommodityService commodityService;

    /**
     * 商城首页
     *
     * @param commodityDto
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(CommodityDto commodityDto) {
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
        av.setViewName("/page/fg/index");
        return av;
    }

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
