package top.lrshuai.aqs.commons;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    public static ThreadPoolExecutor getPool(){
        return new ThreadPoolExecutor(10, 20,60, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
    }
}
