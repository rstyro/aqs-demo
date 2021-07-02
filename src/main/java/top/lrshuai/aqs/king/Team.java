package top.lrshuai.aqs.king;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Accessors(chain = true)
public class Team {
    private String teamName;
    /**
     * 防御塔
     */
    private AtomicInteger defensive;
    /**
     * 基地水晶
     */
    private AtomicInteger crystal;

}
