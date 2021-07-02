package top.lrshuai.aqs.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int count = 10;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(count,count,60, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count, () -> {
            System.out.println("所有人到齐了，开始本次会议！！！");
        });
        MyTask myTask = new MyTask(cyclicBarrier);
        for (int i = 0; i < count; i++) {
            threadPoolExecutor.execute(new Thread(myTask));
        }
        threadPoolExecutor.shutdown();
    }
}
