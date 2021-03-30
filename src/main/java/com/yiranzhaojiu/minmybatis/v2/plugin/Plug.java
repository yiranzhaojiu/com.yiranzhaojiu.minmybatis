package com.yiranzhaojiu.minmybatis.v2.plugin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Plug implements InvocationHandler {
    private Object target;
    private Interceptor interceptor;

    public Plug(Object target,Interceptor interceptor){
        this.target=target;
        this.interceptor=interceptor;
    }
    public static Object wrap(Object target,Interceptor interceptor){
        Class targetClass=target.getClass();
         return Proxy.newProxyInstance(targetClass.getClassLoader(),
                targetClass.getInterfaces(),
                new Plug(target,interceptor));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(interceptor.getClass().isAnnotationPresent(Intercepts.class)&&
            method.getName().equals(interceptor.getClass().getAnnotation(Intercepts.class).value())){
            return interceptor.intercept(new Invocation(target, method, args));
        }
        // 非被拦截方法，执行原逻辑
        return method.invoke(target,args);
    }
}
