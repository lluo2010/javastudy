package disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * Created by louis on 2018/7/21.
 */
public class EventHandler3 implements EventHandler<MyEventInfo> {
    public void onEvent(MyEventInfo myEventInfo, long l, boolean b) throws Exception {
        System.out.println("EventHandler3:[" + Thread.currentThread().getName() + "]" + myEventInfo);
    }
}
