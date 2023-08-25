package cn.yoyo.module.aps.infra.repository.mybatis.dto;

import cn.yoyo.components.biz.convert.ConvertTemplate;
import org.mapstruct.Mapper;
import cn.yoyo.module.aps.domain.entity.ProductLinePartDevice;

/**
 * ProductLinePartDevice <=> TProductLinePartDevice 转化工具。
 *
 * @author three3q
 * @since 2023-08-25
 */
@Mapper(componentModel = "spring")
public interface TProductLinePartDeviceConvert extends ConvertTemplate<ProductLinePartDevice, TProductLinePartDevice> {
}
