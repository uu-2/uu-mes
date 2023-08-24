package cn.yoyo.module.aps.infra;


import cn.yoyo.components.biz.infra.repository.CrudBaseRepoMongoImpl;
import cn.yoyo.module.aps.domain.adapter.DeviceEventRepository;
import cn.yoyo.module.aps.domain.entity.DeviceEvent;
import cn.yoyo.module.aps.infra.mongo.dto.DeviceEventConvert;
import cn.yoyo.module.aps.infra.mongo.dto.TDeviceEvent;
import cn.yoyo.module.aps.infra.mongo.mapper.DeviceEventMapper;
import org.springframework.stereotype.Component;

@Component
public class DeviceEventRepositoryImpl extends CrudBaseRepoMongoImpl<TDeviceEvent, Long, DeviceEvent>
        implements DeviceEventRepository {

    protected DeviceEventRepositoryImpl(DeviceEventMapper mapper, DeviceEventConvert converter) {
        super(mapper, converter);
    }
}
