package cn.yoyo.facade.admin.endpoints.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.tags.Tag;
import cn.yoyo.components.biz.CrudBaseApi;
import cn.yoyo.module.aps.domain.entity.Device;
import cn.yoyo.module.aps.domain.DeviceBiz;

/**
 * Device 业务服务。
 *
 * @author three3q
 * @date 2023-08-25
 */
@RestController
@RequestMapping("/api/aps/device")
@Tag(name = "设备信息表")
public class DeviceApi extends CrudBaseApi<Device, Long, DeviceBiz> {

    public DeviceApi(DeviceBiz crudBiz) {
        super(crudBiz);
    }
}
