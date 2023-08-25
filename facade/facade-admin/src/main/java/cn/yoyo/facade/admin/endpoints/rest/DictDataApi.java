package cn.yoyo.facade.admin.endpoints.rest;

import cn.yoyo.components.biz.CrudBaseApi;
import cn.yoyo.module.sys.domain.entity.Dict;
import cn.yoyo.module.sys.domain.DictDataBiz;
import cn.yoyo.module.sdk.clients.baidu.BaiduClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/dict/data")
@Tag(name = "数据字典")
public class DictDataApi extends CrudBaseApi<Dict,Long,  DictDataBiz> {

    public DictDataApi(DictDataBiz tService) {
        super(tService);
    }

    @Autowired
    private BaiduClient baiduClient;

    @RequestMapping("/call-api")
    public String callAPI() {

        return baiduClient.index();
    }
}
