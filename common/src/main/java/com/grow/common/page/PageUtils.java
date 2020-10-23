package com.grow.common.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/29 17:11
 */
public class PageUtils {

    public static <T> Page<T> getPage(Page<T> page, long current, long size){
        page.setCurrent(current);
        page.setSize(size);
        return page;
    }

    public static <T> Map<String, Object> getDateMap(PageContext<T> pageContext){
        Page<T> page = pageContext.buildData();
        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("records", page.getRecords());
        return data;
    }

    public static <T> List<T> getDateMapByRecords(PageContext<T> pageContext){
        Page<T> page = pageContext.buildData();
        return page.getRecords();
    }
}
