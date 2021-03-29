package com.yiranzhaojiu.minmybatis.v2.executor;

public interface Executor {
    <T> T query(String sql,Object[] paramters,Class<?> clazz);
}
