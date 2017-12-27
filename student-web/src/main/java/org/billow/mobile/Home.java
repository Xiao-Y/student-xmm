package org.billow.mobile;

import org.apache.log4j.Logger;
import org.billow.api.commodity.CommodityService;
import org.billow.api.user.UserService;
import org.billow.common.login.LoginHelper;
import org.billow.model.expand.CommodityDto;
import org.billow.model.expand.UserDto;
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
 * 主页
 *
 * @author liuyongtao
 * @create 2017-12-12 16:40
 */
@Controller
@RequestMapping("/mb/home")
public class Home {
    private static final Logger logger = Logger.getLogger(Home.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CommodityService commodityService;


    @ResponseBody
    @RequestMapping("/login")
    public UserDto login(HttpServletRequest request, String code) {
        System.out.println(code);
        //Map<String, String> map = WeChatPayApi.getOpenIdAndSessionKey(code);
        //System.out.println(map);

        UserDto user = null;
//        Object openID = map.get("openid");
//        System.out.println(openID);
//        if (openID != null && !"".equals(openID)) {
//            // 通过openID获取user对象
//            user = userService.getUserByOpenId(openID.toString());
//        }

        user = userService.findUserByUserName("billow");
        String sessionId = LoginHelper.setLoginUser(request, user);
        user.setSessionId(sessionId);
        return user;
    }

    /**
     * 获取最新商品
     */
    @ResponseBody
    @RequestMapping("/getCommodityNewList")
    public List<CommodityDto> getCommodityNewList(HttpServletRequest request, CommodityDto commodityDto) {
        commodityDto.setDeleFlag("1");
        commodityDto.setStatus("1");
        commodityDto.setValid("1");
        //首页显示最新商品
        PageHelper.startPage(8);
        commodityDto.setObjectOrderBy("new");
        List<CommodityDto> newList = commodityService.selectAll(commodityDto);
        if (ToolsUtils.isNotEmpty(newList)) {
            for (CommodityDto dto : newList) {
                //获取商品图片的名称
                dto.setImg(ImageUtils.getImgPath(dto.getImg()));
            }
        }
        return newList;
    }

    /**
     * 获取最热商品
     */
    @ResponseBody
    @RequestMapping("/getCommodityHotList")
    public List<CommodityDto> getCommodityHotList(HttpServletRequest request, CommodityDto commodityDto) {
        commodityDto.setDeleFlag("1");
        commodityDto.setStatus("1");
        commodityDto.setValid("1");
        //首页显示最新商品
        PageHelper.startPage(4);
        commodityDto.setObjectOrderBy("hot");
        List<CommodityDto> hotList = commodityService.selectAll(commodityDto);
        if (ToolsUtils.isNotEmpty(hotList)) {
            for (CommodityDto dto : hotList) {
                //获取商品图片的名称
                dto.setImg(ImageUtils.getImgPath(dto.getImg()));
            }
        }
        return hotList;
    }
}
