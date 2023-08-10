package cn.yoyo.module.sys.infra.repository;

import cn.yoyo.module.sys.domain.points.DictRepository;
import cn.yoyo.module.sys.infra.repository.mybatis.dto.TDict;
import cn.yoyo.module.sys.infra.repository.mybatis.dto.TDictConvert;
import cn.yoyo.module.sys.infra.repository.mybatis.mapper.DictMapper;
import cn.yoyo.components.biz.infra.repository.CrudBaseRepoMybatisImpl;
import cn.yoyo.module.sys.domain.entity.Dict;
import cn.yoyo.components.syslog.annotation.Log;
import org.springframework.stereotype.Component;

@Component
@Log(module = "字典")
public class DictRepositoryImpl extends CrudBaseRepoMybatisImpl<TDict, Dict> implements DictRepository {

    public DictRepositoryImpl(DictMapper mapper, TDictConvert converter) {
        super(mapper, converter);
    }
}
