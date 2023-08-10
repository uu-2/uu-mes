package cn.yoyo.module.sys.infra.repository.mybatis.dto;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

@Data
@Table("t_dict")
public class TDict {
    @Id(keyType = KeyType.Auto)
    private Long id;
    private String userName;
    private Integer age;
}
