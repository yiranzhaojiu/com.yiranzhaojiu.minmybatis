package com.yiranzhaojiu.minmybatis.v2.executor;

public class SimpleExecutor implements Executor {
    @Override
    public <T> T query(String sql,Object[] paramters,Class<?> clazz) {
        StatementHandler handler=new StatementHandler();
        return handler.query(sql,paramters,clazz);
    }
}
