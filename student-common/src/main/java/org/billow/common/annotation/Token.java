package org.billow.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 令牌
 *
 * @author liuyongtao
 * @create 2017-12-19 10:00
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {

    /**
     * 生成token，默认为save
     *
     * @return
     */
    String teyp() default "save";
}
