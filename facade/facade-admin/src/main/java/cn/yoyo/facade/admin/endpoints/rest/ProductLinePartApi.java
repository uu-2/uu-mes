package cn.yoyo.facade.admin.endpoints.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.tags.Tag;
import cn.yoyo.components.biz.CrudBaseApi;
import cn.yoyo.module.aps.domain.entity.ProductLinePart;
import cn.yoyo.module.aps.domain.ProductLinePartBiz;

/**
 * ProductLinePart 业务服务。
 *
 * @author three3q
 * @since 2023-08-25
 */
@RestController
@RequestMapping("/api/aps/product-line-part")
@Tag(name = "产线部件信息表")
public class ProductLinePartApi extends CrudBaseApi<ProductLinePart, Long, ProductLinePartBiz> {

    public ProductLinePartApi(ProductLinePartBiz crudBiz) {
        super(crudBiz);
    }
}
