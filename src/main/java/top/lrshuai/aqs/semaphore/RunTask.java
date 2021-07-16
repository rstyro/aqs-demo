package top.lrshuai.aqs.semaphore;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class RunTask implements Runnable{

    private String name;
    private Semaphore semaphore;

    private CountDownLatch countDownLatch;

    private Vector<String> vector;

    public RunTask(String name,Semaphore semaphore,CountDownLatch countDownLatch,Vector<String> vector){
        this.name=name;
        this.semaphore=semaphore;
        this.countDownLatch=countDownLatch;
        this.vector=vector;
    }

    @Override
    public void run() {
        try {
            System.out.println(name+" 等待中....");
            semaphore.acquire();
            vector.remove(name);
            System.out.println(name+" 进入厕所，使用中...目前等待有："+vector.toString());
            Thread.sleep((long)(Math.random()*10000));
            System.out.println(name+"使用完毕，退出");
            semaphore.release();
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
