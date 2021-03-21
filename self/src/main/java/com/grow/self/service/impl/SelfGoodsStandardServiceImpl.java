package com.grow.self.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grow.common.id.GoodsGenContext;
import com.grow.common.result.ResponseResult;
import com.grow.common.result.ResponseResultUtils;
import com.grow.self.common.SelfGoodsCheckServer;
import com.grow.self.dto.selfGoodsStandard.SelfGoodsStandardAddDTO;
import com.grow.self.dto.selfGoodsStandard.SelfGoodsStandardDeleteDTO;
import com.grow.self.dto.selfGoodsStandard.SelfGoodsStandardListDTO;
import com.grow.self.dto.selfGoodsStandard.SelfGoodsStandardUpdateDTO;
import com.grow.self.entity.SelfGoodsStandard;
import com.grow.self.mapper.SelfGoodsStandardMapper;
import com.grow.self.service.ISelfGoodsStandardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 自营商品规格表
 *
 * @author XiFYuW
 * @since 2020-08-31
 */
@Service
public class SelfGoodsStandardServiceImpl extends ServiceImpl<SelfGoodsStandardMapper, SelfGoodsStandard> implements ISelfGoodsStandardService {

    private final SelfGoodsStandardMapper selfGoodsStandardMapper;

    private final SelfGoodsCheckServer selfGoodsCheckServer;

    public SelfGoodsStandardServiceImpl(SelfGoodsStandardMapper selfGoodsStandardMapper, SelfGoodsCheckServer selfGoodsCheckServer) {
        this.selfGoodsStandardMapper = selfGoodsStandardMapper;
        this.selfGoodsCheckServer = selfGoodsCheckServer;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseResult list(SelfGoodsStandardListDTO selfGoodsStandardListDTO) {
        final String goodsNo = selfGoodsStandardListDTO.getGoodsNo();
        Map<String, Object> data = selfGoodsCheckServer.listSelfGoodsStyle(
                selfGoodsStandardListDTO.getPage(),
                selfGoodsStandardListDTO.getSize(),
                goodsNo,
                this
        );
        return ResponseResultUtils.getResponseResultS("查询成功", data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult add(SelfGoodsStandardAddDTO selfGoodsStandardAddDTO) {
        boolean isGoodsNo = selfGoodsCheckServer.checkSelfGoodsStyleAdd(
                selfGoodsStandardAddDTO.getGoodsNo(),
                selfGoodsStandardAddDTO.getStandardName(),
                selfGoodsStandardMapper,
                new QueryWrapper<SelfGoodsStandard>()
                        .eq("goods_no", selfGoodsStandardAddDTO.getGoodsNo())
                        .eq("standard_name", selfGoodsStandardAddDTO.getStandardName())
                        .eq("is_del", 0)
        );

        final SelfGoodsStandard selfGoodsStandard = SelfGoodsStandard.builder()
                .createTime(DateUtil.toLocalDateTime(DateUtil.date()))
                .isDel(0)
                .standardName(selfGoodsStandardAddDTO.getStandardName())
                .standardPrice(selfGoodsStandardAddDTO.getStandardPrice())
                .goodsNo(isGoodsNo ? selfGoodsStandardAddDTO.getGoodsNo() : GoodsGenContext.generateGoodsNo())
                .standardGoodsNo(GoodsGenContext.generateGoodsNo())
                .build();
        save(selfGoodsStandard);
        return ResponseResultUtils.getResponseResultS("添加成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(SelfGoodsStandardUpdateDTO selfGoodsStandardUpdateDTO) {
        final SelfGoodsStandard selfGoodsStandard = SelfGoodsStandard.builder()
                .id(selfGoodsStandardUpdateDTO.getId())
                .updateTime(DateUtil.toLocalDateTime(DateUtil.date()))
                .standardName(selfGoodsStandardUpdateDTO.getStandardName())
                .standardPrice(selfGoodsStandardUpdateDTO.getStandardPrice())
                .build();

        updateById(selfGoodsStandard);
        return ResponseResultUtils.getResponseResultS("修改成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult delete(SelfGoodsStandardDeleteDTO selfGoodsStandardDeleteDTO) {
        final SelfGoodsStandard selfGoodsStandard = new SelfGoodsStandard();
        selfGoodsStandard.setId(selfGoodsStandardDeleteDTO.getId());
        selfGoodsStandard.setIsDel(1);

        updateById(selfGoodsStandard);
        return ResponseResultUtils.getResponseResultS("删除成功");
    }
}
