package cn.yoyo.facade.admin.adapter.listener;

import cn.yoyo.module.sys.domain.OperLogBiz;
import cn.yoyo.components.syslog.OperLogEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SysLogListener {

    private final OperLogBiz operLogBiz;
    private final OperLogEventConvert operLogEventConvert;

    public SysLogListener(OperLogBiz operLogBiz, OperLogEventConvert operLogEventConvert) {
        this.operLogBiz = operLogBiz;
        this.operLogEventConvert = operLogEventConvert;
    }

    @Async
    @EventListener(OperLogEvent.class)
    public void onSysLogEvent(OperLogEvent logEvent) {

        try {
            operLogBiz.add(operLogEventConvert.t2e(logEvent));
        } catch (Exception ex) {
            log.error("SysLogListener.onSysLogEvent error", ex);
        }

    }

}
