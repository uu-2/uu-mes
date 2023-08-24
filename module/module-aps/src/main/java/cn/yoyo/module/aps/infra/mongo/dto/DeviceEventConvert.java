package cn.yoyo.module.aps.infra.mongo.dto;

import cn.yoyo.components.biz.convert.ConvertTemplate;
import cn.yoyo.module.aps.domain.entity.DeviceEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeviceEventConvert extends ConvertTemplate<DeviceEvent, TDeviceEvent> {
}
