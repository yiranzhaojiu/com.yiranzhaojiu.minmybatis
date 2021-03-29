package com.yiranzhaojiu.minmybatis.v2.executor;

public class YrzjSimpleExecutor implements YrzjBaseExcutor {
    @Override
    public <T> T query(String sql,Object[] paramters,Class<?> clazz) {
        StatementHandler handler=new StatementHandler();
        return handler.query(sql,paramters,clazz);
    }
}
