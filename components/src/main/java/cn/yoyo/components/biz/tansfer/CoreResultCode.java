package cn.yoyo.components.biz.tansfer;

import lombok.Getter;

@Getter
public enum CoreResultCode implements ResultCode {
    SUCCESS(1000, "操作成功"),
    FAILED(1001, "响应失败"),
    VALIDATE_FAILED(1002, "参数校验失败"),
    ERROR(9999, "未知错误"),
    ERR_NOT_LOGIN(2000, "未登录"),
    ERR_NOT_PERM(2001, "没有权限"),
    ERR_NOT_ROLE(2002, "没有角色"),
    ERR_2_LEVEL_VALID(2003, "二级认证校验失败"),
    ERR_USER_LOCK(2004, "账号服务封禁"),
    ERR_HTTP_BASIC(2005, "Http Basic 校验失败");


    private int code;
    private String msg;

    CoreResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
