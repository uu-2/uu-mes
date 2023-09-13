package cn.yoyo.module.aps;


import com.dtflys.forest.springboot.annotation.ForestScan;
import org.dromara.dynamictp.core.spring.EnableDynamicTp;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@ComponentScan(basePackages = "cn.yoyo")
@MapperScan(basePackages = "cn.yoyo.**.infra.repository.mybatis.mapper")
@ForestScan(basePackages = "cn.yoyo.**.sdk.clients")
//@EnableAsync
//@EnableDynamicTp
public class TestApplication {
    private static AtomicInteger sn = new AtomicInteger(0);

    public static void main(String[] args) {
        runnable(0, 1, "A").start();
        runnable(1, 2, "B").start();
        Thread c = runnable(2, 0, "C");
        c.start();
        try {
            c.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Thread runnable(int s, int nexts, String str) {
        return new Thread(() -> {
            synchronized (sn) {
                for (int i = 0; i < 100; ) {
                    try {
                        if (sn.get() == s) {
                            System.out.println(str);
                            sn.set(nexts);
                            i++;
                            sn.notifyAll();
                        } else {
                            sn.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
