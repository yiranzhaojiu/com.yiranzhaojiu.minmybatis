package com.yiranzhaojiu.minmybatis.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvocationHandler implements InvocationHandler {

    private YrzjSqlSession sqlSession;
    public ProxyInvocationHandler(YrzjSqlSession sqlSession){
        this.sqlSession=sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String statementId = className + "." + methodName;
        return sqlSession.selectOne(statementId,args[0]);
    }
}
