package eventbus;

import com.google.common.eventbus.EventBus;

/**
 * Created by louis on 2018/6/6.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        EventBus bus = new EventBus();
        Handler1 handler1 = new Handler1();
        Handler2 handler2 = new Handler2();
        bus.register(handler1);
        bus.register(handler2);
        Event1 event1 = new Event1(1,"name1");
        bus.post("name");
        bus.post(event1);

        Thread.sleep(6000);

    }
}
