package com.yiranzhaojiu.minmybatis.v2.plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链模式存储
 * */
public class InterceptorChain {
    //保存插件对象
    private final List<Interceptor> interceptors=new ArrayList<>();
    //插件责任链遍历
    public Object pluginAll(Object target) {
        for (Interceptor interceptor:
                interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }
    //添加插件对象
    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }
}
