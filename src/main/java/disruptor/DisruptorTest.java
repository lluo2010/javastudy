package disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.*;

/**
 * Created by louis on 2018/7/21.
 */
public class DisruptorTest {
    public static void main(String[] args) {
        //test1(); //执行1
        //test123(); //1，2，3顺序执行
        //test12then3(); //先1，2同时执行，都完成后，然后3
        test1then23then4(); //先1, 再2，3同时执行，都完成后，然后4
    }


    //执行1
    static void test1(){
        int ringBufferSize = 1024 * 1024; // RingBuffer 大小，必须是 2 的 N 次方；
        EventFactory<MyEventInfo> eventFactory = new EventFactory<MyEventInfo>() {
            public MyEventInfo newInstance() {
                return new MyEventInfo();
            }
        };
        ThreadFactory threadFactory = new ThreadFactory() {
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };
        Disruptor<MyEventInfo> disruptor = new Disruptor<MyEventInfo>(eventFactory,
                ringBufferSize, threadFactory, ProducerType.SINGLE,
                new YieldingWaitStrategy());

        EventHandler<MyEventInfo> handler1 = new EventHandler1();
        disruptor.handleEventsWith(handler1);

        disruptor.start();//启动
        CountDownLatch latch=new CountDownLatch(1);

        MyProducer producer = new MyProducer(latch, disruptor);
        //生产者准备
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(producer);

        try {
            latch.await();//等待生产者完事.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        disruptor.shutdown();
        executor.shutdown();
    }



    //1,2,3,4顺序执行
    static void test123(){
        int ringBufferSize = 1024 * 1024; // RingBuffer 大小，必须是 2 的 N 次方；
        EventFactory<MyEventInfo> eventFactory = new EventFactory<MyEventInfo>() {
            public MyEventInfo newInstance() {
                return new MyEventInfo();
            }
        };
        ThreadFactory threadFactory = new ThreadFactory() {
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };
        Disruptor<MyEventInfo> disruptor = new Disruptor<MyEventInfo>(eventFactory,
                ringBufferSize, threadFactory, ProducerType.SINGLE,
                new YieldingWaitStrategy());

        EventHandler<MyEventInfo> handler1 = new EventHandler1();
        EventHandler<MyEventInfo> handler2 = new EventHandler2();
        EventHandler<MyEventInfo> handler3 = new EventHandler3();

        disruptor.handleEventsWith(handler1).handleEventsWith(handler2).handleEventsWith(handler3);

        disruptor.start();//启动
        CountDownLatch latch=new CountDownLatch(1);

        MyProducer producer = new MyProducer(latch, disruptor);
        //生产者准备
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(producer);

        try {
            latch.await();//等待生产者完事.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        disruptor.shutdown();
        executor.shutdown();
    }

    //先1，2同时执行，都完成后，然后3
    static void test12then3(){
        int ringBufferSize = 1024 * 1024; // RingBuffer 大小，必须是 2 的 N 次方；
        EventFactory<MyEventInfo> eventFactory = new EventFactory<MyEventInfo>() {
            public MyEventInfo newInstance() {
                return new MyEventInfo();
            }
        };
        ThreadFactory threadFactory = new ThreadFactory() {
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };
        Disruptor<MyEventInfo> disruptor = new Disruptor<MyEventInfo>(eventFactory,
                ringBufferSize, threadFactory, ProducerType.SINGLE,
                new YieldingWaitStrategy());

        EventHandler<MyEventInfo> handler1 = new EventHandler1();
        EventHandler<MyEventInfo> handler2 = new EventHandler2();
        EventHandler<MyEventInfo> handler3 = new EventHandler3();
        //disruptor.handleEventsWith(logEventHandler);
        EventHandlerGroup<MyEventInfo> myEventInfoEventHandlerGroup = disruptor.handleEventsWith(handler1, handler2);
        myEventInfoEventHandlerGroup.then(handler3);

        disruptor.start();//启动
        CountDownLatch latch=new CountDownLatch(1);

        MyProducer producer = new MyProducer(latch, disruptor);
        //生产者准备
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(producer);

        try {
            latch.await();//等待生产者完事.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        disruptor.shutdown();
        executor.shutdown();
    }


    //先1, 再2，3同时执行，都完成后，然后4
    static void test1then23then4(){
        int ringBufferSize = 1024 * 1024; // RingBuffer 大小，必须是 2 的 N 次方；
        EventFactory<MyEventInfo> eventFactory = new EventFactory<MyEventInfo>() {
            public MyEventInfo newInstance() {
                return new MyEventInfo();
            }
        };
        ThreadFactory threadFactory = new ThreadFactory() {
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };
        Disruptor<MyEventInfo> disruptor = new Disruptor<MyEventInfo>(eventFactory,
                ringBufferSize, threadFactory, ProducerType.SINGLE,
                new YieldingWaitStrategy());

        EventHandler<MyEventInfo> handler1 = new EventHandler1();
        EventHandler<MyEventInfo> handler2 = new EventHandler2();
        EventHandler<MyEventInfo> handler3 = new EventHandler3();
        EventHandler<MyEventInfo> handler4 = new EventHandler4();
        disruptor.handleEventsWith(handler1);
        disruptor.after(handler1).handleEventsWith(handler2,handler3);
        disruptor.after(handler2,handler3).handleEventsWith(handler4);

        disruptor.start();//启动
        CountDownLatch latch=new CountDownLatch(1);

        MyProducer producer = new MyProducer(latch, disruptor);
        //生产者准备
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(producer);

        try {
            latch.await();//等待生产者完事.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        disruptor.shutdown();
        executor.shutdown();
    }
}
