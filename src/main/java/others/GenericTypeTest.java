package others;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by louis on 2018/7/25.
 * 泛型相关
 *
 */
public class GenericTypeTest {


    public static void main(String[] args) {
        testType();
        testParam(new Integer(1));
        testParam(new ArrayList<String>());
    }


    private static void testType(){
        System.out.println("testType~~~");
        Erasure<String> erasure = new Erasure<String>("hello");
        Class eclz = erasure.getClass();
        System.out.println("erasure class is:"+eclz.getName());


        Field[] fs = eclz.getDeclaredFields();
        for ( Field f:fs) {
            System.out.println("Field name "+f.getName()+" type:"+f.getType().getName());
        }
    }


    private static <V> V testParam(V v){
        System.out.println("test param~~~~");
        System.out.println("type of v:"+v.getClass().getName());
        return v;
    }
}



class Erasure <T>{
    T object;

    public Erasure(T object) {
        System.out.println("Erasure~~~");
        this.object = object;
        System.out.println("param object class type"+object.getClass().getName());
        System.out.println("member object class type"+this.object.getClass().getName());
    }

}
