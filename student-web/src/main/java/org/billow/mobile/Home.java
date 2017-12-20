package org.billow.mobile;

import org.apache.log4j.Logger;
import org.billow.api.commodity.CommodityService;
import org.billow.api.user.UserService;
import org.billow.common.login.LoginHelper;
import org.billow.model.expand.CommodityDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.PageHelper;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    //商品图片路径
    @Value("${commodity.img.upload}")
    private String upload;
    @Value("${system.domain.name}")
    private String systemDomainName;
    @Value("${weichat.appid}")
    public String appid;
    @Value("${weichat.appsecret}")
    public String appsecret;
    @Autowired
    private UserService userService;
    @Autowired
    private CommodityService commodityService;


    @ResponseBody
    @RequestMapping("/login")
    public UserDto login(HttpServletRequest request, String code) {
        System.out.println(code);
//        UserDto user = null;
//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
//        String param = "appid=" + appid + "&secret=" + appsecret + "&grant_type=authorization_code&code=" + code;
//        Gson gson = new Gson();
//        Map<String, String> map = gson.fromJson(HttpRequest.sendGet(url, param), new TypeToken<Map<String, String>>() {
//        }.getType());
//        Object openID = map.get("openid");
//        System.out.println(openID);
//        if (openID != null && !"".equals(openID)) {
//            // 通过openID获取user对象
//            user = userService.getUserByOpenId(openID.toString());
//        }
        UserDto user = userService.findUserByUserName("billow");
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
        String contextPath = request.getSession().getServletContext().getContextPath();
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
                String img = systemDomainName + contextPath + "/" + upload + "/" + dto.getImg();
                dto.setImg(img);
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
        String contextPath = request.getSession().getServletContext().getContextPath();
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
                String img = systemDomainName + contextPath + "/" + upload + "/" + dto.getImg();
                dto.setImg(img);
            }
        }
        return hotList;
    }
}
