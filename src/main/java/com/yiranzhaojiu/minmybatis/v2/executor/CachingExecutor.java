package com.yiranzhaojiu.minmybatis.v2.executor;

import com.yiranzhaojiu.minmybatis.v2.cache.CacheKey;

import java.util.HashMap;
import java.util.Map;

/**
 * 装饰器模式：装饰Executor对象
 * */
public class CachingExecutor implements Executor {

    //被装饰的原对象
    Executor delegate;
    //缓存执行的结果
    private final Map<Integer, Object> cache = new HashMap<>();

    public CachingExecutor(Executor executor) {
        this.delegate = executor;
    }

    @Override
    public <T> T query(String sql, Object[] paramters, Class<?> clazz) {
        CacheKey cacheKey = new CacheKey();
        cacheKey.update(sql);
        cacheKey.update(paramters);
        int hashCodeKey = cacheKey.hashCode();
        if (cache.containsKey(hashCodeKey)) {
            return (T)cache.get(hashCodeKey);
        }
        Object result = delegate.query(sql, paramters, clazz);
        cache.put(hashCodeKey,result);
        return (T) result;
    }
}


