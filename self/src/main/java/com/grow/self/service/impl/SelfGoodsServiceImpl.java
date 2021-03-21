package com.grow.self.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grow.common.page.PageUtils;
import com.grow.common.result.ResponseResult;
import com.grow.common.result.ResponseResultUtils;
import com.grow.self.dto.selfGoods.SelfGoodsAddDTO;
import com.grow.self.dto.selfGoods.SelfGoodsDeleteDTO;
import com.grow.self.dto.selfGoods.SelfGoodsListDTO;
import com.grow.self.dto.selfGoods.SelfGoodsUpdateDTO;
import com.grow.self.entity.SelfGoods;
import com.grow.self.entity.SelfGoodsColor;
import com.grow.self.entity.SelfGoodsSize;
import com.grow.self.entity.SelfGoodsStandard;
import com.grow.self.mapper.SelfGoodsColorMapper;
import com.grow.self.mapper.SelfGoodsMapper;
import com.grow.self.mapper.SelfGoodsSizeMapper;
import com.grow.self.mapper.SelfGoodsStandardMapper;
import com.grow.self.service.ISelfGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 自营商品表
 *
 * @author XiFYuW
 * @since 2020-08-28
 */
@Service
public class SelfGoodsServiceImpl extends ServiceImpl<SelfGoodsMapper, SelfGoods> implements ISelfGoodsService {
    private final SelfGoodsStandardMapper selfGoodsStandardMapper;

    private final SelfGoodsColorMapper selfGoodsColorMapper;

    private final SelfGoodsSizeMapper selfGoodsSizeMapper;

    public SelfGoodsServiceImpl(SelfGoodsStandardMapper selfGoodsStandardMapper, SelfGoodsColorMapper selfGoodsColorMapper,
                                SelfGoodsSizeMapper selfGoodsSizeMapper) {
        this.selfGoodsStandardMapper = selfGoodsStandardMapper;
        this.selfGoodsColorMapper = selfGoodsColorMapper;
        this.selfGoodsSizeMapper = selfGoodsSizeMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseResult list(SelfGoodsListDTO selfGoodsListDTO) {
        String goodsNo = selfGoodsListDTO.getGoodsNo();
        String goodsName = selfGoodsListDTO.getGoodsName();
        String goodsTypeId = selfGoodsListDTO.getGoodsTypeId();
        Map<String, Object> data = PageUtils.getDateMap(() -> page(PageUtils.getPage(
                selfGoodsListDTO.getPage(), selfGoodsListDTO.getSize()),
                new QueryWrapper<SelfGoods>()
                        .eq("is_del", 0)
                        .likeRight(!StringUtils.isEmpty(goodsNo), "goods_no", goodsNo)
                        .likeRight(!StringUtils.isEmpty(goodsName), "goods_name", goodsName)
                        .eq(!StringUtils.isEmpty(goodsTypeId), "goods_type_id", goodsTypeId)
                        .orderByDesc("create_time")));
        return ResponseResultUtils.getResponseResultS("查询成功", data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult add(SelfGoodsAddDTO selfGoodsAddDTO) {

        Integer selfGoodsStandardCount = selfGoodsStandardMapper.selectCount(new QueryWrapper<SelfGoodsStandard>()
                .eq("goods_no", selfGoodsAddDTO.getGoodsNo()).eq("is_del", 0));

        Integer selfGoodsColorCount = selfGoodsColorMapper.selectCount(new QueryWrapper<SelfGoodsColor>()
                .eq("goods_no", selfGoodsAddDTO.getGoodsNo()).eq("is_del", 0));

        Integer selfGoodsSizeCount = selfGoodsSizeMapper.selectCount(new QueryWrapper<SelfGoodsSize>()
                .eq("goods_no", selfGoodsAddDTO.getGoodsNo()).eq("is_del", 0));

        if (selfGoodsStandardCount.compareTo(0) <= 0 && selfGoodsColorCount.compareTo(0) <= 0 && selfGoodsSizeCount.compareTo(0) <= 0) {
            return ResponseResultUtils.getResponseResultF("商品编号错误");
        }

        final SelfGoods selfGoods = SelfGoods.builder()
                .goodsNo(selfGoodsAddDTO.getGoodsNo())
                .createTime(DateUtil.toLocalDateTime(DateUtil.date()))
                .isDel(0)
                .goodsName(selfGoodsAddDTO.getGoodsName())
                .goodsTypeId(selfGoodsAddDTO.getGoodsTypeId())
                .parentId("")/*登录用户parentId*/
                .sellerUserId("")/*登录用户parentId*/
                .shopId(selfGoodsAddDTO.getShopId())
                .goodsIntroduce(selfGoodsAddDTO.getGoodsIntroduce())
                .goodsDetails(selfGoodsAddDTO.getGoodsDetails())
                .goodsSupply(selfGoodsAddDTO.getGoodsSupply())
                .build();

        save(selfGoods);
        return ResponseResultUtils.getResponseResultS("添加成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(SelfGoodsUpdateDTO selfGoodsUpdateDTO) {
        final SelfGoods selfGoods = SelfGoods.builder()
                .goodsName(selfGoodsUpdateDTO.getGoodsName())
                .goodsTypeId(selfGoodsUpdateDTO.getGoodsTypeId())
                .goodsIntroduce(selfGoodsUpdateDTO.getGoodsIntroduce())
                .goodsDetails(selfGoodsUpdateDTO.getGoodsDetails())
                .goodsSupply(selfGoodsUpdateDTO.getGoodsSupply())
                .updateTime(DateUtil.toLocalDateTime(DateUtil.date()))
                .build();

        updateById(selfGoods);
        return ResponseResultUtils.getResponseResultS("修改成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult delete(SelfGoodsDeleteDTO selfGoodsDeleteDTO) {
        final SelfGoods selfGoods = SelfGoods.builder()
                .id(selfGoodsDeleteDTO.getId())
                .isDel(1)
                .build();

        updateById(selfGoods);
        return ResponseResultUtils.getResponseResultS("删除成功");
    }

}
