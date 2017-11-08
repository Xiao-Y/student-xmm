package org.billow.common.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.billow.model.expand.UserDto;
import org.billow.utils.RequestUtils;
import org.billow.utils.ToolsUtils;

public class LoginHelper {

    public static UserDto getLoginUser(HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("currentUser");
        return userDto;
    }

    public static UserDto getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("currentUser");
        return userDto;
    }

    @Deprecated
    public static Integer getLoginUserId() {
        HttpSession session = RequestUtils.getRequest().getSession();
        UserDto user = getLoginUser(session);
        Integer userId = null;
        if (user != null) {
            if (user.getUserName().equals("employee")) {
                userId = 1;
            } else if (user.getUserName().equals("admin")) {
                userId = 2;
            } else if (user.getUserName().equals("sa")) {
                userId = 3;
            } else if (user.getUserName().equals("Manager")) {
                userId = 4;
            } else if (user.getUserName().equals("General manager")) {
                userId = 5;
            } else if (user.getUserName().equals("Board Chairman")) {
                userId = 6;
            }
            // userId = user.getUserId();
        }
        return userId;
    }
}
