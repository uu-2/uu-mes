package cn.yoyo.module.sys.infra.repository.mybatis.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_oper_log")
public class TOperLog {
    /**
     * 日志主键
     */
    @TableId(type = IdType.AUTO)
    private Long Id;

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
    private Integer[] businessTypes;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operTime;

    /**
     * 消耗时间
     */
    private Long costTime;

    /*******************************************************/
    private Integer apiStatus;
    private String apiUrl;
    private String apiMethod;
    private Long apiCostTime;
}
