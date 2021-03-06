package org.truenewx.data.query;

import java.util.Map;

import org.truenewx.core.util.BeanUtil;

/**
 * 查询参数实现
 *
 * @author jianglei
 * @since JDK 1.8
 */
public class QueryParameterImpl extends MultiQueryOrder implements QueryParameter {
    /**
     * 页大小
     */
    private int pageSize;
    /**
     * 页码
     */
    private int pageNo = 1;
    /**
     * 是否计算总数
     */
    private boolean totalable = true;
    /**
     * 是否查询数据记录
     */
    private boolean listable = true;

    public QueryParameterImpl() {
    }

    public QueryParameterImpl(final int pageSize, final int pageNo) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 如果当前页大小未设定，则设定为指定页大小默认值
     *
     * @param pageSize
     *            页大小默认值
     */
    public void setPageSizeDefault(final int pageSize) {
        if (this.pageSize <= 0) {
            this.pageSize = pageSize;
        }
    }

    @Override
    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(final int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public Map<String, Object> getAll() {
        return BeanUtil.toMap(this, "pageSize", "pageNo", "totalable", "listable", "orders",
                "orderString", "orderFieldNames", "all");
    }

    public final void setPaging(final int pageSize, final int pageNo) {
        setPageSize(pageSize);
        setPageNo(pageNo);
    }

    @Override
    public boolean isTotalable() {
        return this.totalable;
    }

    public void setTotalable(final boolean totalable) {
        this.totalable = totalable;
    }

    @Override
    public boolean isListable() {
        return this.listable;
    }

    public void setListable(final boolean listable) {
        this.listable = listable;
    }

}
