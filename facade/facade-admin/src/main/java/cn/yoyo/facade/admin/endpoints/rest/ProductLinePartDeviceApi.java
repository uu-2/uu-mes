package cn.yoyo.facade.admin.endpoints.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.tags.Tag;
import cn.yoyo.components.biz.CrudBaseApi;
import cn.yoyo.module.aps.domain.entity.ProductLinePartDevice;
import cn.yoyo.module.aps.domain.ProductLinePartDeviceBiz;

/**
 * ProductLinePartDevice 业务服务。
 *
 * @author three3q
 * @since 2023-08-25
 */
@RestController
@RequestMapping("/api/aps/product-line-part-device")
@Tag(name = "产线部件设备信息表")
public class ProductLinePartDeviceApi extends CrudBaseApi<ProductLinePartDevice, ProductLinePartDeviceBiz> {

    public ProductLinePartDeviceApi(ProductLinePartDeviceBiz crudBiz) {
        super(crudBiz);
    }
}
