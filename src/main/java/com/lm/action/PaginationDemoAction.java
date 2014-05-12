package com.lm.action;

import java.util.List;

import com.lm.base.component.PageSupport;
import com.lm.service.PaginationDemoService;

@SuppressWarnings("unchecked")
public class PaginationDemoAction extends PageSupport {
    private static final long serialVersionUID = 6920341759643109640L;

    /** 页面显示数据 */
    private List data = null;

    public PaginationDemoAction() {
        // 设置每页显示7条记录
        setPageSize(7);
    }

    @Override
    public String execute() throws Exception {
        data = pageCall("esf", PaginationDemoService.queryData, null);
        return SUCCESS;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
