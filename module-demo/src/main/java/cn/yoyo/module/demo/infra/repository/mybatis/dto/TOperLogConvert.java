package cn.yoyo.module.demo.infra.repository.mybatis.dto;

import cn.yoyo.components.biz.convert.ConvertTemplate;
import org.mapstruct.Mapper;
import cn.yoyo.module.demo.domain.entity.OperLog;

/**
 * OperLog <=> TOperLog 转化工具。
 *
 * @author three3q
 * @since 2023-08-07
 */
@Mapper(componentModel = "spring")
public interface TOperLogConvert extends ConvertTemplate<OperLog, TOperLog> {
}
