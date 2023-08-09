package cn.yoyo.module.sys.infra.repository.mybatis.dto;

import cn.yoyo.components.biz.convert.ConvertTemplate;
import cn.yoyo.module.sys.domain.entity.OperLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TOperLogConvert extends ConvertTemplate<OperLog, TOperLog> {
}
