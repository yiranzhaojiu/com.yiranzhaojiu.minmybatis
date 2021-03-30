package com.yiranzhaojiu.minmybatis.v2.interceptor;

import com.yiranzhaojiu.minmybatis.v2.plugin.Interceptor;
import com.yiranzhaojiu.minmybatis.v2.plugin.Intercepts;
import com.yiranzhaojiu.minmybatis.v2.plugin.Invocation;
import com.yiranzhaojiu.minmybatis.v2.plugin.Plug;

import java.util.Arrays;

@Intercepts("query")
public class MyInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String statement = (String) invocation.getArgs()[0];
        Object[] parameter = (Object[]) invocation.getArgs()[1];

        System.out.println("进入自定义插件：MyPlugin");
        System.out.println("SQL：["+statement+"]");
        System.out.println("Parameters："+ Arrays.toString(parameter));

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plug.wrap(target,this);
    }
}
