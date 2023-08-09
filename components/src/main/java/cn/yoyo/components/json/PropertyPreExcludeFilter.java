package cn.yoyo.components.json;

import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;

import java.util.Collection;

/**
 * 排除JSON敏感属性
 *
 * @author yoyo
 */
public class PropertyPreExcludeFilter extends SimplePropertyPreFilter
{
    public PropertyPreExcludeFilter()
    {
    }

    public PropertyPreExcludeFilter addExcludes(Collection<String> filters)
    {
        filters.forEach((filter) -> {
            this.getExcludes().add(filter);
        });
        return this;
    }
}
