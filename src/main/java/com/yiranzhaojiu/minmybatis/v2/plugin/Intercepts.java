package com.yiranzhaojiu.minmybatis.v2.plugin;

import java.lang.annotation.*;

/**
 * 插件拦截注解
 * */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Intercepts {
    /*执行器Executor的方法名*/
    String value();
}
