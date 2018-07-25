package cloneTest;

import com.alibaba.fastjson.TypeReference;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2018/5/21.
 */


public class LocalCacheImpl implements LocalCache {
    private static Cache<String, Object> mLocalCache;

    /*@PostConstruct
    protected void initCache() {
        if (mLocalCache!=null){
            return;
        }
        mLocalCache = CacheBuilder.newBuilder()
                .expireAfterWrite(LOCAL_EXPIRE_SECOND, TimeUnit.SECONDS)
                .maximumSize(1000 * 100)  //最大缓存记录数目，不是内存容量！
                .build();
    }*/


    public LocalCacheImpl() {
        mLocalCache = CacheBuilder.newBuilder()
                .expireAfterWrite(LOCAL_EXPIRE_SECOND, TimeUnit.SECONDS)
                .maximumSize(1000 * 100)  //最大缓存记录数目，不是内存容量！
                .build();
    }


    @Override
    public <T> T getIfPresent(String strKey, Class<T> clazz) {
        T result = getIfPresent(strKey);
        return JsonDeepClone.clone(result, clazz);
    }

    @Override
    public <T> T getIfPresent(String strKey, TypeReference<T> type) {
        T result = getIfPresent(strKey);
        return JsonDeepClone.clone(result, type);
    }

    @Override
    public <T> T getIfPresent(String strKey, Callable<? extends T> callable, Class<T> clazz) {
        T result = getIfPresent(strKey, callable);
        return JsonDeepClone.clone(result, clazz);
    }

    @Override
    public <T> T getIfPresent(String strKey, Callable<? extends T> callable, TypeReference<? extends T> type) {
        T result = getIfPresent(strKey, callable);
        return JsonDeepClone.clone(result, type);
    }



    private <T> T getIfPresent(String strKey) {
        T result = null;
        if (mLocalCache!=null){
            result = (T)mLocalCache.getIfPresent(strKey);
        }

        return result;
    }

    private <T> T getIfPresent(String strKey, Callable<? extends T> callable) {
        if (mLocalCache == null) {
            return null;
        }

        T ret;
        try {
            ret = (T)mLocalCache.get(strKey, callable);
        } catch (CacheLoader.InvalidCacheLoadException e) {
            ret = null;
        }catch (ExecutionException e){
            throw new RuntimeException(e);
        }

        return ret;
    }


    @Override
    public <T> void put(String strKey, T value) {
        Class clz = value.getClass();
        put(strKey, value, clz);
    }

    @Override
    public <T> void put(String strKey, T value, Class<T> clazz) {
        T clone = JsonDeepClone.clone(value, clazz);
        putValue(strKey, clone);
    }

    @Override
    public <T> void put(String strKey, T value, TypeReference<? extends T> type) {
        T clone = JsonDeepClone.clone(value, type);
        putValue(strKey, clone);
    }


    private <T> void putValue(String strKey, T value) {
        if (mLocalCache!=null){
            mLocalCache.put(strKey, value);
        }
    }


    public static void main(String[] args) {
        //objectTest();
        //listTest();
        mapTest();
    }


    private static void objectTest(){
        System.out.println("----objectTest");
        LocalCacheImpl localCache = new LocalCacheImpl();
        String strKey = "key1";
        MyObject obj1 = new MyObject(1,"1");
        localCache.putValue(strKey, obj1);
        Object ifPresent = localCache.getIfPresent(strKey);
        System.out.println(ifPresent);
        silentSleep(7000);
        System.out.println("after timeout");
        MyObject obj2 = localCache.getIfPresent(strKey, ()->{
            return new MyObject(2,"2");
        });
        System.out.println(obj2);
    }


    private static void listTest(){
        System.out.println("----listTest");
        LocalCacheImpl localCache = new LocalCacheImpl();
        String strKey = "key2";
        MyObject obj1 = new MyObject(1,"1");
        MyObject obj2 = new MyObject(2,"2");
        List<MyObject> list1 = new ArrayList<>();
        list1.add(obj1);
        list1.add(obj2);
        TypeReference<ArrayList<MyObject>> typeReference = new TypeReference<ArrayList<MyObject>>() {
        };
        localCache.put(strKey, list1, typeReference);
        List<MyObject> list2  = localCache.getIfPresent(strKey);
        list2.forEach(obj->{
            System.out.println(obj);
        });

        silentSleep(7000);
        System.out.println("after timeout");
        List<MyObject> list3 = localCache.getIfPresent(strKey, ()->{

            MyObject obj3 = new MyObject(3,"1");
            MyObject obj4 = new MyObject(4,"2");
            List<MyObject> list5 = new ArrayList<>();
            list5.add(obj3);
            list5.add(obj4);
            return  list5;
        }, typeReference);

        list3.forEach(obj->{
            System.out.println(obj);
        });
    }


    private static void mapTest(){
        System.out.println("----mapTest");
        LocalCacheImpl localCache = new LocalCacheImpl();
        String strKey = "key2";
        Map<String, MyObject> map1 = new HashMap<>();
        map1.put("k1", new MyObject(1, "1"));
        map1.put("k2", new MyObject(2, "2"));
        TypeReference<HashMap<String, MyObject>> typeReference = new TypeReference<HashMap<String, MyObject>>() {
        };
        localCache.put(strKey, map1, typeReference);
        Map<String, MyObject> map2  = localCache.getIfPresent(strKey);
        for (Map.Entry<String, MyObject> stringMyObjectEntry : map2.entrySet()) {
            System.out.println(stringMyObjectEntry.getKey()+":"+stringMyObjectEntry.getValue());
        }

        silentSleep(7000);
        System.out.println("after timeout");
        Map<String, MyObject> map3  = localCache.getIfPresent(strKey, ()->{
            Map<String, MyObject> map5 = new HashMap<>();
            map5.put("k3", new MyObject(3, "3"));
            map5.put("k4", new MyObject(4, "4"));
            return map5;

        }, typeReference);

        for (Map.Entry<String, MyObject> stringMyObjectEntry : map3.entrySet()) {
            System.out.println(stringMyObjectEntry.getKey()+":"+stringMyObjectEntry.getValue());
        }
    }



    private static void silentSleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

