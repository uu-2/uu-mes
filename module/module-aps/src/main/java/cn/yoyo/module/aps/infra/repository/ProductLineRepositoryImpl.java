package cn.yoyo.module.aps.infra.repository;

import org.springframework.stereotype.Component;
import cn.yoyo.components.biz.infra.repository.CrudBaseRepoMybatisImpl;
import cn.yoyo.module.aps.domain.entity.ProductLine;
import cn.yoyo.module.aps.domain.points.ProductLineRepository;
import cn.yoyo.module.aps.infra.repository.mybatis.dto.TProductLine;
import cn.yoyo.module.aps.infra.repository.mybatis.dto.TProductLineConvert;
import cn.yoyo.module.aps.infra.repository.mybatis.mapper.TProductLineMapper;

/**
 * ProductLine 存储层实现。
 *
 * @author three3q
 * @date 2023-08-25
 */
@Component
public class ProductLineRepositoryImpl extends CrudBaseRepoMybatisImpl<TProductLine,ProductLine>
    implements ProductLineRepository {

    protected ProductLineRepositoryImpl(TProductLineMapper mapper, TProductLineConvert converter) {
        super(mapper, converter);
    }
}
