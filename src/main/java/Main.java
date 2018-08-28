import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2018/6/6.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("hello");

/*        int allFriendsCount = 0;
        Double myFriendsCount = 0D;
        Double result = myFriendsCount / allFriendsCount;
        if (result==null){
            System.out.println("it's null");
        }
        if (result.isNaN()){
            System.out.println("it's NaN");
        }

        System.out.println(result.toString());

        BigDecimal.valueOf(myFriendsCount/allFriendsCount);
        MyThread thread = new MyThread("thread1");
        thread.start();
        System.out.println("~~~~over");*/


        String s = JSON.toJSONString(null);
        System.out.println("info:"+s);
        Object o = JSON.parseObject("", Object.class);
        if (o==null){
            System.out.println("it's null");
        }else{
            System.out.println("it's not null");
        }
    }



    public static class MyThread extends Thread{
        private String mName;
        public MyThread(String name) {
            super(name);
            mName = name;
        }

        @Override
        public void run() {
            super.run();
            for (int i=0; i<10; i++) {
                System.out.println(mName+" run:" +i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
