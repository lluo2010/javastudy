package cglib.beancopier;

import Utils.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by louis on 2018/7/26.
 */
public class DeepClone {

    public static <T> T cloneObject(T obj) {

        if (!Utils.needCopy(obj)){
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

                if (!Utils.needCopy(field.getType())) {
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
}
