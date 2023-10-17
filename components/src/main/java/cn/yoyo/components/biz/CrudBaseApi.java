package cn.yoyo.components.biz;

import cn.yoyo.components.biz.tansfer.Page;
import cn.yoyo.components.biz.tansfer.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@RestController
public abstract class CrudBaseApi<E, K extends Serializable, Biz extends CrudBaseBiz<E, K>> {
    private final Biz crudBiz;

    /**
     * unchecked
     **/
    public CrudBaseApi(Biz crudBiz) {
        this.crudBiz = crudBiz;
    }


    @Operation(summary = "列表")
    @PostMapping(value = "/list")
    @ResponseBody
    public ResultVO<Page<E>> list() {
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("age", 18);
        HashMap<String, String> operators = new HashMap<>();
        operators.put("age", "GT");
        return new ResultVO<>(crudBiz.list(1, 2, condition, operators));
    }

    @Operation(summary = "详情")
    @RequestMapping(value = "/detail/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultVO<E> get(@PathVariable K id) {
        return new ResultVO<>(crudBiz.detail(id));
    }

    @Operation(summary = "添加")
    @PostMapping(value = "/add")
    @ResponseBody
    public ResultVO<E> add(@Validated @RequestBody E entity) {
        return new ResultVO<>(crudBiz.add(entity));
    }


    @Operation(summary = "修改")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseBody
    public ResultVO<E> edit(@Validated @RequestBody E entity) {
        return new ResultVO<>(crudBiz.edit(entity));
    }


    @Operation(summary = "删除")
    @RequestMapping(value = "/del/{id}", method = {RequestMethod.DELETE, RequestMethod.POST})
    @ResponseBody
    public ResultVO<Boolean> delete(@PathVariable K[] ids) {
        return new ResultVO<>(crudBiz.remove(List.of(ids)));
    }
}
