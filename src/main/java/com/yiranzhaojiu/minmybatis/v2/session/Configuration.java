package com.yiranzhaojiu.minmybatis.v2.session;

import com.yiranzhaojiu.minmybatis.v2.V2MinMybatisTest;
import com.yiranzhaojiu.minmybatis.v2.annotations.ResultEntity;
import com.yiranzhaojiu.minmybatis.v2.annotations.Select;
import com.yiranzhaojiu.minmybatis.v2.binding.MapperRegistry;
import com.yiranzhaojiu.minmybatis.v2.executor.CachingExecutor;
import com.yiranzhaojiu.minmybatis.v2.executor.Executor;
import com.yiranzhaojiu.minmybatis.v2.executor.SimpleExecutor;
import com.yiranzhaojiu.minmybatis.v2.plugin.Interceptor;
import com.yiranzhaojiu.minmybatis.v2.plugin.InterceptorChain;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.BindException;
import java.util.*;

public class Configuration {
    //ResourceBundle 可以直接通过key获取值，键值对
    public static final ResourceBundle properties;
    //Mapper文件对应的配置
    public static final ResourceBundle sqlMaping;
    //StatementId对应的SQL
    private static final Map<String,String> mappedStatements=new HashMap<>();
    //mapper接口文件对应的动态代理工厂类
    private static final MapperRegistry mapperRegistry=new MapperRegistry();
    //插件链存储对象
    private final InterceptorChain interceptorChain=new InterceptorChain();
    //所有Mapper接口
    private List<Class<?>> mapperList=new ArrayList<>();
    //所有Mapper接口类路径
    private List<String> classMapperPaths = new ArrayList<>();

    static {
        properties = ResourceBundle.getBundle("v2/mybatis-application");
        sqlMaping= ResourceBundle.getBundle("v2/v2mybatis");
    }

    /**
     * 初始化
     * */
    public Configuration() throws Exception{
        pluginElement();
        //配置解析
        mapperElement();
        //注解解析
        mapperAnnotation();
    }
    /**
     * 插件处理
     * */
    private void pluginElement() throws Exception {
        if(!properties.containsKey("mybatis.plugins")) return;
        String[] plugins = Configuration.properties.getString("mybatis.plugins").split(",");
        for (String plugin:
             plugins) {
            Interceptor interceptorInstance=(Interceptor)Class.forName(plugin).newInstance();
            //责任链模式，保存插件
            interceptorChain.addInterceptor(interceptorInstance);
        }
    }
    /**
     * SQL文件处理
     * */
    private void mapperElement(){
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
    /**
     * 注解Mapper实现
     * */
    private void mapperAnnotation(){
        if(!properties.containsKey("mapper.pack")) return;
        String mapperPack = properties.getString("mapper.pack");
        String mapperPath=mapperPack.replace(".","/");
        String classPath=this.getClass().getResource("/").getPath();

        doPath(new File(classPath+mapperPath));
        for (String className : classMapperPaths) {
            className = className.replace(
                    classPath
                            .replace("/","\\")
                                .replaceFirst("\\\\",""),"")
                            .replace("\\",".").replace(".class","");
            Class<?> clazz = null;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(clazz.isInterface()){
                mapperList.add(clazz);
            }

        }

        for (Class<?> mapper : mapperList) {
            parsingClass(mapper);
        }


    }
    /**
     * 解析Mapper接口上配置的注解（SQL语句）
     */
    private void parsingClass(Class<?> mapper) {

        // 1.解析方法上的注解
        // 2.解析类上的注解,如果有Entity注解，说明是查询数据库的接口
        Method[] methods = mapper.getMethods();
        for (Method method : methods) {
            //TODO 其他操作
            // 解析@Select注解的SQL语句
            if (method.isAnnotationPresent(Select.class)) {
                for (Annotation annotation : method.getDeclaredAnnotations()) {
                    if (annotation.annotationType().equals(Select.class)) {
                        // 注册接口类型+方法名和SQL语句的映射关系
                        String statement = method.getDeclaringClass().getName() + "." +method.getName();
                        mappedStatements.put(statement, ((Select) annotation).value());
                    }
                    else if(annotation.annotationType().equals(ResultEntity.class)) {
                        //记录mapper对应的工厂类
                        mapperRegistry.addMapper(mapper, ((ResultEntity) annotation).value());
                    }
                }
            }
        }
    }

    private void doPath(File file) {
        // 文件夹，遍历
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f1 : files) {
                doPath(f1);
            }
        } else {
            // 文件，直接添加
            if (file.getName().endsWith(".class")) {
                classMapperPaths.add(file.getPath());
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
        executor=(Executor) interceptorChain.pluginAll(executor);
        return executor;
    }
}
