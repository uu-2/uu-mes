package cn.yoyo.components.biz;


import cn.yoyo.components.biz.tansfer.Page;
import cn.yoyo.components.syslog.annotation.Log;
import cn.yoyo.components.syslog.enums.BizType;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface CrudBaseRepo<E> {
    @Log(bizType = BizType.INSERT)
    @Transactional
    E save(E entity);

    @Log(bizType = BizType.INSERT)
    @Transactional
    List<E> save(List<E> entities, int ...batchSize);

    @Log(bizType = BizType.UPDATE)
    @Transactional
    E update(E entity);

    @Log(bizType = BizType.DELETE)
    @Transactional
    boolean remove(List<? extends Serializable> ids);

    E findById(Serializable id);

    Page<E> findByIds(List<? extends Serializable> ids);

    public Page<E> findPage(int pageNumber, int pageSize, Map<String, Object> condition, Map<String, String>... operators);

}
