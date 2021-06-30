package top.lrshuai.aqs.countdown;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //赛道个数，一人一道
        int count = 6;
        //指挥官
        final CountDownLatch masterCountDownLatch = new CountDownLatch(1);
        //闭锁，可实现计数器递减
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        AtomicInteger atomicInteger = new AtomicInteger(1);
        System.out.println("校园400米赛跑，即将开始");
        Thread.sleep(1*1000);
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(count,count,60,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < count ; i++) {
            executorPool.execute(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"准备好了");
                    //比赛选择准备
                    masterCountDownLatch.await();
                    System.out.println(Thread.currentThread().getName()+"拼命奔跑中");
                    Thread.sleep((long)(Math.random()*10000));
                    if(atomicInteger.decrementAndGet() >= 0){
                        System.out.println("冠军诞生了 "+Thread.currentThread().getName()+"首先到达终点");
                    }else{
                        System.out.println("其次 "+Thread.currentThread().getName()+"到达终点");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //闭锁减一
                countDownLatch.countDown();
            });
        }
        Thread.sleep(5*1000);
        System.out.println("预备，开始比赛");
        // 准备结束，开始比赛，处于等待的线程继续执行任务
        masterCountDownLatch.countDown();

        countDownLatch.await();//线程阻塞，直到闭锁值为0时，阻塞才释放，继续往下执行
        System.out.println("比赛结束");
        executorPool.shutdown();
    }
}
