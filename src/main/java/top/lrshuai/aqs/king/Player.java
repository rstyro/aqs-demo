package top.lrshuai.aqs.king;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player implements Runnable{
    private String name;
    private String team;
    private CyclicBarrier prepare;
    private CyclicBarrier begin;
    private Vector<String> heros;
    private AtomicBoolean isOver;
    /**
     * 是否开始
     */
    private boolean run=false;

    Player(String team, CyclicBarrier prepare,Vector<String> heros,AtomicBoolean isOver){
        this.team=team;
        this.prepare=prepare;
        this.heros=heros;
        this.isOver=isOver;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId()+" 准备好了....");
        try {
            prepare.await();
            synchronized (Player.class){
                Random r = new Random(heros.size());
                this.name = heros.remove(r.nextInt());
            }
            System.out.println(Thread.currentThread().getId() + "选择了英雄："+this.name);
            begin.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(name+" 进入战场....");
        working();
        System.out.println(name+" 退出战场....");
    }

    public void start() {
        this.run = true;
    }

    public void over() {
        this.run = false;
    }

    private void working(){
        while (run){

        }
    }

    public String getName() {
        return name;
    }
}
