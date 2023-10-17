package cn.yoyo.module.aps.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 产线信息表 实体类。
 *
 * @author three3q
 * @since 2023-08-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductLine {
    /**
     * 产线ID
     */
    private Long id;
    /**
     * 产线名称
     */
    private String lineName;
    /**
     * 产线编码
     */
    private String lineCode;
    /**
     * 产线类型
     */
    private String lineType;
    /**
     * 产线状态
     */
    private String lineStatus;
    /**
     * 产线描述
     */
    private String lineDesc;
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
