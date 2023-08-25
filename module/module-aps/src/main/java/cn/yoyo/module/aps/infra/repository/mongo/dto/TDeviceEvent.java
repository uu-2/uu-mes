package cn.yoyo.module.aps.infra.repository.mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("t_device_event")
public class TDeviceEvent {
    @MongoId(targetType = FieldType.INT32)
    @Field("_id")
    private String id;
    private Long deviceId;
    private Long deviceStatus;
    private String eventType;
    private Long eventTime;
    private Long receiveTime;
    private Map<String, Object> eventContent;
}
