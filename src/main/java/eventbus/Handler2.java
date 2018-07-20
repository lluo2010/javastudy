package eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * Created by louis on 2018/6/6.
 */
public class Handler2 {

    @Subscribe
    public void handle(final Event1 e){
        System.out.println("handle2:"+e);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("handle2 event over");
    }
}
