package cn.yoyo.module.aps.domain;


import cn.yoyo.module.aps.domain.adapter.DeviceEventRepository;
import cn.yoyo.module.aps.domain.entity.DeviceEvent;
import org.springframework.stereotype.Component;

@Component
public class DeviceEventBiz {
    private final DeviceEventRepository deviceEventRepository;

    public DeviceEventBiz(DeviceEventRepository deviceEventRepository) {
        this.deviceEventRepository = deviceEventRepository;
    }

    public DeviceEvent receiveEvent(DeviceEvent event) {
        event.setReceiveTime(System.currentTimeMillis());
        return this.deviceEventRepository.save(event);
    }
}
