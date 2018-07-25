package cloneTest;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by louis on 2018/7/24.
 */
public class DeepCopy {

    /**
     * 深层拷贝
     *
     * @param <T>
     * @param obj
     * @return
     * @throws Exception
     */
    public static <T> T copyByStream(T obj){
        //是否实现了序列化接口，即使该类实现了，他拥有的对象未必也有...
        if (Serializable.class.isAssignableFrom(obj.getClass())) {
            //如果子类没有继承该接口，这一步会报错
            try {
                return copyImplSerializable(obj);
            } catch (Exception e) {
                //这里不处理，会运行到下面的尝试json
            }
        }

        return null;
    }



    public static <T> T copyByJsonEx(T obj){
        try {
            return copyByJson(obj);
        } catch (Exception e) {
            //这里不处理，下面返回null
        }
        return null;
    }




    /**
     * 深层拷贝 - 需要类继承序列化接口
     * @param <T>
     * @param obj
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T copyImplSerializable(T obj) throws Exception {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;

        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;

        Object o = null;
        //如果子类没有继承该接口，这一步会报错
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);

            o = ois.readObject();
            return (T) o;
        } catch (Exception e) {
            throw new Exception("对象中包含没有继承序列化的对象");
        } finally{
            try {
                baos.close();
                oos.close();
                bais.close();
                ois.close();
            } catch (Exception e2) {
                //这里报错不需要处理
            }
        }
    }



    /**
     *
     * @param <T>
     * @param obj
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private static <T> T copyByJson(T obj) throws Exception {
        return (T)JSON.parseObject(JSON.toJSONString(obj),obj.getClass());
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


    /**
     * 利用反射机制对对象进行深拷贝,采用了递归方式
     * @param obj
     * @return
     */
    public static <T> T cloneObject(T obj) {
        if (!needCopy(obj)){
            return obj;
        }

        try {
            T cloneObj = (T)(obj.getClass().newInstance());
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object fieldValue = field.get(obj);
                if (fieldValue == null || Modifier.isFinal(field.getModifiers())) {
                    continue;
                }

                if (!needCopy(field.getType())) {
                    field.set(cloneObj, fieldValue);
                } else {
                    Object childObj = field.get(obj);
                    if (childObj == obj) {
                        field.set(cloneObj, cloneObj);
                    } else {
                        field.set(cloneObj, cloneObject(childObj));
                    }
                }
            }
            return cloneObj;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }



    /**
     * 利用反射机制对对象进行深拷贝,采用了递归方式
     * @param
     * @return
     */
    public static <T> Collection<T> cloneObject(final Collection<T> collection) {
        try {
            Collection<T> cloneCollection = collection.getClass().newInstance();
            for (T item : collection) {
                cloneCollection.add((T) cloneObject(item));
            }
            return cloneCollection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 利用反射机制对对象进行深拷贝,采用了递归方式
     * @param
     * @return
     */
    public static <K,V> Map<K,V> cloneObject(final Map<K,V> sourceMap) {
        try {
            Map<K,V> cloneMap = sourceMap.getClass().newInstance();
            for(Map.Entry<K, V> entry : sourceMap.entrySet()){
                K k = entry.getKey();
                V v = entry.getValue();
                K cloneK = cloneObject(k);
                V cloneV = cloneObject(v);
                cloneMap.put(cloneK, cloneV);
            }
            return cloneMap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
