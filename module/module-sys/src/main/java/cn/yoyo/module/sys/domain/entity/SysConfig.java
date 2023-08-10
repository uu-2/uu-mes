package cn.yoyo.module.sys.domain.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 参数配置表 实体类。
 *
 * @author three3q
 * @since 2023-08-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysConfig{
    /**
     * 参数主键
     */
    private Integer configId;
    /**
     * 参数名称
     */
    private String configName;
    /**
     * 参数键名
     */
    private String configKey;
    /**
     * 参数键值
     */
    private String configValue;
    /**
     * 系统内置（Y是 N否）
     */
    private String configType;
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
