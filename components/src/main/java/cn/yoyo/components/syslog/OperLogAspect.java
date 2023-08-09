package cn.yoyo.components.syslog;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.yoyo.components.json.PropertyPreExcludeFilter;
import cn.yoyo.components.syslog.annotation.Log;
import cn.yoyo.components.syslog.annotation.LogIgnore;
import cn.yoyo.components.syslog.enums.BizStatus;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 操作日志切面处理类
 *
 * @author yoyo
 */
@Aspect
@Component
@Slf4j
public class OperLogAspect {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    /**
     * 排除敏感属性字段
     */
    public static final String[] EXCLUDE_PROPERTIES = {"password", "oldPassword", "newPassword", "confirmPassword"};

    /**
     * 计算操作消耗时间
     */
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<>("Cost Time");

    /**
     * 处理请求前执行
     */
    @Before(value = "@annotation(sysLog)")
    public void boBefore(JoinPoint joinPoint, Log sysLog) {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }


    @AfterThrowing(throwing = "e", value = "@annotation(sysLog)")
    public void doAfterThrowing(JoinPoint joinPoint, Log sysLog, Exception e) { //必须统一返回值，否则拿不到返回的数据
        handleLog(joinPoint, sysLog, e, null);
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 方法的执行点
     * @param result    方法返回值
     */
    @AfterReturning(returning = "result", value = "@annotation(sysLog)")
    public void doAfterReturning(JoinPoint joinPoint, Log sysLog, Object result) { //必须统一返回值，否则拿不到返回的数据
        if (null != joinPoint.getTarget().getClass().getAnnotation(LogIgnore.class)) {
            return;
        }

        handleLog(joinPoint, sysLog, null, result);
    }

    protected void handleLog(final JoinPoint joinPoint, Log sysLog, final Exception e, Object jsonResult) {
        try {

            // *========数据库日志=========*//
            OperLogEvent operLog = new OperLogEvent();
            operLog.setStatus(BizStatus.SUCCESS.ordinal());

            // 获取RequestAttributes
            OperLogEvent.fillLogUser(operLog);

            if (e != null) {
                operLog.setStatus(BizStatus.FAIL.ordinal());
                operLog.setErrorMsg(StrUtil.sub(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setExecuteHandler(className + "." + methodName + "()");

            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, sysLog, operLog, jsonResult);
            // 设置消耗时间
            operLog.setOperTime(DateTime.of(TIME_THREADLOCAL.get()));
            operLog.setCostTime(System.currentTimeMillis() - TIME_THREADLOCAL.get());
            // 保存数据库
            eventPublisher.publishEvent(operLog);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        } finally {
            TIME_THREADLOCAL.remove();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log     日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, OperLogEvent operLog, Object jsonResult) throws Exception {
        // 设置action动作
        operLog.setBusinessType(log.bizType().ordinal());
        // 设置标题
        if (StrUtil.isNotEmpty(log.module())) {
            operLog.setTitle(log.module());
        } else {
            Log logModule = joinPoint.getTarget().getClass().getAnnotation(Log.class);
            if (logModule != null) {
                operLog.setTitle(logModule.module());
            }
        }

        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, operLog, log.excludeParamNames());
        }
        // 是否需要保存response，参数和值
        if (log.isSaveResponseData() && jsonResult != null) {
            operLog.setJsonResult(StrUtil.sub(JSONUtil.toJsonStr(jsonResult), 0, 2000));
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(JoinPoint joinPoint, OperLogEvent operLog, String[] excludeParamNames) throws Exception {
        String params = argsArrayToString(joinPoint.getArgs(), excludeParamNames);
        operLog.setOperParam(StrUtil.sub(params, 0, 2000));
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray, String[] excludeParamNames) {
        PropertyPreExcludeFilter epf = excludePropertyPreFilter(excludeParamNames);
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                if (null != o && !isFilterObject(o)) {
                    try {
                        String jsonObj = JSON.toJSONString(o, epf);
                        params += jsonObj.toString() + " ";
                    } catch (Exception e) {
                    }
                }
            }
        }
        return params.trim();
    }

    /**
     * 忽略敏感属性
     */
    public PropertyPreExcludeFilter excludePropertyPreFilter(String[] excludeParamNames) {
        Collection<String> l = Stream.of(EXCLUDE_PROPERTIES).collect(Collectors.toList());
        CollUtil.addAll(l, excludeParamNames);
        return new PropertyPreExcludeFilter().addExcludes(l);
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }

}
