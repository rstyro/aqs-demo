package top.lrshuai.aqs.king;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class KingOfGlory {
    public static void main(String[] args) throws InterruptedException {
        // 玩家个数
        int count=10;
        // 禁用英雄个数
        int disableCount=3;
        // 每队的防御塔个数
        int defensive=5;
        // 每队的水晶个数
        int crystal=1;
        AtomicBoolean isOver = new AtomicBoolean(Boolean.FALSE);
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(count,count,60, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
        Vector<String> heroList = freeHeroList();
        // 队伍
        Team redTeam = new Team().setTeamName("红队").setCrystal(new AtomicInteger(crystal)).setDefensive(new AtomicInteger(defensive));
        Team blueTeam = new Team().setTeamName("蓝队").setCrystal(new AtomicInteger(crystal)).setDefensive(new AtomicInteger(defensive));
        CountDownLatch step = new CountDownLatch(3);
        CyclicBarrier prepare = new CyclicBarrier(count,()->{
            step.countDown();
            if(step.getCount() == 2){
                Random random = new Random();
                if(!isOver.get()){
                    for (int i = 0; i < disableCount; i++) {
                        System.out.println("禁用英雄"+heroList.remove(random.nextInt(heroList.size()-1)));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }else if(step.getCount() == 1){
                System.out.println("开始游戏...");
            }
            else if(step.getCount() == 0){
                System.out.println("游戏结束...");
            }
        });

        // 开始构建玩家
        for (int i = 0; i < count; i++) {
            if(i%2==0){
                executorPool.execute(new Player(redTeam, blueTeam, prepare, heroList, isOver));
            }else {
                executorPool.execute(new Player(blueTeam, redTeam, prepare,heroList, isOver));
            }
        }
        // 等待游戏结束
        step.await();
        executorPool.shutdown();

    }

    public static Vector<String> freeHeroList(){
        Vector<String> list = new Vector<>();
        list.add("后羿");
        list.add("艾琳");
        list.add("成吉思汗");
        list.add("狄仁杰");
        list.add("鲁班七号");
        list.add("亚瑟");
        list.add("白起");
        list.add("狂铁");
        list.add("凯爹");
        list.add("孙策");
        list.add("钟无艳");
        list.add("妲己");
        list.add("安吉拉");
        list.add("杨玉环");
        list.add("小乔");
        list.add("大乔");
        list.add("貂蝉");
        list.add("诸葛亮");
        list.add("阿轲");
        list.add("韩信");
        list.add("李白");
        list.add("赵云");
        list.add("孙悟空");
        list.add("百里玄策");
        return list;
    }
}
