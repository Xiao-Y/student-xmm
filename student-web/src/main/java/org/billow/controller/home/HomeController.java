package org.billow.controller.home;

import org.apache.log4j.Logger;
import org.billow.api.menu.MenuService;
import org.billow.api.user.UserService;
import org.billow.common.email.EmailServer;
import org.billow.common.login.LoginHelper;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.MenuDto;
import org.billow.model.expand.UserDto;
import org.billow.model.expand.UserRoleDto;
import org.billow.utils.ToolsUtils;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.generator.Md5Encrypt;
import org.billow.utils.generator.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private EmailServer emailServer;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;
    //客户角色
    @Value("${customerRole}")
    private String customerRole;

    /**
     * 登陆
     *
     * @return
     * @author XiaoY
     * @date: 2017年5月24日 下午10:32:24
     */
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request) {
        //先消除session
        UserDto loginUser = LoginHelper.getLoginUser(request);
        if (loginUser != null) {
            LoginHelper.removeUserSession(loginUser.getUserId());
        }

        String userName = "";
        String password = "";
        Cookie[] cookies = request.getCookies();
        //对cookies中的数据进行遍历，找到用户名
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if ("userName".equals(cookies[i].getName())) {
                    userName = cookies[i].getValue();
                }
            }
        }
        ModelAndView av = new ModelAndView();
        av.addObject("userName", userName);
        av.addObject("password", password);
        av.setViewName("page/home/login");
        return av;
    }

    /**
     * 进入主页，验证登陆
     *
     * @param userTemp
     * @return
     * @author XiaoY
     * @date: 2017年5月24日 下午10:32:33
     */
    @RequestMapping("/homeIndex")
    public ModelAndView homeIndex(UserDto userTemp, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) {
        ModelAndView av = new ModelAndView();
        //记住用户名
        if (userTemp.isRememberMe()) {
            try {
                Cookie userCo = new Cookie("userName", userTemp.getUserName());
                userCo.setMaxAge(60 * 60 * 24 * 30);
                response.addCookie(userCo);
            } catch (Exception e) {
                logger.error(e);
                e.printStackTrace();
            }
        }

        userTemp.setPassword(LoginHelper.md5PasswordTwo(userTemp.getPassword()));
        UserDto user = userService.findUserByUserNameAndPwd(userTemp);
        //验证的合法性
        if (user == null || user.getUserId() == null) {
            attr.addFlashAttribute("errorMsg", "用户名或密码错误！");
            av.setViewName("redirect:/home/login");
            return av;
        } else if ("0".equals(user.getVaild())) {
            attr.addFlashAttribute("errorMsg", "对不起，该用户没有通过验证，请进入邮箱验证！");
            av.setViewName("redirect:/home/login");
            return av;
        }

        LoginHelper.setLoginUser(request, user);
        String viewName = "redirect:/home/index";
        List<UserRoleDto> userRoleDtos = user.getUserRoleDtos();
        if (ToolsUtils.isNotEmpty(userRoleDtos)) {
            if (userRoleDtos.size() == 1) {
                if (customerRole.equals(userRoleDtos.get(0).getRoleDto().getRoleName())) {
                    //重定向，防止刷新跳出
                    viewName = "redirect:/fg/fgHome/index";
                }
            }
        } else {
            //重定向，防止刷新跳出
            viewName = "redirect:/fg/fgHome/index";
        }
        av.setViewName(viewName);
        return av;
    }

    /**
     * 显示主页
     *
     * @return
     * @author XiaoY
     * @date: 2017年6月18日 上午11:50:40
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, RedirectAttributes attr) {
        ModelAndView av = new ModelAndView();
        UserDto user = LoginHelper.getLoginUser(request);
        if (user == null || user.getUserId() == null) {
            attr.addFlashAttribute("errorMsg", "用户登陆超时，请重新登陆！");
            av.setViewName("redirect:/home/login");
            return av;
        }
        av.setViewName("page/home/index");
        return av;
    }

    /**
     * 显示主页
     *
     * @return
     * @author XiaoY
     * @date: 2017年6月18日 上午11:50:40
     */
    @RequestMapping("/main")
    public String main() {
        return "page/home/main";
    }

    /**
     * 显示菜单
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @return
     * @date 2017年4月24日 上午9:45:13
     */
    @ResponseBody
    @RequestMapping("/menu")
    public List<MenuDto> indexMenu(HttpServletRequest request) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        ServletContext servletContext = request.getServletContext();
        String contextPath = servletContext.getContextPath();
        List<MenuDto> menus = menuService.findMenu(loginUser, contextPath);
        return menus;
    }

    /**
     * 进入忘记密码页面
     *
     * @return
     */
    @RequestMapping("/forgetPwd")
    public String forgetPwd() {
        return "page/home/forgetPwd";
    }

    /**
     * 发送忘记密码链接到指定邮箱
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendMailLink")
    public JsonResult sendMailLink(UserDto user, HttpServletRequest request) {
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        try {
            UserDto userDto = userService.findUserByUserName(user.getUserName());
            if (userDto == null) {//用户不存在
                message = "此用户不存在，请查证！";
                type = MessageTipsCst.TYPE_ERROR;
            } else {
                //更新校验码
                String secretKey = UUID.generate();
                userDto.setSecretKey(secretKey);
                userService.updateByPrimaryKeySelective(userDto);
                String digitalSignature = Md5Encrypt.md5(secretKey);// 数字签名
                //发送邮件
                //http://localhost:8099/home/checkLink?sid=79d5d8e88c89db8214d5ef429af2a653&userName=admin
                StringBuffer emailContent = new StringBuffer();
                emailContent.append("请勿回复本邮件.点击下面的链接,重设密码<br>");
                emailContent.append("<a href=\"");
                emailContent.append(request.getScheme());
                emailContent.append("://");
                emailContent.append(request.getServerName());
                emailContent.append(":");
                emailContent.append(request.getServerPort());
                emailContent.append(request.getContextPath());
                emailContent.append("/home/");
                emailContent.append("checkLink?sid=");
                emailContent.append(digitalSignature);
                emailContent.append("&type=editPwd");
                emailContent.append("&userName=");
                emailContent.append(userDto.getUserName());
                emailContent.append("\" target='_blank'>点击我重新设置密码</a>");
                emailContent.append("<br><br>");
                logger.info(emailContent.toString());
                emailServer.singleMailSend(userDto.getMail(), "找回您的账户密码", emailContent.toString());
                message = MessageTipsCst.SEND_SUCCESS;
                type = MessageTipsCst.TYPE_SUCCES;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.SEND_FAILURE;
            type = MessageTipsCst.TYPE_ERROR;
        }
        json.setMessage(message);
        json.setType(type);
        json.setRoot("/home/login");
        return json;
    }

    /**
     * 检查邮件链接的正确性
     */
    @RequestMapping("/checkLink")
    public ModelAndView checkLink(@RequestParam("sid") String sid, @RequestParam("type") String type, @RequestParam("userName") String userName) {
        ModelAndView av = new ModelAndView();
        if (ToolsUtils.isNotEmpty(sid)) {
            UserDto userDto = userService.findUserByUserName(userName);
            if (userDto != null) {
                String secretKey = userDto.getSecretKey();
                if (ToolsUtils.isNotEmpty(secretKey)) {
                    String digitalSignature = Md5Encrypt.md5(secretKey);// 数字签名
                    if (digitalSignature.equals(sid)) {
                        if ("editPwd".equals(type)) {//修改密码
                            av.addObject("userName", userName);
                            av.addObject("sid", sid);
                            av.setViewName("page/home/editPwd");
                        } else if ("regist".equals(type)) {//注册验证
                            av.addObject("userName", userName);
                            av.setViewName("page/home/registSuccess");
                            userDto.setPassword(null);
                            userDto.setSecretKey("");
                            userDto.setVaild("1");
                            userService.updateByPrimaryKeySelective(userDto);
                        }
                        return av;
                    }
                }
            }
        }
        av.setViewName("page/404/404");
        return av;
    }

    /**
     * 更新密码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updatePwd/{sid}")
    public JsonResult updatePwd(UserDto userDto, @PathVariable("sid") String sid) {
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        try {
            UserDto dto = userService.findUserByUserName(userDto.getUserName());
            if (dto != null) {
                String secretKey = dto.getSecretKey();
                String digitalSignature = Md5Encrypt.md5(secretKey);// 数字签名
                if (!sid.equals(digitalSignature)) {
                    message = "签名错误，请不要修改邮件链接！";
                    type = MessageTipsCst.TYPE_ERROR;
                } else {
                    dto.setPassword(LoginHelper.md5PasswordTwo(userDto.getPassword()));
                    dto.setSecretKey(null);
                    userService.updateByPrimaryKey(dto);
                    message = MessageTipsCst.UPDATE_SUCCESS;
                    type = MessageTipsCst.TYPE_SUCCES;
                }
            } else {
                message = "该用户不存在，请查证！";
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
     * 进入注册页面
     *
     * @return
     */
    @RequestMapping("/register")
    public String register() {
        return "page/home/register";
    }

    /**
     * 保存用户注册信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveRegister")
    public JsonResult saveRegister(HttpServletRequest request, UserDto userDto) {
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        try {
            String secretKey = UUID.generate();
            userDto.setVaild("0");
            userDto.setSecretKey(secretKey);
            userDto.setPassword(LoginHelper.md5PasswordTwo(userDto.getPassword()));
            userService.saveRegister(userDto);

            String digitalSignature = Md5Encrypt.md5(secretKey);// 数字签名
            //发送邮件
            //http://localhost:8099/home/checkLink?sid=79d5d8e88c89db8214d5ef429af2a653&userName=admin
            StringBuffer emailContent = new StringBuffer();
            emailContent.append("请勿回复本邮件.点击下面的链接,验证注册用户<br>");
            emailContent.append("<a href=\"");
            emailContent.append(request.getScheme());
            emailContent.append("://");
            emailContent.append(request.getServerName());
            emailContent.append(":");
            emailContent.append(request.getServerPort());
            emailContent.append(request.getContextPath());
            emailContent.append("/home/");
            emailContent.append("checkLink?sid=");
            emailContent.append(digitalSignature);
            emailContent.append("&type=regist");
            emailContent.append("&userName=");
            emailContent.append(userDto.getUserName());
            emailContent.append("\" target='_blank'>点击我验证注册用户</a>");
            emailContent.append("<br><br>");
            logger.info(emailContent.toString());
            emailServer.singleMailSend(userDto.getMail(), "验证注册用户", emailContent.toString());

            message = MessageTipsCst.REGISTER_SUCCESS;
            type = MessageTipsCst.TYPE_SUCCES;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.REGISTER_FAILURE;
            type = MessageTipsCst.TYPE_ERROR;
        }
        json.setMessage(message);
        json.setType(type);
        json.setRoot("/home/login");
        return json;
    }

    /**
     * 校验用户名是否存在
     *
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkUserName/{userName}")
    public String checkUserName(@PathVariable("userName") String userName) {
        UserDto userDto = userService.findUserByUserName(userName);
        if (userDto == null) {
            return "0";
        }
        return "1";
    }
}
