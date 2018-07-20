package eventbus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

/**
 * Created by louis on 2018/6/6.
 */
public class Handler1 {

    @Subscribe
    public void handle(final Event1 e) throws Exception {
        System.out.println("handle:"+e);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("event over");
    }

    @Subscribe
    private void handle(final String str){
        System.out.println("handle:"+str);
    }
}
