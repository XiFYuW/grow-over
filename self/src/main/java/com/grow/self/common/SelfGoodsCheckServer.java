package com.grow.self.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grow.common.exception.BaseRuntimeException;
import com.grow.common.page.PageUtils;
import com.grow.common.result.ResponseResultUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/06 16:32
 */
@Service
public class SelfGoodsCheckServer {

    public <T> boolean checkSelfGoodsStyleAdd(
            String goodsNo,
            String name,
            final BaseMapper<T> baseMapper,
            final QueryWrapper<T> queryWrapper) {
        boolean isGoodsNo = !StringUtils.isEmpty(goodsNo);
        if (isGoodsNo) {
            Integer count = baseMapper.selectCount(queryWrapper);
            if (count.compareTo(0) > 0) {
                throw new BaseRuntimeException(ResponseResultUtils.getResponseResultF(name + "该商品样式已存在，请更换商品或重新输入。"));
            }
        }
        return isGoodsNo;
    }

    public <T, M extends BaseMapper<T>> Map<String, Object> listSelfGoodsStyle(
            long current,
            long limit,
            String goodsNo,
            final ServiceImpl<M, T> mtService) {
        return PageUtils.getDateMap(() -> mtService.page(PageUtils.getPage(current, limit), new QueryWrapper<T>()
                .eq("is_del", 0)
                .eq(!StringUtils.isEmpty(goodsNo), "goods_no", goodsNo)
                .orderByDesc("create_time")));
    }
}
