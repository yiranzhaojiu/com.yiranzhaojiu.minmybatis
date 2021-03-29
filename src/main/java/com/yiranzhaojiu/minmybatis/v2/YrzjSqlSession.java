package com.yiranzhaojiu.minmybatis.v2;


import com.yiranzhaojiu.minmybatis.v2.executor.YrzjBaseExcutor;

public class YrzjSqlSession {
    YrzjBaseExcutor executor;
    YrzjConfiguration configuration;
    public YrzjSqlSession(YrzjBaseExcutor executor, YrzjConfiguration configuration){
        this.executor=executor;
        this.configuration=configuration;
    }

    //调用Configuration的getMapper方法
    public <T> T getMapper(Class<?> clazz){
        return (T)configuration.getMapper(this,clazz);
    }
    //调用Configuration的getMapper方法
    public <T> T getMapper(Class<?> clazz,Class<?> resultClazz){
        return (T)configuration.getMapper(this,clazz,resultClazz);
    }

    //操作数据库对外提供统一的方法，实则调用是执行器Executor的方法
    public <T> T selectOne(String statementId,Object[] paramter,Class<?> clazz) {
        String sql = YrzjConfiguration.sqlMaping.getString(statementId);
        return (T) executor.query(sql,paramter,clazz);
    }
}
