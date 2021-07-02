package top.lrshuai.aqs.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyTask implements Runnable{

    private CyclicBarrier cyclicBarrier;

    public MyTask(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier=cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+" 进入会议...");
            cyclicBarrier.await();
            System.out.println("会议结束，"+Thread.currentThread().getName()+" 离开会议...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
