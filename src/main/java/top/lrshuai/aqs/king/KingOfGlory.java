package top.lrshuai.aqs.king;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class KingOfGlory {
    public static void main(String[] args) {
        int count=10;
        int disableCount=3;
        int defensive=3;
        int crystal=1;
        AtomicBoolean isOver = new AtomicBoolean(Boolean.FALSE);
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(count,count,60, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
        Vector<String> heroList = freeHeroList();
        Team redTeam = new Team().setTeamName("红队").setCrystal(new CountDownLatch(crystal)).setDefensive(new CountDownLatch(defensive));
        Team blueTeam = new Team().setTeamName("蓝队").setCrystal(new CountDownLatch(crystal)).setDefensive(new CountDownLatch(defensive));
        CyclicBarrier prepare = new CyclicBarrier(count,()->{
            Random random = new Random();
            for (int i = 0; i < disableCount; i++) {
                System.out.println("禁用英雄"+heroList.remove(random.nextInt(heroList.size()-1)));
            }
        });

        CyclicBarrier begin=new CyclicBarrier(count,()->{
            System.out.println("开始游戏");
        });

        for (int i = 0; i < count; i++) {
            executorPool.execute(()->{

            });
        }


        // 结束游戏
        while (isOver.get()){
            executorPool.shutdown();
        }
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
        list.add("凯");
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
