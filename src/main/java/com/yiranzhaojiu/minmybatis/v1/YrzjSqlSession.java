package com.yiranzhaojiu.minmybatis.v1;

public class YrzjSqlSession {
    YrzjExecutor executor;
    YrzjConfiguration configuration;
    public YrzjSqlSession(YrzjExecutor executor,YrzjConfiguration configuration){
        this.executor=executor;
        this.configuration=configuration;
    }

    //调用Configuration的getMapper方法
    public <T> T getMapper(Class<?> clazz){
        return (T)configuration.getMapper(this,clazz);
    }

    //操作数据库对外提供统一的方法，实则调用是执行器Executor的方法
    public <T> T selectOne(String statementId,Object paramter) {
        String sql = YrzjConfiguration.properties.getString(statementId);
        sql = String.format(sql, paramter);
        return (T) executor.query(sql);
    }
}
