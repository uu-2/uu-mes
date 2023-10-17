package cn.yoyo.components.mybatisplus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus 配置类.
 *
 * @author YuRuizhi
 * @date 2023/10/17
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public SqlInjector sqlInjector() {
        return new SqlInjector();
    }
}
