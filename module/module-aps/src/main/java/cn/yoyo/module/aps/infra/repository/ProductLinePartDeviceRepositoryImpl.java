package cn.yoyo.module.aps.infra.repository;

import org.springframework.stereotype.Component;
import cn.yoyo.components.biz.infra.repository.CrudBaseRepoMybatisImpl;
import cn.yoyo.module.aps.domain.entity.ProductLinePartDevice;
import cn.yoyo.module.aps.domain.points.ProductLinePartDeviceRepository;
import cn.yoyo.module.aps.infra.repository.mybatis.dto.TProductLinePartDevice;
import cn.yoyo.module.aps.infra.repository.mybatis.dto.TProductLinePartDeviceConvert;
import cn.yoyo.module.aps.infra.repository.mybatis.mapper.TProductLinePartDeviceMapper;

/**
 * ProductLinePartDevice 存储层实现。
 *
 * @author three3q
 * @since 2023-08-25
 */
@Component
public class ProductLinePartDeviceRepositoryImpl extends CrudBaseRepoMybatisImpl<TProductLinePartDevice,ProductLinePartDevice>
    implements ProductLinePartDeviceRepository {

    protected ProductLinePartDeviceRepositoryImpl(TProductLinePartDeviceMapper mapper, TProductLinePartDeviceConvert converter) {
        super(mapper, converter);
    }
}
