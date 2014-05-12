package com.lm.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class MockDataBase {

    private List data = new ArrayList();

    public MockDataBase() {
        fillData();
    }

    private void fillData() {
        for (int i = 1; i < 100; i++) {
            data.add(buildRow(i));
        }
    }

    private Map buildRow(int i) {
        Map row = new HashMap(4);
        row.put("ID", i);
        row.put("NAME", "LM" + i);
        row.put("TEL", "TEL" + i);
        row.put("AGE", 10 + i);
        return row;
    }

    /**
     * 模拟取得分页数据
     * 没有进行充分校验
     * @param start
     * @param length
     * @return
     */
    public List queryPage(int start, int length) {
        int totalSize = getTotalSize();

        if (start < 0) {
            start = 0;
        }

        if (length < 0) {
            length = 0;
        }

        if (start + length > totalSize) {
            return data.subList(start, totalSize);
        }

        return data.subList(start, start + length);
    }

    public int getTotalSize() {
        return data.size();
    }
}
