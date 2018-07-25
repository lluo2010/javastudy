package cloneTest;

import com.alibaba.fastjson.TypeReference;
import com.google.common.cache.Cache;
import com.google.errorprone.annotations.CompatibleWith;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by louis on 2018/5/21.
 */
public interface LocalCache{
    int LOCAL_EXPIRE_SECOND = 5;   //本地缓存过期时间

    /**
     * 缓存中获取，没找到返回null
     * @param strKey
     * @return
     */
    <T> T getIfPresent(String strKey, Class<T> clazz);
    <T> T getIfPresent(String strKey, TypeReference<T> type);

    /**
     * 获取缓存，为空则执行callable获取，返回值还是有可能为空
     * @param strKey
     * @param callable
     * @return
     * @throws ExecutionException
     */
    <T> T getIfPresent(String strKey, Callable<? extends T> callable, Class<T> clazz);
    <T> T getIfPresent(String strKey, Callable<? extends T> callable, TypeReference<? extends T> type);


    /**
     * 保存到缓存
     * @param strKey
     * @param value
     */
    <T> void put(String strKey, T value);
    <T> void put(String strKey, T value, Class<T> clazz);
    <T> void put(String strKey, T value, TypeReference<? extends T> type);
}
