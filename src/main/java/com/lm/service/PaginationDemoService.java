package com.lm.service;

import java.util.HashMap;
import java.util.Map;

import com.lm.base.component.Pager;
import com.lm.db.MockDataBase;

@SuppressWarnings("unchecked")
public class PaginationDemoService {
    private static final String pkg = PaginationDemoService.class.getCanonicalName();

    public static final String queryData = pkg + "." + "queryData";

    public Map queryData(Map map) {
        Pager p = (Pager) map.get(Pager.PAGERKEY);
        int start = p.getStartIndex();
        int length = p.getPageSize();
        MockDataBase db = new MockDataBase();

        Map ret = new HashMap();

        ret.put(Pager.TOTALSIZE, db.getTotalSize());
        ret.put(Pager.PAGERVALUE, db.queryPage(start, length));
        return ret;
    }
}
