package cn.yoyo.components.biz.infra.repository;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// @Aspect
// @Component
@Slf4j
public class DynamicDataSourceAspect {
    // TODO: 2023-10-17 [Henry.Yu] mybatis-plus 数据源动态切换
    /*@Pointcut("execution(* *..mybatis.mapper..*(..))")
    public void dbOperatorMethods() {
    }

    *//**
     * 处理请求前执行
     *//*
    @Around("dbOperatorMethods()")
    public Object dbAround(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean needClearDsKey = false;
        try {
            log.info("Before method: " + joinPoint.getSignature().getName());

            // 获取代理类
            Object proxy = joinPoint.getTarget();

            // 没有通过 UseDataSource 注解指定数据源，使用默认数据源
            if (StrUtil.isEmpty(getConfigDataSourceKey(proxy))) {
                //TODO 实现根据上下问数据源动态切换逻辑
                needClearDsKey = true;
            }

            Object result = joinPoint.proceed();
            log.info("After method: " + joinPoint.getSignature().getName());
            return result;
        } catch (Exception e) {
            log.error("error", e);
            throw e.getCause();
        } finally {
            if (needClearDsKey) {
                DataSourceKey.clear();
            }
        }

    }

    private static String getConfigDataSourceKey(*//*Method method, *//*Object proxy) {
//        UseDataSource useDataSource = method.getAnnotation(UseDataSource.class);
//        if (useDataSource != null && StringUtil.isNotBlank(useDataSource.value())) {
//            return useDataSource.value();
//        }

        Class<?>[] interfaces = proxy.getClass().getInterfaces();
        for (Class<?> anInterface : interfaces) {
            UseDataSource annotation = anInterface.getAnnotation(UseDataSource.class);
            if (annotation != null) {
                return annotation.value();
            }
        }

        if (interfaces[0] != RowMapper.class) {
            TableInfo tableInfo = TableInfoFactory.ofMapperClass(interfaces[0]);
            if (tableInfo != null) {
                String dataSourceKey = tableInfo.getDataSource();
                if (StringUtil.isNotBlank(dataSourceKey)) {
                    return dataSourceKey;
                }
            }
        }
        return null;
    }*/

}
