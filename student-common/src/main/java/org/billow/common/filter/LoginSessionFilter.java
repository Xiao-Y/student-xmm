package org.billow.common.filter;

import org.billow.common.login.LoginHelper;
import org.billow.model.expand.UserDto;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 登陆验证过滤器
 *
 * @author liuyongtao
 * @create 2017-11-06 10:42
 */
@WebFilter(filterName = "sessionFilter", urlPatterns = "/*")
public class LoginSessionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 不过滤的uri
        List<String> notFilter = new ArrayList<>();
        notFilter.add("/home/login");
        notFilter.add("/home/homeIndex");
        notFilter.add("/home/forgetPwd");//进入忘记密码页面
        notFilter.add("/home/sendMailLink");//发送忘记密码链接到指定邮箱
        notFilter.add("/home/checkLink");//检查邮件链接的正确性
        notFilter.add("/home/register");//进入注册页面
        notFilter.add("/home/saveRegister");//保存用户注册信息

        //uri关键字过渡
        List<String> notUri = new ArrayList<>();
        notUri.add("druid");
        notUri.add("static");
        notUri.add("updatePwd");
        notUri.add("checkUserName");

        // 请求的uri
        String uri = request.getRequestURI();

        //是否要过滤
        boolean filterFlag = true;
        for (String u : notUri) {
            if (uri.indexOf(u) > -1) {
                filterFlag = false;
                break;
            }
        }
        if (filterFlag) {// 是否过滤
            if (!notFilter.contains(uri)) {// 执行过滤
                // 从session中获取登录者实体
                UserDto loginUser = LoginHelper.getLoginUser(request);
                if (null == loginUser || "0".equals(loginUser.getVaild())) {
                    response.sendRedirect("/home/login");
                } else {
                    // 如果session中存在登录者实体，则继续
                    filterChain.doFilter(request, response);
                }
            } else {// 如果不执行过滤，则继续
                filterChain.doFilter(request, response);
            }
        } else {// 如果uri中包含static，则继续
            filterChain.doFilter(request, response);
        }
    }
}
