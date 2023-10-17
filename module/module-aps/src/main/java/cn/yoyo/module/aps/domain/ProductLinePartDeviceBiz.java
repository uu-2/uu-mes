package cn.yoyo.module.aps.domain;

import org.springframework.stereotype.Component;
import cn.yoyo.components.biz.CrudBaseBiz;
import cn.yoyo.module.aps.domain.entity.ProductLinePartDevice;
import cn.yoyo.module.aps.domain.points.ProductLinePartDeviceRepository;

/**
 * ProductLinePartDevice 业务服务。
 *
 * @author three3q
 * @date 2023-08-25
 */
@Component
public class ProductLinePartDeviceBiz extends CrudBaseBiz<ProductLinePartDevice, Long> {

    public ProductLinePartDeviceBiz(ProductLinePartDeviceRepository repo) {
        super(repo);
    }
}
