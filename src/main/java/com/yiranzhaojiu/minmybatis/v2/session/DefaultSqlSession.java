package com.yiranzhaojiu.minmybatis.v2.session;

import com.yiranzhaojiu.minmybatis.v2.executor.Executor;
import com.yiranzhaojiu.minmybatis.v2.executor.SimpleExecutor;
import java.net.BindException;

public class DefaultSqlSession implements SqlSession {

    Configuration configuration;
    Executor executor;

    public DefaultSqlSession(Configuration configuration){
        this.configuration=configuration;
        this.executor=new SimpleExecutor();
    }

    @Override
    public <T> T getMapper(Class<?> clazz) throws BindException {
        return configuration.getMapper(clazz,this);
    }

    @Override
    public <T> T selectOne(String statementId,Object[] paramter,Class<?> clazz) {
        String sql = configuration.getMappedStatement(statementId);
        return (T) executor.query(sql,paramter,clazz);
    }
}
