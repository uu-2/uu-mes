package cn.yoyo.components.executor;

import org.dromara.dynamictp.core.support.ThreadPoolBuilder;
import org.dromara.dynamictp.core.support.task.wrapper.MdcTaskWrapper;
import org.dromara.dynamictp.core.support.task.wrapper.TtlTaskWrapper;

import java.util.concurrent.Executor;

public abstract class Executors {
    public static Executor newCachedThreadPool(int nThreads) {
        return ThreadPoolBuilder.newBuilder()
                .threadPoolName("TpExecutor")
                .maximumPoolSize(nThreads)
                .taskWrapper(new MdcTaskWrapper())
                .taskWrapper(new TtlTaskWrapper())
                .buildDynamic();
    }
}
