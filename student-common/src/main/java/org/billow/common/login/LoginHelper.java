package org.billow.common.login;

import org.apache.log4j.Logger;
import org.billow.model.expand.UserDto;
import org.billow.utils.RequestUtils;
import org.billow.utils.constant.CommonCst;
import org.billow.utils.generator.Md5Encrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class LoginHelper {

    private static Logger logger = Logger.getLogger(LoginHelper.class);

    public static UserDto getLoginUser() {
        HttpSession session = RequestUtils.getRequest().getSession();
        UserDto userDto = (UserDto) session.getAttribute(CommonCst.CURRENT_USER);
        return userDto;
    }

    /**
     * 获取用户登陆信息
     *
     * @param session
     * @return
     */
    public static UserDto getLoginUser(HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute(CommonCst.CURRENT_USER);
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
        UserDto userDto = (UserDto) session.getAttribute(CommonCst.CURRENT_USER);
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
        session.setAttribute(CommonCst.CURRENT_USER, userDto);
    }

    /**
     * 获取购物车数量
     *
     * @param request
     * @return
     */
    public static int getShoppingCount(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int shoppingCount = (int) session.getAttribute(CommonCst.SHOPPING_COUNT);
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
        session.setAttribute(CommonCst.SHOPPING_COUNT, shoppingCount);
    }

    /**
     * md5两次加密
     *
     * @param password
     * @return
     */
    public static String md5PasswordTwo(String password) {
        return Md5Encrypt.md5(Md5Encrypt.md5(password));
    }

    /**
     * 比较两次密码是否一致
     *
     * @param sourcePassword 源密码（未加密）
     * @param targetPassword 目标密码（加密后）
     * @return
     */
    public static boolean equalstPassword(String sourcePassword, String targetPassword) {
        boolean flag;
        try {
            flag = targetPassword.equals(md5PasswordTwo(sourcePassword));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            flag = false;
        }
        return flag;
    }

    /**
     * 通过userId移除用户session
     *
     * @param userId
     */
    public static void removeUserSession(Integer userId) {
        HttpServletRequest request = RequestUtils.getRequest();
        Map<Integer, HttpSession> sessionMap = (Map<Integer, HttpSession>) request.getServletContext()
                .getAttribute(CommonCst.SESSION_MAP);
        //获取用户session集合
        HttpSession userSession = sessionMap.get(userId);
        if (userSession != null) {
            //销毁session
            userSession.invalidate();
        }
    }

    public static void main(String[] args) {
        //9db06bcff9248837f86d1a6bcf41c9e7
        logger.info(LoginHelper.equalstPassword("111111", "9db06bcff9248837f86d1a6bcf41c9e7"));
    }
}
