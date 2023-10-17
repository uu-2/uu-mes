package cn.yoyo.module.aps.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 产线部件信息表 实体类。
 *
 * @author three3q
 * @since 2023-08-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductLinePart {
    /**
     * 产线部件ID
     */
    private Long id;
    /**
     * 产线ID
     */
    private Long lineId;
    /**
     * 部件名称
     */
    private String partName;
    /**
     * 部件编码
     */
    private String partCode;
    /**
     * 部件状态
     */
    private String partStatus;
    /**
     * 部件描述
     */
    private String partDesc;
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
