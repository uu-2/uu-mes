package cn.yoyo.module.aps.domain;

import org.springframework.stereotype.Component;
import cn.yoyo.components.biz.CrudBaseBiz;
import cn.yoyo.module.aps.domain.entity.ProductLinePart;
import cn.yoyo.module.aps.domain.points.ProductLinePartRepository;

/**
 * ProductLinePart 业务服务。
 *
 * @author three3q
 * @date 2023-08-25
 */
@Component
public class ProductLinePartBiz extends CrudBaseBiz<ProductLinePart, Long> {

    public ProductLinePartBiz(ProductLinePartRepository repo) {
        super(repo);
    }
}
