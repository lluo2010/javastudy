package Utils;

/**
 * Created by louis on 2018/7/26.
 */
public class Utils {

    /**
     * 是否需要进行对象copy
     * @param
     * @return
     */
    public static <T> boolean needCopy(final T t) {
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
