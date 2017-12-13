package org.billow.common.filter;

import org.billow.common.login.LoginHelper;
import org.billow.model.expand.UserDto;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
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
        ServletContext servletContext = request.getSession().getServletContext();
        String contextPath = servletContext.getContextPath();
        // 不过滤的uri
        List<String> notFilter = new ArrayList<>();
        notFilter.add(contextPath + "/home/login");
        notFilter.add(contextPath + "/home/homeIndex");
        notFilter.add(contextPath + "/home/forgetPwd");//进入忘记密码页面
        notFilter.add(contextPath + "/home/sendMailLink");//发送忘记密码链接到指定邮箱
        notFilter.add(contextPath + "/home/checkLink");//检查邮件链接的正确性
        notFilter.add(contextPath + "/home/register");//进入注册页面
        notFilter.add(contextPath + "/home/saveRegister");//保存用户注册信息
        notFilter.add(contextPath + "/aliPay/notifyResult");//支付宝支付通知异步回调
        notFilter.add(contextPath + "/aliPay/returnResult");//支付宝支付通知同步回调

        //uri关键字过渡
        List<String> notUri = new ArrayList<>();
        notUri.add("druid");
        notUri.add("static");
        notUri.add("updatePwd");
        notUri.add("checkUserName");
        notUri.add("mb");
        notUri.add("upload");

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
                    response.sendRedirect(contextPath + "/home/login");
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
