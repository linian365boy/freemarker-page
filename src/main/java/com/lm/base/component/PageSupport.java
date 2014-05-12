package com.lm.base.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lm.base.CallService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 分页类
 * @author jnbzwm
 */
@SuppressWarnings("unchecked")
public abstract class PageSupport extends ActionSupport {
    private static final long serialVersionUID = 3147031770691719324L;

    /** 分页计算类 */
    private WebPager webPager = new WebPager();

    /** prev/next/指定页 */
    private String action = null;

    /** 当前页码 */
    private int currentPage = 1;

    /** 总页码数 */
    private int totalPages;

    /** 总记录数 */
    private int totalSize;

    public List pageCall(String module, String target, Map dataMap) throws Exception {
        if (dataMap == null) {
            dataMap = new HashMap();
        }
        // 加入分页数据区间
        dataMap.put(Pager.PAGERKEY, getPager());
        Map ret = CallService.process(target, dataMap);

        // 设定totalSize
        setTotalSize(convertToInt(ret.get(Pager.TOTALSIZE)));
        webPager.getPageInfo().setTotalSize(convertToInt(ret.get(Pager.TOTALSIZE)));

        // 设定当前页码
        setCurrentPage(webPager.getPageInfo().getCurrentPage());
        // 设定totalPages
        totalPages = webPager.getPageInfo().getTotalPages();

        return (List) ret.get(Pager.PAGERVALUE);
    }

    private int convertToInt(Object o) {
        if (o == null) {
            return 0;
        }
        try {
            return Integer.parseInt(o.toString());
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }

    private Pager getPager() {
        // 设定计算分页用参数:总记录数
        webPager.getPageInfo().setTotalSize(totalSize);

        // 设定计算分页用参数:当前页码
        webPager.setCurrentPage(currentPage);

        // 计算分页
        webPager.gotoPage(action);
        return webPager.getPageInfo();
    }

    public WebPager getWebPager() {
        return webPager;
    }

    public void setWebPager(WebPager webPager) {
        this.webPager = webPager;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalSize(int totalSize) {
        if (this.totalSize == 0) {
            this.totalSize = totalSize;
        }
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setPageSize(int size) {
        if (size > 0) {
            webPager.getPageInfo().setPageSize(size);
        }
    }
}
