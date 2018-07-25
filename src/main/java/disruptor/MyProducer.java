package disruptor;

import com.lmax.disruptor.EventTranslatorTwoArg;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;

/**
 * Created by louis on 2018/7/20.
 */
public class MyProducer implements Runnable{
    CountDownLatch mLatch;
    Disruptor<MyEventInfo> disruptor;

    public MyProducer(CountDownLatch mLatch, Disruptor<MyEventInfo> disruptor) {
        this.mLatch = mLatch;
        this.disruptor = disruptor;
    }

    public void run() {
        MyEventInfoTranslator translator = new MyEventInfoTranslator();
        for (int i = 0; i < 10; i++) {
            disruptor.publishEvent(translator, i, String.valueOf(i));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mLatch.countDown();
    }
}
