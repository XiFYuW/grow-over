package com.grow.common.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author XiFYuW
 * @since  2020/08/31 9:01
 */

public interface PageContextResultDataBack<T, S> {

    List<S> buildResultDataBack(Page<T> page);
}
