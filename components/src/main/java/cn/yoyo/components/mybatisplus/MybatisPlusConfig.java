package cn.yoyo.components.mybatisplus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author K
 * @since 2019/7/9
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public SqlInjector sqlInjector() {
        return new SqlInjector();
    }
}
