package com.lm.base.component;

/**
 * 分页动作类.<br>
 * 
 * @author jnbzwm
 * 
 */
public class WebPager {
    /**
     * 分页信息.
     */
    private Pager pageInfo = new Pager();

    private int currentPage = 1;

    /**
     * 默认构造函数.
     */
    public WebPager() {
    }

    /**
     * 分页动作.<br>
     * 主要为：1) first 2) prev 3) next 4)last 5) 具体页码.
     */
    public void gotoPage(String action) {
        int startIndex = getStartIndex();//pageInfo.getStartIndex();
        int totalSize = pageInfo.getTotalSize();
        int pageSize = pageInfo.getPageSize();

        if (action == null) {
            pageInfo.setStartIndex(0);
            return;
        }

        if ("first".equals(action)) { // 到首页
            startIndex = 0;
        }
        else if ("prev".equals(action)) { // 前一页
            startIndex -= pageSize;
            if (startIndex < 0) {
                startIndex = 0;
            }
        }
        else if ("next".equals(action)) { // 后一页
            startIndex += pageSize;
            if (startIndex >= totalSize) {
                startIndex -= pageSize;
            }
        }
        else if ("last".equals(action)) { // 最末页
            startIndex = gotoLast();
        }
        else if ("refresh".equals(action)) { // 刷新当前页
            // 刷新当前页时，可能是删除或者新增之后，所有需要重新计算总数
            pageInfo.setTotalSize(0);
        }
        else if (action.matches("\\d+")) {// 指定页
            int gopage = convertToInt(action);
            if (gopage <= 1) {
                startIndex = 0;
            }
            else if (gopage > totalSize / pageSize) {
                startIndex = gotoLast();
            }
            else {
                startIndex = (gopage - 1) * pageSize;
            }
        }

        pageInfo.setStartIndex(startIndex);
    }

    private int convertToInt(String o) {
        try {
            return Integer.parseInt(o);
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }

    private int getStartIndex() {
        return pageInfo.getPageSize() * (currentPage - 1);
    }

    /**
     * 跳转到最末页
     * @return
     */
    private int gotoLast() {
        int totalSize = pageInfo.getTotalSize();
        int pageSize = pageInfo.getPageSize();

        int modValue = totalSize % pageSize;
        int startIndex = modValue == 0 ? (totalSize - pageSize)
                : (pageInfo.getTotalSize() - modValue);
        return startIndex;
    }

    /**
     * 取得分页信息.
     * @return 分页信息.
     */
    public Pager getPageInfo() {
        return pageInfo;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
