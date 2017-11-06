package org.billow.controller.user;

import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.billow.api.user.UserService;
import org.billow.common.login.LoginHelper;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.RoleDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.PageHelper;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户信息管理
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 查询用户列表
     *
     * @param request
     * @param userDto 查询条件
     * @return
     */
    @RequestMapping("/queryUsers")
    public ModelAndView queryUsers(HttpServletRequest request, UserDto userDto) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        ModelAndView av = new ModelAndView();
        PageInfo<UserDto> page = userService.queryUsers(loginUser,userDto);
        av.addObject("page", page);
        av.setViewName(PagePathCst.BASEPATH_USER + "userList");
        return av;
    }

    /**
     * 添加/修改菜单信息，包含角色信息
     *
     * @param user
     * @return
     */
    @RequestMapping("/userEdit")
    public ModelAndView userEdit(HttpServletRequest request,UserDto user) {
        UserDto loginUser = LoginHelper.getLoginUser(request);
        UserDto userDto = userService.userEdit(loginUser,user);
        ModelAndView av = new ModelAndView();
        // 用于修改后保持停留在页面
        userDto.setPageNo(user.getPageNo());
        av.addObject("user", userDto);
        av.setViewName(PagePathCst.BASEPATH_USER + "userEdit");
        return av;
    }

    /**
     * 保存用户信息修改
     *
     * @param user    用户信息
     * @param roleIds 角色信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/userSave")
    public JsonResult userSave(@RequestParam(required = false, value = "roleId") String[] roleIds, UserDto user) {
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        try {
            userService.saveUserAndRole(user, roleIds);
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
        json.setRoot("/user/queryUsers?pageNo=" + user.getPageNo());
        return json;
    }

    @ResponseBody
    @RequestMapping("/userDel")
    public JsonResult userDel(UserDto user) {
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        try {
            userService.deleteDel(user);
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
}
