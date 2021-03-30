package com.yiranzhaojiu.minmybatis.v2.plugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 插件代理执行的包装类
 * */
public class Invocation {
    //目标对象
    private Object target;
    //目标执行方法
    private Method method;
    //目标方法参数
    private Object[] args;
    public Invocation(Object target,Method method,Object[] args){
        this.target=target;
        this.method=method;
        this.args=args;
    }

    public Object getTarget() {
        return target;
    }
    public Method getMethod() {
        return method;
    }
    public Object[] getArgs() {
        return args;
    }


    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, args);
    }
}
