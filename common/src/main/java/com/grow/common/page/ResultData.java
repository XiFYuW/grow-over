package com.grow.common.page;

import java.util.List;

public class ResultData<T> {

    private final Long total;
    private final List<T> rows;
    ResultData(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public List<T> getRows() {
        return rows;
    }
}
