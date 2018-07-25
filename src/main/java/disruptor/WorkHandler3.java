package disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * Created by louis on 2018/7/21.
 */
public class WorkHandler3 implements WorkHandler<MyEventInfo> {
    public void onEvent(MyEventInfo myEventInfo) throws Exception {
        System.out.println("WorkHandler3:[" + Thread.currentThread().getName() + "]" + myEventInfo);
    }
}
