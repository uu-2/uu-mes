package cn.yoyo.module.aps.infra.repository.mybatis.dto;

import cn.yoyo.components.biz.convert.ConvertTemplate;
import org.mapstruct.Mapper;
import cn.yoyo.module.aps.domain.entity.Device;

/**
 * Device <=> TDevice 转化工具。
 *
 * @author three3q
 * @since 2023-08-25
 */
@Mapper(componentModel = "spring")
public interface TDeviceConvert extends ConvertTemplate<Device, TDevice> {
}
