package cn.yoyo.module.demo.domain.entity;

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
 * 系统操作日志记录表 实体类。
 *
 * @author three3q
 * @since 2023-08-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperLog{
    /**
     * 日志主键
     */
    private Long id;
    /**
     * 操作模块
     */
    private String title;
    /**
     * 业务类型 "0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据"
     */
    private Integer businessType;
    /**
     * 业务类型数组
     */
    private String businessTypes;
    /**
     * 请求方法
     */
    private String executeHandler;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 用户类别  "0=其它,1=后台用户,2=手机端用户"
     */
    private Integer userType;
    /**
     * 操作人员
     */
    private String userId;
    /**
     * 操作人员名称
     */
    private String userName;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 请求url
     */
    private String operUrl;
    /**
     * 操作地址
     */
    private String operIp;
    /**
     * 操作地点
     */
    private String operLocation;
    /**
     * 请求参数
     */
    private String operParam;
    /**
     * 返回参数
     */
    private String jsonResult;
    /**
     * 操作状态（0正常 1异常）
     */
    private Integer status;
    /**
     * 错误消息
     */
    private String errorMsg;
    /**
     * 操作时间
     */
    private LocalDateTime operTime;
    /**
     * 消耗时间
     */
    private Long costTime;
    /**
     * API状态
     */
    private Integer apiStatus;
    /**
     * API地址
     */
    private String apiUrl;
    /**
     * API方法
     */
    private String apiMethod;
    /**
     * API消耗时间
     */
    private Long apiCostTime;

}
