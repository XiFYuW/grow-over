package com.grow.self.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grow.common.result.ResponseResult;
import com.grow.self.dto.selfGoodsStandard.SelfGoodsStandardAddDTO;
import com.grow.self.dto.selfGoodsStandard.SelfGoodsStandardDeleteDTO;
import com.grow.self.dto.selfGoodsStandard.SelfGoodsStandardListDTO;
import com.grow.self.dto.selfGoodsStandard.SelfGoodsStandardUpdateDTO;
import com.grow.self.entity.SelfGoodsStandard;

/**
 * 自营商品规格表
 *
 * @author XiFYuW
 * @since 2020-08-31
 */
public interface ISelfGoodsStandardService extends IService<SelfGoodsStandard> {

    ResponseResult list(SelfGoodsStandardListDTO selfGoodsStandardListDTO);

    ResponseResult add(SelfGoodsStandardAddDTO selfGoodsStandardAddDTO);

    ResponseResult update(SelfGoodsStandardUpdateDTO selfGoodsStandardUpdateDTO);

    ResponseResult delete(SelfGoodsStandardDeleteDTO selfGoodsStandardDeleteDTO);

}
