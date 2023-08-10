package cn.yoyo.components.executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfiguration {

    @Bean
    public Executor taskExecutor() {
        return Executors.newCachedThreadPool(100);
    }
}
