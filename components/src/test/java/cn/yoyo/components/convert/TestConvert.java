package cn.yoyo.components.convert;

import cn.yoyo.components.biz.convert.ConvertTemplate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestConvert extends ConvertTemplate<E, T> {
}
