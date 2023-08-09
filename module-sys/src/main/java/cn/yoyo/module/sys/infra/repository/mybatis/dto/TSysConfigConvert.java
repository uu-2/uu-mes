package cn.yoyo.module.sys.infra.repository.mybatis.dto;

import cn.yoyo.components.biz.convert.ConvertTemplate;
import org.mapstruct.Mapper;
import cn.yoyo.module.sys.domain.entity.SysConfig;

/**
 * SysConfig <=> TSysConfig 转化工具。
 *
 * @author three3q
 * @since 2023-08-07
 */
@Mapper(componentModel = "spring")
public interface TSysConfigConvert extends ConvertTemplate<SysConfig, TSysConfig> {
}
