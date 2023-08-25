package cn.yoyo.module.aps.domain.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 设备信息表 实体类。
 *
 * @author three3q
 * @since 2023-08-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device{
    /**
     * 设备ID
     */
    private Long id;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备编码
     */
    private String deviceCode;
    /**
     * 设备类型
     */
    private String deviceType;
    /**
     * 设备描述
     */
    private String deviceDesc;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 备注
     */
    private String remark;

}
