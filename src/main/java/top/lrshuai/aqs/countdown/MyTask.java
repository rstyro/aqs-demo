package top.lrshuai.aqs.countdown;

import java.util.concurrent.CountDownLatch;

public class MyTask implements Runnable{

    private CountDownLatch countDownLatch;

    public MyTask(CountDownLatch countDownLatch){
        this.countDownLatch=countDownLatch;
    }

    public void run() {
        System.out.println("run...");
        countDownLatch.countDown();
    }
}
