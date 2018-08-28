package others;

/**
 * Created by louis on 2018/8/2.
 * 验证静态变量/静态代码块，  一般变量/代码块的执行顺序
 */
public class MyTestInfo {

    private int age = 1;

    public static String staticFirstName = "first";

    static {
        System.out.println("static block init, staticFirstName:"+staticFirstName);
    }

    {
        System.out.println("block init, staticFirstName:"+staticFirstName+",age:"+age);
    }

    public static String staticLastName = "last";

    public MyTestInfo() {
        System.out.println("MYTestInfo constructor, staticFistName:"+staticFirstName);
    }


    public static void main(String[] args) {
        MyTestInfo info = new MyTestInfo();
    }
}
