package cn.yoyo.module.aps.infra.mongo.mapper;

import cn.yoyo.module.aps.infra.mongo.dto.TDeviceEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceEventMapper extends MongoRepository<TDeviceEvent, Long> {
}
