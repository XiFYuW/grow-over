package com.grow.common.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/31 9:01
 */
public interface PageContext<T> {

    Page<T> buildData();
}
