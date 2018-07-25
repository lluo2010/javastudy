package cloneTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * Created by louis on 2018/7/24.
 */
public class JsonDeepClone {

    public static <T> T clone(T t, Class<T> clazz){
        if (!needCopy(t)){
            return t;
        }

        return JSON.parseObject(JSON.toJSONString(t), clazz);
    }

    public static <T> T clone(T t, TypeReference<? extends T> type){
        if (!needCopy(t)){
            return t;
        }

        return JSON.parseObject(JSON.toJSONString(t), type);
    }


    /**
     * 是否需要进行对象copy
     * @param
     * @return
     */
    private static <T> boolean needCopy(final T t) {
        if (t == null) {
            return false;
        }

        Class cls = t.getClass();
        return !(cls.isPrimitive()
                || cls.equals(String.class)
                || cls.getSuperclass().equals(Number.class)
                || cls.equals(Boolean.class));
    }
}
