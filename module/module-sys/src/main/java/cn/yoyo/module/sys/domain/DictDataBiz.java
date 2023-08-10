package cn.yoyo.module.sys.domain;

import cn.yoyo.components.biz.CrudBaseBiz;
import cn.yoyo.module.sys.domain.entity.Dict;
import cn.yoyo.module.sys.domain.points.DictRepository;
import org.springframework.stereotype.Component;

@Component
public class DictDataBiz extends CrudBaseBiz<Dict> {


    public DictDataBiz(DictRepository repo) {
        super(repo);
    }

}
