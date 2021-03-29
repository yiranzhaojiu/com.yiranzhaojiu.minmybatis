package com.yiranzhaojiu.minmybatis.v2.session;

import com.sun.javaws.security.AppContextUtil;
import com.yiranzhaojiu.minmybatis.v2.binding.MapperRegistry;
import com.yiranzhaojiu.minmybatis.v2.executor.CachingExecutor;
import com.yiranzhaojiu.minmybatis.v2.executor.Executor;
import com.yiranzhaojiu.minmybatis.v2.executor.SimpleExecutor;

import java.net.BindException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class Configuration {
    //ResourceBundle 可以直接通过key获取值，键值对
    public static final ResourceBundle properties;
    //Mapper文件对应的配置
    public static final ResourceBundle sqlMaping;
    //StatementId对应的SQL
    private static final Map<String,String> mappedStatements=new HashMap<>();
    //mapper接口文件对应的动态代理工厂类
    private static final MapperRegistry mapperRegistry=new MapperRegistry();

    static {
        properties = ResourceBundle.getBundle("v2/mybatis-application");
        sqlMaping= ResourceBundle.getBundle("v2/v2mybatis");
    }

    /**
     * 初始化
     * */
    public Configuration(){
        for (String  key:
            sqlMaping.keySet()) {
            String str = sqlMaping.getString(key);
            String[] split = str.split("--resultEntity:");
            String sql = split[0];
            String resultClass = split[1];
            String mapperClassName = key.substring(0, key.lastIndexOf("."));
            try {
                //记录StatementId对应的SQL
                mappedStatements.put(key,sql);
                //记录mapper对应的工厂类
                mapperRegistry.addMapper(Class.forName(mapperClassName),Class.forName(resultClass));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T getMapper(Class<?> clazz,SqlSession sqlSession) throws BindException {
        return mapperRegistry.getMapper(clazz, sqlSession);
    }

    public String getMappedStatement(String statementId){
        return mappedStatements.get(statementId);
    }

    /**
     * 创建执行器
     * */
    public Executor newExecutor() {
        Executor executor = new SimpleExecutor();
        //判断是否开启了缓存配置，装饰器模式
        if (properties.containsKey("mybatis.cached.enable") &&
                properties.getString("mybatis.cached.enable").toUpperCase().equals("TRUE")) {
            executor = new CachingExecutor(executor);
        }
        return executor;
    }
}
