package cn.yoyo.components.biz;

import cn.yoyo.components.biz.tansfer.Page;
import cn.yoyo.components.biz.tansfer.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract class CrudBaseApi<E, T extends CrudBaseBiz<E>> {
    private final T crudBiz;

    /** unchecked **/
    public CrudBaseApi(T crudBiz) {
        this.crudBiz = crudBiz;
    }


    @Operation(summary = "列表")
    @PostMapping(value = "/list")
    @ResponseBody
    public ResultVO<Page<E>> list() {
        return new ResultVO<>(crudBiz.list(1, 2, null));
    }

    @Operation(summary = "详情")
    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResultVO<E> get(@PathVariable Long id) {
        return new ResultVO<>(crudBiz.detail(id));
    }

    @Operation(summary = "添加")
    @PostMapping
    @ResponseBody
    public ResultVO<E> add(@Validated @RequestBody E entity) {
        return new ResultVO<>(crudBiz.add(entity));
    }


    @Operation(summary = "修改")
    @PutMapping
    @ResponseBody
    public ResultVO<E> edit(@Validated @RequestBody E entity) {
        return new ResultVO<>(crudBiz.edit(entity));
    }


    @Operation(summary = "删除")
    @DeleteMapping("/{ids}")
    @ResponseBody
    public ResultVO<Boolean> remove(@PathVariable Long[] ids) {
        return new ResultVO<>(crudBiz.remove(List.of(ids)));
    }
}
