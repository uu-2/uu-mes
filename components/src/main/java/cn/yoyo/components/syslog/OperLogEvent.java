package cn.yoyo.components.syslog;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.yoyo.components.syslog.enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Date;

@Data
public class OperLogEvent {

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

    static void fillLogUser(OperLogEvent operLog) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            // 处理非HTTP请求情况，比如：job、mq等
            operLog.setOperIp("daemon");
            operLog.setOperUrl("daemon");
            // 设置请求方式
            operLog.setRequestMethod("daemon");
            operLog.setUserName("daemon_sys");
            operLog.setUserId("0");
            operLog.setUserName("daemon_sys");
            operLog.setUserType(UserType.SYS_DAEMON.ordinal());
        } else {
            // 从获取RequestAttributes中获取HttpServletRequest的信息
            HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            // 请求的地址
            String ip = JakartaServletUtil.getClientIP(request);
            operLog.setOperIp(ip);
            operLog.setOperUrl(StrUtil.sub(request.getRequestURI(), 0, 255));
            // 设置请求方式
            operLog.setRequestMethod(request.getMethod());
            // 获取当前的用户
//            LoginUser loginUser = SecurityUtils.getLoginUser();
//            if (loginUser != null)
//            {
//                operLog.setOperName(loginUser.getUsername());
//                operLog.setOperId(loginUser.getUserId());
//            }
        }
    }
}
