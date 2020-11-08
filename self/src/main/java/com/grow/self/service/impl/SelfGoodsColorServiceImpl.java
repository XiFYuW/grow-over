package com.grow.self.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grow.common.id.GoodsGenContext;
import com.grow.common.result.ResponseResult;
import com.grow.common.result.ResponseResultUtils;
import com.grow.self.common.SelfGoodsCheckServer;
import com.grow.self.dto.selfGoodsColor.SelfGoodsColorAddDTO;
import com.grow.self.dto.selfGoodsColor.SelfGoodsColorDeleteDTO;
import com.grow.self.dto.selfGoodsColor.SelfGoodsColorListDTO;
import com.grow.self.dto.selfGoodsColor.SelfGoodsColorUpdateDTO;
import com.grow.self.entity.SelfGoodsColor;
import com.grow.self.mapper.SelfGoodsColorMapper;
import com.grow.self.service.ISelfGoodsColorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 自营商品颜色表
 *
 * @author XiFYuW
 * @since 2020-08-31
 */
@Service
public class SelfGoodsColorServiceImpl extends ServiceImpl<SelfGoodsColorMapper, SelfGoodsColor> implements ISelfGoodsColorService {
    private final SelfGoodsColorMapper selfGoodsColorMapper;

    private final SelfGoodsCheckServer selfGoodsCheckServer;

    public SelfGoodsColorServiceImpl(SelfGoodsColorMapper selfGoodsColorMapper, SelfGoodsCheckServer selfGoodsCheckServer) {
        this.selfGoodsColorMapper = selfGoodsColorMapper;
        this.selfGoodsCheckServer = selfGoodsCheckServer;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseResult list(SelfGoodsColorListDTO selfGoodsColorListDTO) {
        final String goodsNo = selfGoodsColorListDTO.getGoodsNo();
        Map<String, Object> data = selfGoodsCheckServer.listSelfGoodsStyle(
                selfGoodsColorListDTO.getPage(),
                selfGoodsColorListDTO.getLimit(),
                goodsNo,
                this
        );
        return ResponseResultUtils.getResponseResultS("查询成功", data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult add(SelfGoodsColorAddDTO selfGoodsColorAddDTO) {
        boolean isGoodsNo = selfGoodsCheckServer.checkSelfGoodsStyleAdd(
                selfGoodsColorAddDTO.getGoodsNo(),
                selfGoodsColorAddDTO.getColorName(),
                selfGoodsColorMapper,
                new QueryWrapper<SelfGoodsColor>()
                        .eq("goods_no", selfGoodsColorAddDTO.getGoodsNo())
                        .eq("color_name", selfGoodsColorAddDTO.getColorName())
                        .eq("is_del", 0)
        );

        final String goodsNo = isGoodsNo ? selfGoodsColorAddDTO.getGoodsNo() : GoodsGenContext.generateGoodsNo();

        final SelfGoodsColor selfGoodsColor = SelfGoodsColor.builder()
                .colorGoodsNo(GoodsGenContext.generateGoodsNo())
                .isDel(0)
                .createTime(DateUtil.toLocalDateTime(DateUtil.date()))
                .colorPrice(selfGoodsColorAddDTO.getColorPrice())
                .colorName(selfGoodsColorAddDTO.getColorName())
                .goodsNo(goodsNo)
                .build();
        save(selfGoodsColor);

        if (isGoodsNo) {
            return ResponseResultUtils.getResponseResultS("添加成功");
        }
        final Map<String, Object> map = new HashMap<>();
        map.put("goodsNo", goodsNo);
        return ResponseResultUtils.getResponseResultS("添加成功", map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(SelfGoodsColorUpdateDTO selfGoodsColorUpdateDTO) {
        final SelfGoodsColor selfGoodsColor = SelfGoodsColor.builder()
                .id(selfGoodsColorUpdateDTO.getId())
                .colorName(selfGoodsColorUpdateDTO.getColorName())
                .colorPrice(selfGoodsColorUpdateDTO.getColorPrice())
                .updateTime(DateUtil.toLocalDateTime(DateUtil.date()))
                .build();

        updateById(selfGoodsColor);
        return ResponseResultUtils.getResponseResultS("修改成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult delete(SelfGoodsColorDeleteDTO selfGoodsColorDeleteDTO) {
        final SelfGoodsColor selfGoodsColor = new SelfGoodsColor();
        selfGoodsColor.setId(selfGoodsColorDeleteDTO.getId());
        selfGoodsColor.setIsDel(1);

        updateById(selfGoodsColor);
        return ResponseResultUtils.getResponseResultS("删除成功");
    }
}
