package com.yiranzhaojiu.minmybatis.v2.binding;

import com.yiranzhaojiu.minmybatis.v2.session.SqlSession;

import java.lang.reflect.Proxy;

public class MapperProxyFactory {

    //mapper接口
    private Class<?> mapperInterface;
    //结果接映射的实体
    private Class<?> resultClazzName;

    public MapperProxyFactory(Class<?> mapperInterface,Class<?> resultClazzName) {
        this.mapperInterface=mapperInterface;
        this.resultClazzName = resultClazzName;
    }

    public <T> T newInstance(SqlSession sqlSession) {
       return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader()
                ,new Class[]{mapperInterface},new MapperProxy(sqlSession,resultClazzName));
    }
}
