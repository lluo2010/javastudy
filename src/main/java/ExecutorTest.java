import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by louis on 2018/7/13.
 */
public class ExecutorTest {
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    public void test() {
        executorService.execute(new RunTask());
        executorService.execute(new RunTask());
        executorService.execute(new RunTask());
    }
}


//线程加载
class RunTask implements Runnable {
    public void run() {
        System.out.println("thread:"+Thread.currentThread().getName());
    }

}


