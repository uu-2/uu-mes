package cn.yoyo.components.biz.infra.repository;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.yoyo.components.biz.CrudBaseRepo;
import cn.yoyo.components.biz.convert.ConvertTemplate;
import cn.yoyo.components.biz.infra.exception.RepoException;
import cn.yoyo.components.biz.tansfer.Page;
import cn.yoyo.components.syslog.annotation.Log;
import cn.yoyo.components.syslog.enums.BizType;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.constant.SqlOperator;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public abstract class CrudBaseRepoMongoImpl<T, K, E> implements CrudBaseRepo<E> {
    private final MongoRepository<T, K> mapper;
    private final ConvertTemplate<E, T> converter;
    private final Class<T> clazzT;

    protected CrudBaseRepoMongoImpl(MongoRepository<T, K> mapper, ConvertTemplate<E, T> converter) {
        this.mapper = mapper;
        this.converter = converter;


        clazzT = (Class<T>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    @Log(bizType = BizType.INSERT)
    public E save(E entity) {
        T t = this.mapper.save(converter.e2t(entity));
        return converter.t2e(t);
    }

    @Override
    @Log(bizType = BizType.INSERT)
    @Transactional
    public List<E> save(List<E> entities, int... batchSize) {
        int bs = 1000;
        if (batchSize.length == 0) {
            bs = batchSize[0];
        }

        for (List<E> es : ListUtil.partition(entities, bs)) {
            List<T> result = this.mapper.saveAll(converter.e2t(es));
            if (result.size() != es.size()) {
                throw new RepoException("批量插入失败");
            }

        }
        return entities;
    }


    @Override
    @Log(bizType = BizType.UPDATE)
    public E update(E entity) {
        T t = this.mapper.save(converter.e2t(entity));
        return converter.t2e(t);
    }

    @Override
    @Log(bizType = BizType.DELETE)
    public boolean remove(List<? extends Serializable> ids) {
        this.mapper.deleteAllById((Iterable<? extends K>) ids);
        return true;
    }

    @Override
    public E findById(Serializable id) {
        T d = this.mapper.findById((K) id).orElse(null);
        return d == null ? null : converter.t2e(d);
    }

    @Override
    public Page<E> findByIds(List<? extends Serializable> ids) {
        List<T> list = this.mapper.findAllById((Iterable<K>) ids);
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

        ExampleMatcher matcher = ExampleMatcher.matching()//构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)//改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true);//改变默认大小写忽略方式：忽略大小写

        T t = BeanUtil.mapToBean(condition, clazzT, true, null);

        Example<T> query = Example.of(t, matcher);//构建对象
        Pageable pageable = PageRequest.of(pageNumber, pageSize);//构建分页信息


        org.springframework.data.domain.Page<T> page = this.mapper.findAll(query, pageable);//执行分页查询

        return Page.<E>builder()
                .pageSize(page.getSize())
                .pageNum(page.getNumber())
                .totalPage(page.getTotalPages())
                .totalRow(page.getTotalElements())
                .list(converter.t2e(page.toList())).build();
    }
}
