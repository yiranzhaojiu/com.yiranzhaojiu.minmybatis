package com.yiranzhaojiu.minmybatis.v2;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

public class YrzjConfiguration {

    //ResourceBundle 可以直接通过key获取值，键值对
    public static final ResourceBundle properties;
    public static final ResourceBundle sqlMaping;
    static {
        properties = ResourceBundle.getBundle("v2/mybatis-application");
        sqlMaping= ResourceBundle.getBundle("v2/v2mybatis");
    }

    public <T> T getMapper(YrzjSqlSession sqlSession, Class<?> clazz){
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                new ProxyInvocationHandler(sqlSession,null));
    }
    public <T> T getMapper(YrzjSqlSession sqlSession, Class<?> clazz,Class<?> resultClazz){
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                new ProxyInvocationHandler(sqlSession,resultClazz));
    }
}
