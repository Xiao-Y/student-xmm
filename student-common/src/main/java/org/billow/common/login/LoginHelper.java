package org.billow.common.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.billow.model.expand.UserDto;
import org.billow.utils.RequestUtils;
import org.billow.utils.ToolsUtils;

public class LoginHelper {

    public static UserDto getLoginUser() {
        HttpSession session = RequestUtils.getRequest().getSession();
        UserDto userDto = (UserDto) session.getAttribute("currentUser");
        return userDto;
    }

    /**
     * 获取用户登陆信息
     *
     * @param session
     * @return
     */
    public static UserDto getLoginUser(HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("currentUser");
        return userDto;
    }

    /**
     * 获取用户登陆信息
     *
     * @param request
     * @return
     */
    public static UserDto getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("currentUser");
        return userDto;
    }

    /**
     * 设置用户登陆信息
     *
     * @param request
     * @param userDto
     */
    public static void setLoginUser(HttpServletRequest request, UserDto userDto) {
        HttpSession session = request.getSession();
        session.setAttribute("currentUser", userDto);
    }

    /**
     * 获取购物车数量
     *
     * @param request
     * @return
     */
    public static int getShoppingCount(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int shoppingCount = (int) session.getAttribute("shoppingCount");
        return shoppingCount;
    }

    /**
     * 设置购物车数量
     *
     * @param request
     * @param shoppingCount
     */
    public static void setShoppingCount(HttpServletRequest request, int shoppingCount) {
        HttpSession session = request.getSession();
        session.setAttribute("shoppingCount", shoppingCount);
    }
}
