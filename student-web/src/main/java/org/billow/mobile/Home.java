package org.billow.mobile;

import org.billow.api.commodity.CommodityService;
import org.billow.model.expand.CommodityDto;
import org.billow.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 主页
 *
 * @author liuyongtao
 * @create 2017-12-12 16:40
 */
@Controller
@RequestMapping("/mb/home")
public class Home {

    @Autowired
    private CommodityService commodityService;

    /**
     * 获取最新商品
     */
    @ResponseBody
    @RequestMapping("/getCommodityNewList")
    public List<CommodityDto> getCommodityNewList(CommodityDto commodityDto) {
        commodityDto.setDeleFlag("1");
        commodityDto.setStatus("1");
        commodityDto.setValid("1");
        //首页显示最新商品
        PageHelper.startPage(8);
        commodityDto.setObjectOrderBy("new");
        List<CommodityDto> newList = commodityService.selectAll(commodityDto);
        return newList;
    }

    /**
     * 获取最热商品
     */
    @ResponseBody
    @RequestMapping("/getCommodityHotList")
    public List<CommodityDto> getCommodityHotList(CommodityDto commodityDto) {
        commodityDto.setDeleFlag("1");
        commodityDto.setStatus("1");
        commodityDto.setValid("1");
        //首页显示最新商品
        PageHelper.startPage(8);
        commodityDto.setObjectOrderBy("hot");
        List<CommodityDto> hotList = commodityService.selectAll(commodityDto);
        return hotList;
    }
}
