package org.billow.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一个用户 相同url 同时提交 相同数据 验证
 *
 * @author liuyongtao
 * @create 2017-12-19 10:39
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SameUrlData {
    /**
     * 请求间隔时间,毫秒
     */
    long intervalTime() default 0;
}
