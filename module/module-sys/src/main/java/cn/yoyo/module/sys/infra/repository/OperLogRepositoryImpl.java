package cn.yoyo.module.sys.infra.repository;

import cn.yoyo.module.sys.domain.points.OperLogRepository;
import cn.yoyo.module.sys.infra.repository.mybatis.dto.TOperLog;
import cn.yoyo.module.sys.infra.repository.mybatis.dto.TOperLogConvert;
import cn.yoyo.module.sys.infra.repository.mybatis.mapper.OperLogMapper;
import cn.yoyo.module.sys.domain.entity.OperLog;
import cn.yoyo.components.biz.infra.repository.CrudBaseRepoMybatisImpl;
import cn.yoyo.components.syslog.annotation.LogIgnore;
import org.springframework.stereotype.Component;

@Component
@LogIgnore
public class OperLogRepositoryImpl extends CrudBaseRepoMybatisImpl<TOperLog, OperLog> implements OperLogRepository {

    public OperLogRepositoryImpl(OperLogMapper mapper, TOperLogConvert converter) {
        super(mapper, converter);
    }
}
