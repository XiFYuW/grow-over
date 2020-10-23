package com.grow.self.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grow.common.result.ResponseResult;
import com.grow.self.dto.selfGoods.SelfGoodsAddDTO;
import com.grow.self.dto.selfGoods.SelfGoodsDeleteDTO;
import com.grow.self.dto.selfGoods.SelfGoodsListDTO;
import com.grow.self.dto.selfGoods.SelfGoodsUpdateDTO;
import com.grow.self.entity.SelfGoods;

/**
 * 自营商品表
 *
 * @author XiFYuW
 * @since 2020-08-28
 */
public interface ISelfGoodsService extends IService<SelfGoods> {

    ResponseResult list(SelfGoodsListDTO selfGoodsListDTO);

    ResponseResult add(SelfGoodsAddDTO selfGoodsAddDTO);

    ResponseResult update(SelfGoodsUpdateDTO selfGoodsUpdateDTO);

    ResponseResult delete(SelfGoodsDeleteDTO selfGoodsDeleteDTO);


}
