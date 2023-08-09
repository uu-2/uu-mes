package cn.yoyo.components.biz.infra.repository;

import cn.hutool.core.collection.ListUtil;
import cn.yoyo.components.biz.convert.ConvertTemplate;
import cn.yoyo.components.biz.infra.exception.RepoException;
import cn.yoyo.components.biz.tansfer.Page;
import com.mybatisflex.core.BaseMapper;
import cn.yoyo.components.biz.CrudBaseRepo;
import cn.yoyo.components.syslog.annotation.Log;
import cn.yoyo.components.syslog.enums.BizType;
import com.mybatisflex.core.constant.SqlOperator;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CrudBaseRepoMybatisImpl<T, E> implements CrudBaseRepo<E> {
    private final BaseMapper<T> mapper;
    private final ConvertTemplate<E, T> converter;

    protected CrudBaseRepoMybatisImpl(BaseMapper<T> mapper, ConvertTemplate<E, T> converter) {
        this.mapper = mapper;
        this.converter = converter;
    }

    @Override
    @Log( bizType = BizType.INSERT)
    public E save(E entity) {
        return this.mapper.insert(converter.e2t(entity)) > 0 ? entity : null;
    }

    @Override
    @Log(bizType = BizType.INSERT)
    @Transactional
    public List<E> save(List<E> entities, int ...batchSize) {
        int bs = 1000;
        if (batchSize.length == 0) {
            bs = batchSize[0];
        }

        for(List<E> es :ListUtil.partition(entities, bs)) {
            int result = this.mapper.insertBatch(converter.e2t(es));
            if (result != es.size()) {
                throw new RepoException("批量插入失败");
            }

        }
        return entities;
    }


    @Override
    @Log(bizType = BizType.UPDATE)
    public E update(E entity) {
        return this.mapper.update(converter.e2t(entity)) > 0 ? entity : null;
    }

    @Override
    @Log(bizType = BizType.DELETE)
    public boolean remove(List<? extends Serializable> ids) {
        return this.mapper.deleteBatchByIds(ids) >= 0;
    }

    @Override
    public E findById(Serializable id) {
        T d = this.mapper.selectOneById(id);
        return d == null ? null : converter.t2e(d);
    }

    @Override
    public Page<E> findByIds(List<? extends Serializable> ids) {
        List<T> list = this.mapper.selectListByIds(ids);
        List<E> data = converter.t2e(list);
        return Page.<E>builder()
                .pageNum(1)
                .pageSize(data.size())
                .totalPage(1)
                .totalRow(data.size())
                .list(data).build();
    }

    @Override
    public Page<E> findPage(int pageNumber, int pageSize, Map<String, Object> condition, Map<String, String>... operators) {
        QueryWrapper wrapper = new QueryWrapper();
        if (null!=condition){
            if (operators.length > 0) {
                HashMap<String, SqlOperator> sqlOpt = new HashMap<>();
                for (Map<String, String> operator : operators) {
                    for (String key : operator.keySet()) {
                        sqlOpt.put(key, SqlOperator.valueOf(operator.get(key)));
                    }
                }
                wrapper.and(condition, sqlOpt);
            }else {
                wrapper.and(condition);
            }
        }

        com.mybatisflex.core.paginate.Page<T> page = this.mapper.paginate(pageNumber, pageSize, wrapper);
        return Page.<E>builder()
                .pageSize(page.getPageSize())
                .pageNum(page.getPageNumber())
                .totalPage(page.getTotalPage())
                .totalRow(page.getTotalRow())
                .list(converter.t2e(page.getRecords())).build();
    }
}
