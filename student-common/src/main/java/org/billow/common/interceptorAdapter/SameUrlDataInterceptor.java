package org.billow.common.interceptorAdapter;

import com.alibaba.fastjson.JSON;
import org.billow.common.annotation.SameUrlData;
import org.billow.utils.ToolsUtils;
import org.billow.utils.date.DateTime;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 一个用户 相同url 同时提交 相同数据 验证,
 * 主要通过 session中保存到的url 和 请求参数。如果和上次相同，则是重复提交表单
 *
 * @author liuyongtao
 * @create 2017-12-19 10:45
 */
public class SameUrlDataInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            SameUrlData annotation = method.getAnnotation(SameUrlData.class);
            if (annotation != null) {
                Long intervalTime = annotation.intervalTime();
                if (repeatDataValidator(request, intervalTime)) {//如果重复相同数据
                    return false;
                } else {
                    return true;
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 验证同一个url数据是否相同提交  ,相同返回true
     *
     * @param httpServletRequest
     * @param intervalTime       间隔时间
     * @return
     */
    public boolean repeatDataValidator(HttpServletRequest httpServletRequest, Long intervalTime) {
        boolean intervalTimeFlag = true;
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        String params = JSON.toJSONString(parameterMap);
        String url = httpServletRequest.getRequestURI();
        Map<String, String> map = new HashMap<>();
        map.put(url, params);
        String nowUrlParams = map.toString();

        String[] intervalTimes = parameterMap.get("intervalTime");
        if (ToolsUtils.isNotEmpty(intervalTimes)) {
            //当前请求时间
            String nowIntervalTime = intervalTimes[0];
            //上次请求的时间
            String proIntervalTime = (String) httpServletRequest.getSession().getAttribute("proIntervalTime");
            String proUrl = (String) httpServletRequest.getSession().getAttribute("proUrl");
            if (url.equals(proUrl) && ToolsUtils.isNotEmpty(nowIntervalTime) && ToolsUtils.isNotEmpty(proIntervalTime)) {
                long min = (new DateTime(nowIntervalTime, DateTime.YEAR_TO_SECOND).getTime()) - (new DateTime(proIntervalTime, DateTime.YEAR_TO_SECOND).getTime());
                if (min < intervalTime) {
                    intervalTimeFlag = false;
                } else {
                    httpServletRequest.getSession().setAttribute("proIntervalTime", nowIntervalTime);
                }
            }
            if (ToolsUtils.isEmpty(proIntervalTime)) {
                httpServletRequest.getSession().setAttribute("proIntervalTime", nowIntervalTime);
            }
        }
        httpServletRequest.getSession().setAttribute("proUrl", url);
        Object preUrlParams = httpServletRequest.getSession().getAttribute("repeatData");
        if (preUrlParams == null) {//如果上一个数据为null,表示还没有访问页面
            httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);
            return false;
        } else {//否则，已经访问过页面
            if (!preUrlParams.toString().equals(nowUrlParams) && intervalTimeFlag) {//如果上次 url+数据 和本次url加数据不同，则不是重复提交
                httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);
                return false;
            }
        }
        return true;
    }
}