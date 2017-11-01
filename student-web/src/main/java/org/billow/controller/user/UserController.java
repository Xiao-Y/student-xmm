package org.billow.controller.user;

import org.billow.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户信息管理
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
}
