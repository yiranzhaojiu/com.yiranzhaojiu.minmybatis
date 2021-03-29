package com.yiranzhaojiu.minmybatis.v1;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

public class YrzjConfiguration {

    //ResourceBundle 可以直接通过key获取值，键值对
    public static final ResourceBundle properties;
    static {
        properties = ResourceBundle.getBundle("v1/mybatis");
    }

    public <T> T getMapper(YrzjSqlSession sqlSession,Class<?> clazz){
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                new ProxyInvocationHandler(sqlSession));
    }
}
