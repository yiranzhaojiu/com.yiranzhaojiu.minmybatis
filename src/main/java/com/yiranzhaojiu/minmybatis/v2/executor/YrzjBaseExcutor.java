package com.yiranzhaojiu.minmybatis.v2.executor;

public interface YrzjBaseExcutor {
    <T> T query(String sql,Object[] paramters,Class<?> clazz);
}
