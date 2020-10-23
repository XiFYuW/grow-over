package com.grow.self.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grow.common.id.GoodsGenContext;
import com.grow.common.result.ResponseResult;
import com.grow.common.result.ResponseResultUtils;
import com.grow.self.common.SelfGoodsCheckServer;
import com.grow.self.dto.selfGoodsSize.SelfGoodsSizeAddDTO;
import com.grow.self.dto.selfGoodsSize.SelfGoodsSizeDeleteDTO;
import com.grow.self.dto.selfGoodsSize.SelfGoodsSizeListDTO;
import com.grow.self.dto.selfGoodsSize.SelfGoodsSizeUpdateDTO;
import com.grow.self.entity.SelfGoodsSize;
import com.grow.self.mapper.SelfGoodsSizeMapper;
import com.grow.self.service.ISelfGoodsSizeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 自营商品尺码表
 *
 * @author XiFYuW
 * @since 2020-08-31
 */
@Service
public class SelfGoodsSizeServiceImpl extends ServiceImpl<SelfGoodsSizeMapper, SelfGoodsSize> implements ISelfGoodsSizeService {

    private final SelfGoodsSizeMapper selfGoodsSizeMapper;

    private final SelfGoodsCheckServer selfGoodsCheckServer;

    public SelfGoodsSizeServiceImpl(SelfGoodsSizeMapper selfGoodsSizeMapper, SelfGoodsCheckServer selfGoodsCheckServer) {
        this.selfGoodsSizeMapper = selfGoodsSizeMapper;
        this.selfGoodsCheckServer = selfGoodsCheckServer;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseResult list(SelfGoodsSizeListDTO selfGoodsSizeListDTO) {
        final String goodsNo = selfGoodsSizeListDTO.getGoodsNo();
        Map<String, Object> data = selfGoodsCheckServer.listSelfGoodsStyle(
                selfGoodsSizeListDTO.getPage(),
                selfGoodsSizeListDTO.getLimit(),
                goodsNo,
                this
        );
        return ResponseResultUtils.getResponseResultS("查询成功", data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult add(SelfGoodsSizeAddDTO selfGoodsSizeAddDTO) {
        boolean isGoodsNo = selfGoodsCheckServer.checkSelfGoodsStyleAdd(
                selfGoodsSizeAddDTO.getGoodsNo(),
                selfGoodsSizeAddDTO.getSizeName(),
                selfGoodsSizeMapper,
                new QueryWrapper<SelfGoodsSize>()
                        .eq("goods_no", selfGoodsSizeAddDTO.getGoodsNo())
                        .eq("standard_name", selfGoodsSizeAddDTO.getSizeName())
                        .eq("is_del", 0)
        );

        final SelfGoodsSize selfGoodsSize = SelfGoodsSize.builder()
                .sizeGoodsNo(GoodsGenContext.generateGoodsNo())
                .goodsNo(isGoodsNo ? selfGoodsSizeAddDTO.getGoodsNo() : GoodsGenContext.generateGoodsNo())
                .sizeName(selfGoodsSizeAddDTO.getSizeName())
                .createTime(DateUtil.toLocalDateTime(DateUtil.date()))
                .isDel(0)
                .sizePrice(selfGoodsSizeAddDTO.getSizePrice())
                .build();
        save(selfGoodsSize);
        return ResponseResultUtils.getResponseResultS("添加成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(SelfGoodsSizeUpdateDTO selfGoodsSizeUpdateDTO) {
        final SelfGoodsSize selfGoodsSize = SelfGoodsSize.builder()
                .id(selfGoodsSizeUpdateDTO.getId())
                .updateTime(DateUtil.toLocalDateTime(DateUtil.date()))
                .sizePrice(selfGoodsSizeUpdateDTO.getSizePrice())
                .sizeName(selfGoodsSizeUpdateDTO.getSizeName())
                .build();

        updateById(selfGoodsSize);
        return ResponseResultUtils.getResponseResultS("修改成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult delete(SelfGoodsSizeDeleteDTO selfGoodsSizeDeleteDTO) {
        final SelfGoodsSize selfGoodsSize = new SelfGoodsSize();
        selfGoodsSize.setId(selfGoodsSizeDeleteDTO.getId());
        selfGoodsSize.setIsDel(1);

        updateById(selfGoodsSize);
        return ResponseResultUtils.getResponseResultS("删除成功");
    }
}
