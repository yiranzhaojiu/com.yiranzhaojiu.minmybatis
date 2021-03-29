package com.yiranzhaojiu.minmybatis.v2.binding;

import com.yiranzhaojiu.minmybatis.v2.session.SqlSession;

import java.net.BindException;
import java.util.HashMap;
import java.util.Map;

/**
 * Mapper注册类
 * */
public class MapperRegistry {

    //用于存储Mapper类文件对应的动态代理类
    private final Map<Class,MapperProxyFactory> knownMappers=new HashMap<>();

    public void addMapper(Class<?> clazz,Class<?> resultClazzName) {
        if(!knownMappers.containsKey(clazz)){
            knownMappers.put(clazz,new MapperProxyFactory(clazz,resultClazzName));
        }
    }

    //创建代理对象类
    public <T> T getMapper(Class<?> clazz, SqlSession sqlSession) throws BindException {
        if(!knownMappers.containsKey(clazz)){
            throw new BindException("Type:"+clazz+",is not known to Dynamic relation");
        }
        MapperProxyFactory mapperProxyFactory = knownMappers.get(clazz);
        return mapperProxyFactory.newInstance(sqlSession);
    }
}
