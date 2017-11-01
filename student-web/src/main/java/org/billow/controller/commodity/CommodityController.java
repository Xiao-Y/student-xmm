package org.billow.controller.commodity;

import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.billow.api.commodity.CommodityService;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.CommodityDto;
import org.billow.utils.PageHelper;
import org.billow.utils.ToolsUtils;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.billow.utils.generator.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商品修改控制器<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-19 15:45:38
 */
@Controller
@RequestMapping("/commodity")
public class CommodityController {
    private static final Logger logger = Logger.getLogger(CommodityController.class);

    @Autowired
    private CommodityService commodityService;

    /**
     * 进入商品修改列表页面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(CommodityDto commodityDto) {
        ModelAndView av = new ModelAndView();
        PageHelper.startPage();
        commodityDto.setDeleFlag("1");
        List<CommodityDto> commodityList = commodityService.selectAll(commodityDto);
        PageInfo<CommodityDto> page = new PageInfo<>(commodityList);
        av.addObject("page", page);
        av.setViewName(PagePathCst.BASEPATH_COMMODITY + "commodityList");
        return av;
    }

    /**
     * 进入商品修改页面
     *
     * @param commodity
     * @return
     */
    @RequestMapping("/commodityEdit")
    public ModelAndView commodityEdit(CommodityDto commodity) {
        CommodityDto commodityDto = new CommodityDto();
        // 编辑时，显示数据
        if (ToolsUtils.isNotEmpty(commodity.getId())) {
            commodityDto = commodityService.selectByPrimaryKey(commodity);
        }
        ModelAndView av = new ModelAndView();
        // 用于修改后保持停留在页面
        commodityDto.setPageNo(commodity.getPageNo());
        av.addObject("commodity", commodityDto);
        av.setViewName(PagePathCst.BASEPATH_COMMODITY + "commodityEdit");
        return av;
    }

    /**
     * 保存商品信息
     *
     * @param commodityDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/commoditySave")
    public JsonResult commoditySave(CommodityDto commodityDto) {
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        try {
            if (ToolsUtils.isEmpty(commodityDto.getId())) {
                commodityDto.setId(UUID.generate());
                commodityService.insert(commodityDto);
            } else {
                commodityService.updateByPrimaryKeySelective(commodityDto);
            }
            message = MessageTipsCst.SUBMIT_SUCCESS;
            type = MessageTipsCst.TYPE_SUCCES;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.SUBMIT_FAILURE;
            type = MessageTipsCst.TYPE_ERROR;
        }
        json.setMessage(message);
        json.setType(type);
        json.setRoot("/commodity/index?pageNo=" + commodityDto.getPageNo());
        return json;
    }

    /**
     * 逻辑删除
     *
     * @param commodityDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/commodityDel")
    public JsonResult commodityDel(CommodityDto commodityDto) {
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        try {
            commodityDto.setDeleFlag("0");
            commodityService.updateByPrimaryKeySelective(commodityDto);
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
     * 进入商品选购页面
     *
     * @return
     */
    @RequestMapping("/commodityView")
    public ModelAndView commodityView(CommodityDto commodityDto, HttpServletRequest request) {
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