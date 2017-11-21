package org.billow.common.listener;

import org.billow.model.expand.UserDto;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登陆session监听
 *
 * @author liuyongtao
 * @create 2017-11-21 12:35
 */
@WebListener
public class LoginSessionListener implements ServletContextListener, HttpSessionAttributeListener {

    private static final String SESSION_MAP = "sessionMap";

    private ServletContext application = null;

    /**
     * 容器初始化
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //容器初始化时，向application中存放一个空的容器
        Map<Integer, HttpSession> sessionMap = new HashMap<>();
        this.application = sce.getServletContext();
        this.application.setAttribute(SESSION_MAP, sessionMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        this.application = sce.getServletContext();
        this.application.setAttribute(SESSION_MAP, null);
    }

    /**
     * 在session中添加对象时触发此操作 笼统的说就是调用setAttribute这个方法时候会触发的
     *
     * @param event
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        // 如果登陆成功，则将用户保存在列表之中
        Map<Integer, HttpSession> sessionMap = (Map<Integer, HttpSession>) this.application.getAttribute(SESSION_MAP);
        if (event.getValue() instanceof UserDto) {
            UserDto loginUser = (UserDto) event.getValue();
            sessionMap.put(loginUser.getUserId(), event.getSession());
        }
        this.application.setAttribute(SESSION_MAP, sessionMap);
    }

    /**
     * 修改、删除,invalidate,session中添加对象时触发此操作  笼统的说就是调用 removeAttribute这个方法时候会触发的
     *
     * @param event
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        Map<Integer, HttpSession> sessionMap = (Map<Integer, HttpSession>) this.application.getAttribute(SESSION_MAP);
        if (event.getValue() instanceof UserDto) {
            UserDto loginUser = (UserDto) event.getValue();
            sessionMap.remove(loginUser.getUserId());
        }
        this.application.setAttribute(SESSION_MAP, sessionMap);
    }

    /**
     * 在Session属性被重新设置时
     *
     * @param event
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        this.attributeAdded(event);
    }
}
