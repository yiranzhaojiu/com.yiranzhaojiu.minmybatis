package com.yiranzhaojiu.minmybatis.v2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvocationHandler implements InvocationHandler {

    private YrzjSqlSession sqlSession;
    private Class resultMap;
    public ProxyInvocationHandler(YrzjSqlSession sqlSession,Class resultMap){
        this.sqlSession=sqlSession;
        this.resultMap=resultMap;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String statementId = className + "." + methodName;
        return sqlSession.selectOne(statementId,args,resultMap);
    }
}
