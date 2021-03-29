package com.yiranzhaojiu.minmybatis.v2.session;

public class SqlSessiionFactory {

    private Configuration configuration;
    public SqlSessiionFactory build(){
        this.configuration=new Configuration();
        return this;
    }
    public SqlSession openSession() {
        return new DefaultSqlSession(this.configuration);
    }
}
