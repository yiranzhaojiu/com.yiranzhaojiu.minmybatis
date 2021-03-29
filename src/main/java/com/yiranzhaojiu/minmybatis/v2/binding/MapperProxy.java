package com.yiranzhaojiu.minmybatis.v2.binding;

import com.yiranzhaojiu.minmybatis.v2.session.SqlSession;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy  implements InvocationHandler {

    private SqlSession sqlSession;
    Class resultClazz;
    public MapperProxy(SqlSession sqlSession,Class resultClazz) {
        this.sqlSession = sqlSession;
        this.resultClazz = resultClazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String statementId = className + "." + methodName;
        return sqlSession.selectOne(statementId,args,resultClazz);
    }
}
