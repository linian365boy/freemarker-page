package com.lm.base.component;

import java.io.Serializable;

/**
 * 分页信息.<br>
 * 需要传输到Service端，因此实现Serializable接口.<br>
 * 本类主要存储总记录数，分页数目以及当前分页开始索引.<br>
 * 
 * @author jnbzwm
 *
 */
public class Pager implements Serializable {
    private static final long serialVersionUID = -3268681370879473308L;

    public static final String PAGERKEY = "PAGERKEY";
    public static final String TOTALSIZE = "TOTALSIZE";
    public static final String PAGERVALUE = "PAGERVALUE";

    public static final String PAGESIZE = "PAGESIZE";

    /**
     * 总记录数.
     */
    protected int totalSize;
    /**
     * 分页大小数目.
     */
    protected int pageSize = 10;

    /**
     * 当前分页开始索引.
     */
    protected int startIndex;

    private int currentPageRows;

    /**
     * 取得总记录数目.
     * @return 总记录数目.
     */
    public int getTotalSize() {
        return totalSize;
    }

    /**
     * 设置总记录数目.
     * @param totalSize
     */
    public void setTotalSize(int totalSize) {
        if (this.totalSize == 0) {
            this.totalSize = totalSize;
        }
    }

    /**
     * 重置分页信息.
     * 主要是在重新查询时使用.
     * 重置总记录数目和当前分页开始索引.
     */
    public void reset() {
        setStartIndex(0);
        setTotalSize(0);
    }

    /**
     * 取得分页大小数目.
     * @return 分页大小数目.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置分页大小数目.
     * @param pageSize 分页大小数目.
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize < 0 ? 0 : pageSize;
    }

    /**
     * 取得当前分页开始索引.
     * @return 当前分页开始索引.
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * 设置当前分页开始索引(0-based).
     * @param startIndex 当前分页开始索引.
     */
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * 获得当前分页从第N条记录开始的N(1-based).<br>
     * 当总数量为0时，返回0.<br>
     * @return 从第N条记录开始的N
     */
    public int getFromIndex() {
        return totalSize > 0 ? startIndex + 1 : 0;
    }

    /**
     * 获得当前分页到第N条记录结束的N. <br>
     * @return 到第N条记录结束的N
     */
    public int getToIndex() {
        return startIndex + pageSize > totalSize ? totalSize : startIndex + pageSize;
    }

    /**
     * 获取总页数 公式： UP(A/B) = int( (A+B-1)/B ) 证明：
     * 
     * 上取整用UP表示
     * 
     * 由于A>0、B>0，且A、B都是整数，所以可以设A=NB+M
     * 
     * 其中N为非负整数，M为0到B-1的数，则 A/B = N + M/B (A+B-1)/B = N + 1 + (M - 1)/B;
     * 
     * 当M为0时， UP(A/B) = N， int((A+B-1)/B) = N + int(1 - 1/B) = N
     * 
     * 当M为1到B-1的数时，0 <= M-1 <= B-2 UP(A/B) = N + 1， int((A+B-1)/B) = N + 1 +
     * int((M-1)/B) = N + 1
     * 
     * 所以对A>0、B>0的整数A、B都有： UP(A/B) = int((A+B-1)/B)
     * 
     * 其他算法1: (int)Math.ceil((double)a/(double)b); 其他算法2: if (a % b == 0) {
     * return a / b; } else { return (a / b) + 1; }
     * 
     * @return
     */
    public int getTotalPages() {
        int totalPages = getPageSize() <= 0 ? 1 : (getTotalSize() + getPageSize() - 1) / getPageSize();
        return totalPages < 1 ? 1 : totalPages;
    }

    /**
     * 取得当前第几页.
     * 
     * @return 当前第几页.
     */
    public int getCurrentPage() {
        return getPageSize() <= 0 ? 1 : (getStartIndex() + getPageSize()) / getPageSize();
    }

    public void setCurrentPageRows(int currentPageRows) {
        this.currentPageRows = currentPageRows;
    }

    public int getCurrentPageRows() {
        return currentPageRows;
    }
}
