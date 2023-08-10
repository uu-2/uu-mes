package cn.yoyo.components.context;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Set;

public class ContextInterceptor implements HandlerInterceptor {

    static final String TRACE_ID_KEY = "traceId";
    static final Set<String> TRACE_ID_HEADER = Set.of(
            "X-Trace-Id",
            "x-trace-id",
            "Trace-Id",
            "trace-id",
            "X-Request-Id",
            "x-request-id",
            "Request-Id",
            "request-id"
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String traceId = IdUtil.fastUUID();

        //从header中获取traceId
        for (String header : TRACE_ID_HEADER) {
            String headerTraceId = request.getHeader(header);
            if (StrUtil.isNotBlank(headerTraceId)) {
                traceId = headerTraceId;
                break;
            }
        }

        //MDC记录traceId
        MDC.put(TRACE_ID_KEY, traceId);

        //同时给TransmittableThreadLocal记录traceId
        ContextManager.put(TRACE_ID_KEY, traceId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) {

        //清除数据
        MDC.clear();
        ContextManager.remove();
    }
}
