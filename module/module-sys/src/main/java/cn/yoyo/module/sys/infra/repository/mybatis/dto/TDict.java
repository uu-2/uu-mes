package cn.yoyo.module.sys.infra.repository.mybatis.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_dict")
public class TDict {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
    private Integer age;
}