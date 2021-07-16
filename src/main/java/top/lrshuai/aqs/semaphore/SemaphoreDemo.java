package top.lrshuai.aqs.semaphore;

import top.lrshuai.aqs.commons.ThreadPool;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        //厕所坑位
        int count = 2;
        // 人数
        int peopleNum = 8;
        CountDownLatch countDownLatch = new CountDownLatch(peopleNum);
        Semaphore semaphore = new Semaphore(count);
        ThreadPoolExecutor pool = ThreadPool.getPool();
        Vector<String> vector = new Vector(peopleNum);
        System.out.println("目前有厕所位置："+count);
        for (int i = 0; i < peopleNum; i++) {
            String name = "编号:"+(i+1);
            Thread thread = new Thread(new RunTask(name,semaphore, countDownLatch,vector),name);
            vector.add(name);
            pool.execute(thread);
        }
        countDownLatch.await();
        pool.shutdown();

    }
}
