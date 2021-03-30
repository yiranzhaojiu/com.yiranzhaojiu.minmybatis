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
        return interceptor.intercept(new Invocation(target, method, args));
    }
}
