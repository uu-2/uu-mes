package cn.yoyo.module.aps.domain;


import cn.yoyo.module.aps.domain.points.DeviceEventRepository;
import cn.yoyo.module.aps.domain.entity.DeviceEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 设备事件上报业务
 */
@Component
public class DeviceEventBiz {
    private final DeviceEventRepository deviceEventRepository;
    private final ApplicationContext applicationContext;

    public DeviceEventBiz(DeviceEventRepository deviceEventRepository, ApplicationContext applicationContext) {
        this.deviceEventRepository = deviceEventRepository;
        this.applicationContext = applicationContext;
    }

    /**
     * 接收设备事件
     * @param event
     * @return
     */
    public DeviceEvent receiveEvent(DeviceEvent event) {
        event.setReceiveTime(System.currentTimeMillis());
        applicationContext.publishEvent(event);
        return this.deviceEventRepository.save(event);
    }
}
