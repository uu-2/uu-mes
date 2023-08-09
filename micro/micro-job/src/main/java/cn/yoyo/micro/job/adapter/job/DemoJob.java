package cn.yoyo.micro.job.adapter.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DemoJob {
    @XxlJob("emotionEmotionCal")
    public ReturnT<String> emotionEmotionCal() throws Exception {
        log.debug("XXL-JOB, taskCal Start.");
        // 执行参数获取
        String param = XxlJobHelper.getJobParam();
        log.info("XXL-JOB, param:{}", param);
        XxlJobHelper.log("XXL-JOB, Hello World.");
        log.debug("XXL-JOB, taskCal End.");
        return ReturnT.SUCCESS;
    }
}
