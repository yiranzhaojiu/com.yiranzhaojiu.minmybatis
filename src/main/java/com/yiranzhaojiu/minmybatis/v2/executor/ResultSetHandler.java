package com.yiranzhaojiu.minmybatis.v2.executor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;

public class ResultSetHandler {

    /**
     * 结果集处理
     * */
    public <T> T handler(ResultSet resultSet,Class<?> clazz) throws Exception {
        Object result = clazz.newInstance();
        while (resultSet.next()){
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field:declaredFields){
                setValue(result,resultSet,field);
            }
        }
        return (T)result;
    }
    /**
     * 通过setter方法属性赋值
     * */
    private void  setValue(Object instance,ResultSet resultSet,Field field) throws Exception {
        Method method = instance.getClass().getMethod("set" + firstWordCapital(field.getName()),field.getType());
        method.invoke(instance,getResult(resultSet,field));
    }
    /**
     * 从结果集中获取数据
     * */
    private Object getResult(ResultSet resultSet,Field field) throws Exception {
        Class<?> type = field.getType();
        String fieldName = field.getName();
        if(type==Integer.class){
            return resultSet.getInt(fieldName);
        }
        else if(type==Long.class){
            return resultSet.getLong(fieldName);
        }
        else if(type==Boolean.class){
            return resultSet.getBoolean(fieldName);
        }
        else{
            return resultSet.getString(fieldName);
        }
    }

    /**
     * 首字母大写
     * */
    private String firstWordCapital(String str){
        String fisrt=str.substring(0,1);
        String other=str.substring(1);
        return fisrt.toUpperCase()+other;
    }
}
