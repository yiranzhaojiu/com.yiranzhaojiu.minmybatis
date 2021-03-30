package com.yiranzhaojiu.minmybatis.v2.plugin;

/**
 * 对外提供插件统一标准
 * */
public interface Interceptor {
    /**
     * 插件核心逻辑的实现
     * */
    Object intercept(Invocation invocation) throws Throwable;

    /**
     * 对被拦截对象进行代理
     * */
    Object plugin(Object target);
}
