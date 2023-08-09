package cn.yoyo.components.biz.adapter.rest;

import cn.hutool.core.text.StrFormatter;
import cn.yoyo.components.biz.tansfer.ResultCode;
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
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, fieldErrors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultVO<?> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String pName = e.getPropertyName();
        String errMsg = StrFormatter.format("argument {} error: {}", pName, e.getMessage());
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, errMsg);
    }

    // 使用form data方式调用接口，校验异常抛出 BindException
    @ExceptionHandler(BindException.class)
    public ResultVO<?> BindException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
//        List<String> collect = fieldErrors.stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.toList());
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, fieldErrors);
    }

    //单个参数校验异常抛出
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVO<?> ConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, errors);
    }


    /**
     * 系统异常 预期以外异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<?> handleUnexpectedServer(Exception ex) {
        log.error("系统异常：", ex);
        return new ResultVO<>(ResultCode.ERROR, ex.getMessage());
    }

    /**
     * 所以异常的拦截
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<?> exception(Throwable ex) {
        log.error("系统异常：", ex);
        return new ResultVO<>(ResultCode.ERROR, ex.getMessage());
    }
}
