package com.grow.common.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author XiFYuW
 * @date 2020/08/31 9:01
 */

public interface PageContextBack<T> {

    Object buildDataBack(Page<T> page);
}
