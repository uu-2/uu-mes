package cn.yoyo.module.sys.domain;

import org.springframework.stereotype.Component;
import cn.yoyo.components.biz.CrudBaseBiz;
import cn.yoyo.module.sys.domain.entity.SysConfig;
import cn.yoyo.module.sys.domain.points.SysConfigRepository;

/**
 * SysConfig 业务服务。
 *
 * @author three3q
 * @since 2023-08-07
 */
@Component
public class SysConfigBiz extends CrudBaseBiz<SysConfig, Long> {

    public SysConfigBiz(SysConfigRepository repo) {
        super(repo);
    }
}
