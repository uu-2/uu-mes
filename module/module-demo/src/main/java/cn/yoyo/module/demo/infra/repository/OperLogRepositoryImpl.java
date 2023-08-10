package cn.yoyo.module.demo.infra.repository;

import org.springframework.stereotype.Component;
import cn.yoyo.components.biz.infra.repository.CrudBaseRepoMybatisImpl;
import cn.yoyo.module.demo.domain.entity.OperLog;
import cn.yoyo.module.demo.domain.points.OperLogRepository;
import cn.yoyo.module.demo.infra.repository.mybatis.dto.TOperLog;
import cn.yoyo.module.demo.infra.repository.mybatis.dto.TOperLogConvert;
import cn.yoyo.module.demo.infra.repository.mybatis.mapper.TOperLogMapper;

/**
 * OperLog 存储层实现。
 *
 * @author three3q
 * @since 2023-08-07
 */
@Component
public class OperLogRepositoryImpl extends CrudBaseRepoMybatisImpl<TOperLog,OperLog>
    implements OperLogRepository {

    protected OperLogRepositoryImpl(TOperLogMapper mapper, TOperLogConvert converter) {
        super(mapper, converter);
    }
}
