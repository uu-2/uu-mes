package cn.yoyo.facade.admin.endpoints.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.tags.Tag;
import cn.yoyo.components.biz.CrudBaseApi;
import cn.yoyo.module.sys.domain.entity.SysConfig;
import cn.yoyo.module.sys.domain.SysConfigBiz;

/**
 * SysConfig 业务服务。
 *
 * @author three3q
 * @since 2023-08-07
 */
@RestController
@RequestMapping("/sys/config")
@Tag(name = "参数配置表")
public class SysConfigApi extends CrudBaseApi<SysConfig, Long, SysConfigBiz> {

    public SysConfigApi(SysConfigBiz crudBiz) {
        super(crudBiz);
    }
}
