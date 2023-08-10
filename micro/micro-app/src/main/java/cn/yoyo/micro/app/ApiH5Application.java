package cn.yoyo.micro.app;

import com.dtflys.forest.springboot.annotation.ForestScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@EnableAsync
@ComponentScan(basePackages = "cn.yoyo")
@MapperScan(basePackages = "cn.yoyo.**.infra.repository.mybatis.mapper")
@ForestScan(basePackages = "cn.yoyo.**.infra.clients")
public class ApiH5Application {

    public static void main(String[] args) {
        SpringApplication.run(ApiH5Application.class, args);
    }

}
