package top.lrshuai.aqs.king;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.CountDownLatch;

@Data
@Accessors(chain = true)
public class Team {
    private String teamName;
    private CountDownLatch defensive;
    private CountDownLatch crystal;

}
