package cn.yoyo.module.aps;


import com.dtflys.forest.springboot.annotation.ForestScan;
import org.dromara.dynamictp.core.spring.EnableDynamicTp;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = "cn.yoyo")
@MapperScan(basePackages = "cn.yoyo.**.infra.repository.mybatis.mapper")
@ForestScan(basePackages = "cn.yoyo.**.sdk.clients")
//@EnableAsync
//@EnableDynamicTp
public class TestApplication {
}
