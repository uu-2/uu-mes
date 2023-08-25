package cn.yoyo.module.aps.infra.repository;

import org.springframework.stereotype.Component;
import cn.yoyo.components.biz.infra.repository.CrudBaseRepoMybatisImpl;
import cn.yoyo.module.aps.domain.entity.Device;
import cn.yoyo.module.aps.domain.points.DeviceRepository;
import cn.yoyo.module.aps.infra.repository.mybatis.dto.TDevice;
import cn.yoyo.module.aps.infra.repository.mybatis.dto.TDeviceConvert;
import cn.yoyo.module.aps.infra.repository.mybatis.mapper.TDeviceMapper;

/**
 * Device 存储层实现。
 *
 * @author three3q
 * @since 2023-08-25
 */
@Component
public class DeviceRepositoryImpl extends CrudBaseRepoMybatisImpl<TDevice,Device>
    implements DeviceRepository {

    protected DeviceRepositoryImpl(TDeviceMapper mapper, TDeviceConvert converter) {
        super(mapper, converter);
    }
}
