package cn.yoyo.module.sys.infra.repository.mybatis.dto;

import cn.yoyo.components.biz.convert.ConvertTemplate;
import cn.yoyo.module.sys.domain.entity.Dict;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TDictConvert extends ConvertTemplate<Dict, TDict> {
}
