package cn.yoyo.module.aps.infra.repository.mybatis.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 产线部件设备信息表 实体类。
 *
 * @author three3q
 * @since 2023-08-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_product_line_part_device", schema = "yoyo")
public class TProductLinePartDevice implements Serializable {
    /**
     * 产线部件设备ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 产线ID
     */
    private Long lineId;
    /**
     * 部件ID
     */
    private Long partId;
    /**
     * 设备ID
     */
    private Long deviceId;
    /**
     * 状态
     */
    private String status;
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
     * 删除者
     */
    private String deleteBy;
    /**
     * 删除时间
     */
    private LocalDateTime deleteTime;
    /**
     * 备注
     */
    private String remark;

}
