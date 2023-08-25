package cn.yoyo.module.sys.domain;

import cn.yoyo.components.biz.CrudBaseBiz;
import cn.yoyo.module.sys.domain.entity.OperLog;
import cn.yoyo.module.sys.domain.points.OperLogRepository;
import org.springframework.stereotype.Component;

@Component
public class OperLogBiz extends CrudBaseBiz<OperLog, Long> {

    public OperLogBiz(OperLogRepository repo) {
        super(repo);
    }

}
