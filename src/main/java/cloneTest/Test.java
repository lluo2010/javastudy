package cloneTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.Serializable;
import java.util.*;

/**
 * Created by louis on 2018/7/24.
 */
public class Test {

    private static int testNum = 100000;
    public static void main(String[] args) {
        MyObject obj1 = new MyObject(1, "1");
        MyObject object = DeepCopy.cloneObject(obj1);
        System.out.println("result:"+object);


        List<MyObject> list = new ArrayList<MyObject>();
        MyObject object1 = new MyObject(1, "1");
        MyObject object2 = new MyObject(2, "2");
        MyObject object3 = new MyObject(3, "3");
        list.add(object1);
        list.add(object2);
        list.add(object3);

        String s = JSON.toJSONString(list);
        System.out.println(s);
        ArrayList<String> strings = JSON.parseObject(s, new TypeReference<ArrayList<String>>() {
        });

        /*System.out.println("obj:"+obj1);
        //MyObject myObject = DeepCopy.copyByJsonEx(obj1);
        //System.out.println("new obj:"+myObject);


        MyObject myObject1 = DeepCopy.copyByStream(obj1);
        System.out.println("new obj by stream:"+myObject1);*/
/*        streamCloneTest();
        jsonCloneTest();
        ObjectCloneTest();*/
/*        test1(1);
        test1("hello");
        test1(new Object());
        test1(new MyObject());*/
        //testListClone();
        //testMapClone();
    }



    private static <T>  void test(T t){
        String simpleName = t.getClass().getSimpleName();
        System.out.println(simpleName);
    }

    private static void testListClone(){
        List<MyObject> list = new ArrayList<MyObject>();
        MyObject object1 = new MyObject(1, "1");
        MyObject object2 = new MyObject(2, "2");
        MyObject object3 = new MyObject(3, "3");
        list.add(object1);
        list.add(object2);
        list.add(object3);
        Collection<MyObject> myObjects = DeepCopy.cloneObject(list);
        for (MyObject obj:myObjects){
            System.out.println(obj);
        }
    }


    private static void testMapClone(){
        Map<String, MyObject> map1 = new HashMap<String, MyObject>();
        MyObject object1 = new MyObject(1, "1");
        MyObject object2 = new MyObject(2, "2");
        MyObject object3 = new MyObject(3, "3");
        map1.put("1", object1);
        map1.put("2", object2);
        map1.put("3", object3);
        Map<String, MyObject> stringMyObjectMap = DeepCopy.cloneObject(map1);
        stringMyObjectMap.entrySet().stream().forEach(entry->{
            System.out.println(entry.getKey()+":"+entry.getValue());
        });
    }


    private static <T >void test1(T obj){
        System.out.println("test1");
    }

    private static void test1(String obj){
        System.out.println("test2");
    }


    private static void streamCloneTest() {
        MyObject obj1 = new MyObject(1, "1");
        long start = System.currentTimeMillis();
        for (int i = 0; i < testNum; i++) {
            MyObject myObject1 = DeepCopy.copyByStream(obj1);
            int k = 0;
        }
        System.out.println("stream finished time:" + (System.currentTimeMillis() - start));
    }



    private static void jsonCloneTest() {
        MyObject obj1 = new MyObject(1, "1");
        long start = System.currentTimeMillis();
        for (int i = 0; i < testNum; i++) {
            MyObject myObject1 = DeepCopy.copyByJsonEx(obj1);
            int k = 0;
        }

        System.out.println("json finished time:" + (System.currentTimeMillis() - start));
    }


    private static void ObjectCloneTest() {
        MyObject obj1 = new MyObject(1, "1");
        long start = System.currentTimeMillis();
        for (int i = 0; i < testNum; i++) {
            MyObject myObject1 = (MyObject) DeepCopy.cloneObject(obj1);
            int k = 0;
        }

        System.out.println("object finished time:" + (System.currentTimeMillis() - start));
    }

    private static void cloneList(){

    }
}


class MyObject implements Serializable{
    private int age;
    private String name;

    public MyObject() {
    }

    public MyObject(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
