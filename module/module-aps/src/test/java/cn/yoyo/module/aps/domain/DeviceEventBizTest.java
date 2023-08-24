package cn.yoyo.module.aps.domain;

import cn.yoyo.module.aps.TestApplication;
import cn.yoyo.module.aps.domain.entity.DeviceEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestApplication.class)
class DeviceEventBizTest {

    @Autowired
    private DeviceEventBiz deviceEventBiz;
    @Test
    void receiveEvent() {
        DeviceEvent result = deviceEventBiz.receiveEvent(DeviceEvent.builder()
                .deviceId(System.currentTimeMillis())
                        .eventContent(Map.of("a", "b", "c", "d", "e", "f"))
                .build());

        System.out.printf("result: %s\n", result.getId());
    }
}