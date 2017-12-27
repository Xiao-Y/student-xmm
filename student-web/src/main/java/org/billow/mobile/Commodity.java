package org.billow.mobile;

import com.github.pagehelper.PageInfo;
import org.billow.api.commodity.CommodityService;
import org.billow.model.expand.CommodityDto;
import org.billow.utils.PageHelper;
import org.billow.utils.ToolsUtils;
import org.billow.utils.image.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商品列表
 *
 * @author liuyongtao
 * @create 2017-12-18 15:59
 */
@Controller
@RequestMapping("/mb/commodity")
public class Commodity {

    @Autowired
    private CommodityService commodityService;

    /**
     * 商品列表页面
     *
     * @param commodityDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/shop")
    public PageInfo<CommodityDto> shop(HttpServletRequest request, CommodityDto commodityDto) {
        String contextPath = request.getSession().getServletContext().getContextPath();
        commodityDto.setDeleFlag("1");
        commodityDto.setValid("1");
        PageHelper.startPage(6);
        List<CommodityDto> commodityList = commodityService.selectAll(commodityDto);
        if (ToolsUtils.isNotEmpty(commodityList)) {
            for (CommodityDto dto : commodityList) {
                //获取商品图片的名称
                dto.setImg(ImageUtils.getImgPath(dto.getImg()));
            }
        }
        PageInfo<CommodityDto> page = new PageInfo<>(commodityList);
        return page;
    }
}
