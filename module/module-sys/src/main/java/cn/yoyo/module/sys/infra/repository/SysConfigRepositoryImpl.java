package cn.yoyo.module.sys.infra.repository;

import org.springframework.stereotype.Component;
import cn.yoyo.components.biz.infra.repository.CrudBaseRepoMybatisImpl;
import cn.yoyo.module.sys.domain.entity.SysConfig;
import cn.yoyo.module.sys.domain.points.SysConfigRepository;
import cn.yoyo.module.sys.infra.repository.mybatis.dto.TSysConfig;
import cn.yoyo.module.sys.infra.repository.mybatis.dto.TSysConfigConvert;
import cn.yoyo.module.sys.infra.repository.mybatis.mapper.TSysConfigMapper;

/**
 * SysConfig 存储层实现。
 *
 * @author three3q
 * @since 2023-08-07
 */
@Component
public class SysConfigRepositoryImpl extends CrudBaseRepoMybatisImpl<TSysConfig,SysConfig>
    implements SysConfigRepository {

    protected SysConfigRepositoryImpl(TSysConfigMapper mapper, TSysConfigConvert converter) {
        super(mapper, converter);
    }
}
