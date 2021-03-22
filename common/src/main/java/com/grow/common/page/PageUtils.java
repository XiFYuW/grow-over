package com.grow.common.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XiFYuW
 * @since  2020/08/29 17:11
 */
public class PageUtils {

    public static <T> Page<T> getPage(long current, long size){
        Page<T> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        return page;
    }

    public static <T> Map<String, Object> getDateMap(PageContext<T> pageContext){
        Page<T> page = pageContext.buildData();
        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());
        return data;
    }

    public static <T> ResultData<T> getDateResultData(PageContext<T> pageContext){
        Page<T> page = pageContext.buildData();
        return new ResultData<T>(page.getTotal(), page.getRecords());
    }

    public static <T> Map<String, Object> getDateMapBack(PageContext<T> pageContext, PageContextBack<T> pageContextBack){
        Page<T> page = pageContext.buildData();
        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", pageContextBack.buildDataBack(page));
        return data;
    }

    public static <T> List<T> getDateMapByRecords(PageContext<T> pageContext){
        Page<T> page = pageContext.buildData();
        return page.getRecords();
    }

}
