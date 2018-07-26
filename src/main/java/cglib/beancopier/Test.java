package cglib.beancopier;

import net.sf.cglib.beans.BeanCopier;

/**
 * Created by louis on 2018/7/26.
 */
public class Test {

    public static void main(String[] args) {
        //basicTest();
        cloneCheckTest();


    }


    static void basicTest(){
        System.out.println("basicTest~~~");
        People p1 = new People(1,"luo");
        BeanCopier beanCopier = BeanCopier.create(People.class, People.class, false);
        People p2 = new People();
        beanCopier.copy(p1, p2, null);
        System.out.println("result:"+p2);
        p1.setAge(2);
    }


    //判断是否是深拷贝
    static  void cloneCheckTest(){
        System.out.println("cloneCheckTest~~~");
        People p1 = new People(1,"luo");
        Family f1 = new Family(p1, 1);
        BeanCopier beanCopier = BeanCopier.create(Family.class, Family.class, false);
        Family f2 = new Family();
        beanCopier.copy(f1, f2, null);
        System.out.println("result: f2"+f2);

        f1.getPeople().setAge(2);
        f1.getPeople().setName("2");
        System.out.println("f1:"+f1);
        System.out.println("f2:"+f2);
    }

}
