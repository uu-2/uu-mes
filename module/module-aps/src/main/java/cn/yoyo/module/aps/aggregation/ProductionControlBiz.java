package cn.yoyo.module.aps.aggregation;

import cn.yoyo.module.aps.domain.entity.DeviceEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ProductionControlBiz {
    @Async
    @EventListener(DeviceEvent.class)
    public void handleDeviceEvent(DeviceEvent event) {
        System.out.printf("handle device event: %s\n", event.getId());
        // 更新产线设备状态

    }
}
