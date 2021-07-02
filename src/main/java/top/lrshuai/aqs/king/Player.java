package top.lrshuai.aqs.king;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player implements Runnable {
    /**
     * 角色名
     */
    private String name;

    /**
     * 准备
     */
    private CyclicBarrier prepare;

    /**
     * 团队名称
     */
    private Team team;

    /**
     * 需要攻击的目标团队
     */
    private Team targetTeam;

    /**
     * 可选英雄池
     */
    private Vector<String> heroList;
    /**
     * 游戏是否结束
     */
    private AtomicBoolean isOver;

    /**
     * 是否开始
     */
    private boolean run = false;

    Player(Team team, Team targetTeam, CyclicBarrier prepare, Vector<String> heroList, AtomicBoolean isOver) {
        this.team = team;
        this.targetTeam = targetTeam;
        this.prepare = prepare;
        this.heroList = heroList;
        this.isOver = isOver;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getId() + " 准备好了....");
            prepare.await();
            synchronized (Player.class) {
                Random r = new Random();
                this.name = heroList.remove(r.nextInt(heroList.size() - 1));
            }
            System.out.println("玩家-" + Thread.currentThread().getId() + "选择了英雄：" + getName() + ", 队伍：" + team.getTeamName());
            prepare.await();
            System.out.println(name + " 进入战场....");
            start();
            playing();
            // 等所有玩家结束，然后退出游戏
            prepare.await();
            System.out.println(name + " 退出战场....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void playing() {
        while (run) {
            try {
                if (isOver.get()) {
                    break;
                }
                Random random = new Random();
                Thread.sleep(1000 * (random.nextInt(5) +1));
                if (targetTeam.getDefensive().getAndDecrement() > 0) {
                    System.out.println(getName() + " 摧毁" + targetTeam.getTeamName() + "一座防御塔...");
                } else  {
                    if (targetTeam.getCrystal().decrementAndGet() == 0 && !isOver.getAndSet(Boolean.TRUE)) {
                        System.out.println(getName() + " 摧毁"+targetTeam.getTeamName()+"基地水晶，" + team.getTeamName() + " 获得游戏胜利!!!");
                    }
                    break;
                }
            } catch (InterruptedException e) {
                break;
            }
        }
        over();
    }

    public void start() {
        this.run = true;
    }

    public void over() {
        this.run = false;
    }

    public String getName() {
        return name;
    }

}
