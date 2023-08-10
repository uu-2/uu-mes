package cn.yoyo.module.demo.domain;

import org.springframework.stereotype.Component;
import cn.yoyo.components.biz.CrudBaseBiz;
import cn.yoyo.module.demo.domain.entity.OperLog;
import cn.yoyo.module.demo.domain.points.OperLogRepository;

/**
 * OperLog 业务服务。
 *
 * @author three3q
 * @since 2023-08-07
 */
@Component
public class OperLogBiz extends CrudBaseBiz<OperLog> {

    public OperLogBiz(OperLogRepository repo) {
        super(repo);
    }
}
