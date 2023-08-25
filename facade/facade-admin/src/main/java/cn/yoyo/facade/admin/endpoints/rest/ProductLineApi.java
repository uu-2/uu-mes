package cn.yoyo.facade.admin.endpoints.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.tags.Tag;
import cn.yoyo.components.biz.CrudBaseApi;
import cn.yoyo.module.aps.domain.entity.ProductLine;
import cn.yoyo.module.aps.domain.ProductLineBiz;

/**
 * ProductLine 业务服务。
 *
 * @author three3q
 * @since 2023-08-25
 */
@RestController
@RequestMapping("/api/aps/product-line")
@Tag(name = "产线信息表")
public class ProductLineApi extends CrudBaseApi<ProductLine, ProductLineBiz> {

    public ProductLineApi(ProductLineBiz crudBiz) {
        super(crudBiz);
    }
}
