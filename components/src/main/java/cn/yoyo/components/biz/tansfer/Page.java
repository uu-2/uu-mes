package cn.yoyo.components.biz.tansfer;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Page <T> {
    static final int INIT_VALUE = -1;
    private int pageNum;
    private int pageSize;
    /**
     * 总页数。
     */
    private long totalPage = INIT_VALUE;
    /**
     * 总数据数量。
     */
    private long totalRow = INIT_VALUE;
    private List<T> list;
}
