package cn.yoyo.module.aps.infra.repository;


import cn.yoyo.components.biz.infra.repository.CrudBaseRepoMongoImpl;
import cn.yoyo.module.aps.domain.points.DeviceEventRepository;
import cn.yoyo.module.aps.domain.entity.DeviceEvent;
import cn.yoyo.module.aps.infra.repository.mongo.dto.DeviceEventConvert;
import cn.yoyo.module.aps.infra.repository.mongo.dto.TDeviceEvent;
import cn.yoyo.module.aps.infra.repository.mongo.mapper.DeviceEventMapper;
import org.springframework.stereotype.Component;

@Component
public class DeviceEventRepositoryImpl extends CrudBaseRepoMongoImpl<TDeviceEvent, Long, DeviceEvent>
        implements DeviceEventRepository {

    protected DeviceEventRepositoryImpl(DeviceEventMapper mapper, DeviceEventConvert converter) {
        super(mapper, converter);
    }
}
