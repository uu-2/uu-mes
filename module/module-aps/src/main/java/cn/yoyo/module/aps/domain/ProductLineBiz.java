package cn.yoyo.module.aps.domain;

import cn.yoyo.components.biz.CrudBaseBiz;
import cn.yoyo.module.aps.domain.entity.ProductLine;
import cn.yoyo.module.aps.domain.points.ProductLineRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductLineBiz extends CrudBaseBiz<ProductLine, Long> {
    protected ProductLineBiz(ProductLineRepository repo) {
        super(repo);
    }
}
