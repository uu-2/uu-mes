package cn.yoyo.components.biz;

import cn.yoyo.components.biz.tansfer.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class CrudBaseBiz<E> {

    private final CrudBaseRepo<E> repo;
    protected CrudBaseBiz(CrudBaseRepo<E> repo) {
        this.repo = repo;
    }
    public boolean remove(List<? extends Serializable> ids) {
        return this.repo.remove(ids);
    }

    public Page<E> list(int pageNumber, int pageSize, Map<String, Object> condition, Map<String, String>... operators) {
        return this.repo.findPage(pageNumber, pageSize, condition, operators);
    }

    public E detail(Long id) {
        return this.repo.findById(id);
    }

    public E add(E entity) {
        return this.repo.save(entity);
    }

    public E edit(E entity) {
        return this.repo.update(entity);
    }
}
