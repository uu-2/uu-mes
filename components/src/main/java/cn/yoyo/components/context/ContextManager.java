package cn.yoyo.components.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

public class ContextManager {
    /**
     * 实现 TransmittableThreadLocal 的 initialValue,beforeExecute,afterExecute接口
     */
    final static TransmittableThreadLocal<Map<String, Object>> _ctx = new TransmittableThreadLocal<>() {
        /**
         * 在多线程数据传递的时候，将数据复制一份给MDC
         */
        @Override
        protected void beforeExecute() {
            final Map<String, Object> mdc = get();
            mdc.forEach((k, v) -> {
                MDC.put(k, v.toString());
            });
        }

        @Override
        protected void afterExecute() {
            MDC.clear();
        }

        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    /**
     * 获取 上下文 的数据
     */
    public static Object get(String key) {
        return _ctx.get().get(key);
    }

    public static Object put(String key, Object value) {
        return _ctx.get().put(key, value);
    }

    public static void remove() {
        _ctx.get().clear();
        _ctx.remove();
    }
}
