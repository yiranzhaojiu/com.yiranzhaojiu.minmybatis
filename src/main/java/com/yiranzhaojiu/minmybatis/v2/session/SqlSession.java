package com.yiranzhaojiu.minmybatis.v2.session;

import java.net.BindException;

public interface SqlSession {

     /**
      * 获取Mapper文件对应的MapperFactory
      * */
     <T> T getMapper(Class<?> clazz) throws BindException;

     <T> T selectOne(String statementId,Object[] paramter,Class<?> clazz);
}
