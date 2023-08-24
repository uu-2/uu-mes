package cn.yoyo.module.aps.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceEvent {
    private String id;
    private Long deviceId;
    private String eventType;
    private Long eventTime;
    private Long receiveTime;
    private Map<String, Object> eventContent;
}
