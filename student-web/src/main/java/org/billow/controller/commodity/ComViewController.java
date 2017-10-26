package org.billow.controller.commodity;

import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.billow.api.commodity.CommodityService;
import org.billow.model.expand.CommodityDto;
import org.billow.utils.PageHelper;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商品预览（商家）
 *
 * @author liuyongtao
 * @create 2017-10-20 10:13
 */
@Controller
@RequestMapping("/comView")
public class ComViewController {

    @Autowired
    private CommodityService commodityService;

    /**
     * 进入商品修改列表页面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(CommodityDto commodityDto, HttpServletRequest request) {
        ModelAndView av = new ModelAndView();
        PageHelper.startPage(12);
        commodityDto.setDeleFlag("1");
        commodityDto.setValid("1");
        List<CommodityDto> commodityList = commodityService.selectAll(commodityDto);
        PageInfo<CommodityDto> page = new PageInfo<>(commodityList);
        av.addObject("page", page);
        av.setViewName(PagePathCst.BASEPATH_COMMODITY + "commodityView");
        return av;
    }
}
