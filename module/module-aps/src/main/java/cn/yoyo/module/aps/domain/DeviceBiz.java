package cn.yoyo.module.aps.domain;

import org.springframework.stereotype.Component;
import cn.yoyo.components.biz.CrudBaseBiz;
import cn.yoyo.module.aps.domain.entity.Device;
import cn.yoyo.module.aps.domain.points.DeviceRepository;

/**
 * Device 业务服务。
 *
 * @author three3q
 * @since 2023-08-25
 */
@Component
public class DeviceBiz extends CrudBaseBiz<Device,Long> {

    public DeviceBiz(DeviceRepository repo) {
        super(repo);
    }
}
