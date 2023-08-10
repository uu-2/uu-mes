package cn.yoyo.components.biz.adapter.rest;

import cn.dev33.satoken.exception.*;
import cn.hutool.core.text.StrFormatter;
import cn.yoyo.components.biz.tansfer.CoreResultCode;
import cn.yoyo.components.biz.tansfer.ResultVO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Set;

@RestControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionControllerAdvice {

    // 使用form data方式调用接口，校验异常抛出 BindException
    // 使用 json 请求体调用接口，校验异常抛出 MethodArgumentNotValidException
    // 单个参数校验异常抛出ConstraintViolationException

    // 处理 json 请求体调用接口校验失败抛出的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<?> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
//        List<String> collect = fieldErrors.stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.toList());
        return new ResultVO<>(CoreResultCode.VALIDATE_FAILED, fieldErrors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultVO<?> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String pName = e.getPropertyName();
        String errMsg = StrFormatter.format("argument {} error: {}", pName, e.getMessage());
        return new ResultVO<>(CoreResultCode.VALIDATE_FAILED, errMsg);
    }

    // 使用form data方式调用接口，校验异常抛出 BindException
    @ExceptionHandler(BindException.class)
    public ResultVO<?> BindException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
//        List<String> collect = fieldErrors.stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.toList());
        return new ResultVO<>(CoreResultCode.VALIDATE_FAILED, fieldErrors);
    }

    //单个参数校验异常抛出
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVO<?> ConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
        return new ResultVO<>(CoreResultCode.VALIDATE_FAILED, errors);
    }

    // 拦截：未登录异常
    @ExceptionHandler(NotLoginException.class)
    public ResultVO<?> handlerException(NotLoginException e) {

        // 打印堆栈，以供调试
        log.error("NotLoginException: 未登录", e);

        // 返回给前端
        return new ResultVO<>(CoreResultCode.ERR_NOT_LOGIN);
    }

    // 拦截：缺少权限异常
    @ExceptionHandler(NotPermissionException.class)
    public ResultVO<?> handlerException(NotPermissionException e) {
        log.error("缺少权限：: {}", e.getPermission(), e);
        return new ResultVO<>(CoreResultCode.ERR_NOT_PERM, "缺少权限：" + e.getPermission());
    }

    // 拦截：缺少角色异常
    @ExceptionHandler(NotRoleException.class)
    public ResultVO<?> handlerException(NotRoleException e) {
        log.error("缺少角色：: {}", e.getRole(), e);
        return new ResultVO<>(CoreResultCode.ERR_NOT_ROLE,"缺少角色：" + e.getRole());
    }

    // 拦截：二级认证校验失败异常
    @ExceptionHandler(NotSafeException.class)
    public ResultVO<?> handlerException(NotSafeException e) {
        log.error("二级认证校验失败：: {}", e.getService(), e);
        return new ResultVO<>(CoreResultCode.ERR_2_LEVEL_VALID,"二级认证校验失败：" + e.getService());
    }

    // 拦截：服务封禁异常
    @ExceptionHandler(DisableServiceException.class)
    public ResultVO<?> handlerException(DisableServiceException e) {
        log.error("当前账号 " + e.getService() + " 服务已被封禁 (level=" + e.getLevel() + ")：" + e.getDisableTime() + "秒后解封", e);
        return new ResultVO<>(CoreResultCode.ERR_USER_LOCK,"当前账号 " + e.getService() + " 服务已被封禁 (level=" + e.getLevel() + ")：" + e.getDisableTime() + "秒后解封");
    }

    // 拦截：Http Basic 校验失败异常
    @ExceptionHandler(NotBasicAuthException.class)
    public ResultVO<?> handlerException(NotBasicAuthException e) {
        log.error("Http Basic 校验失败",  e);
        return new ResultVO<>(CoreResultCode.ERR_HTTP_BASIC,e.getMessage());
    }


    /**
     * 系统异常 预期以外异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<?> handleUnexpectedServer(Exception ex) {
        log.error("系统异常：", ex);
        return new ResultVO<>(CoreResultCode.ERROR, ex.getMessage());
    }

    /**
     * 所以异常的拦截
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<?> exception(Throwable ex) {
        log.error("系统异常：", ex);
        return new ResultVO<>(CoreResultCode.ERROR, ex.getMessage());
    }
}
