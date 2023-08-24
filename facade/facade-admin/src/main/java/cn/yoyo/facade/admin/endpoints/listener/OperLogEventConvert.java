package cn.yoyo.facade.admin.endpoints.listener;

import cn.yoyo.module.sys.domain.entity.OperLog;
import cn.yoyo.components.biz.convert.ConvertTemplate;
import cn.yoyo.components.syslog.OperLogEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperLogEventConvert extends ConvertTemplate<OperLog, OperLogEvent> {
}
