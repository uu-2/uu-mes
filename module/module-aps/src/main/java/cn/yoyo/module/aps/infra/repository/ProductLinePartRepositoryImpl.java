package cn.yoyo.module.aps.infra.repository;

import org.springframework.stereotype.Component;
import cn.yoyo.components.biz.infra.repository.CrudBaseRepoMybatisImpl;
import cn.yoyo.module.aps.domain.entity.ProductLinePart;
import cn.yoyo.module.aps.domain.points.ProductLinePartRepository;
import cn.yoyo.module.aps.infra.repository.mybatis.dto.TProductLinePart;
import cn.yoyo.module.aps.infra.repository.mybatis.dto.TProductLinePartConvert;
import cn.yoyo.module.aps.infra.repository.mybatis.mapper.TProductLinePartMapper;

/**
 * ProductLinePart 存储层实现。
 *
 * @author three3q
 * @since 2023-08-25
 */
@Component
public class ProductLinePartRepositoryImpl extends CrudBaseRepoMybatisImpl<TProductLinePart,ProductLinePart>
    implements ProductLinePartRepository {

    protected ProductLinePartRepositoryImpl(TProductLinePartMapper mapper, TProductLinePartConvert converter) {
        super(mapper, converter);
    }
}
