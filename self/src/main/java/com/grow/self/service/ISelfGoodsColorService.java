package com.grow.self.service;

import com.grow.common.result.ResponseResult;
import com.grow.self.dto.selfGoodsColor.SelfGoodsColorAddDTO;
import com.grow.self.dto.selfGoodsColor.SelfGoodsColorDeleteDTO;
import com.grow.self.dto.selfGoodsColor.SelfGoodsColorListDTO;
import com.grow.self.dto.selfGoodsColor.SelfGoodsColorUpdateDTO;
import com.grow.self.entity.SelfGoodsColor;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 自营商品颜色表
 *
 * @author XiFYuW
 * @since 2020-08-31
 */
public interface ISelfGoodsColorService extends IService<SelfGoodsColor> {

    ResponseResult list(SelfGoodsColorListDTO selfGoodsColorListDTO);

    ResponseResult add(SelfGoodsColorAddDTO selfGoodsColorAddDTO);

    ResponseResult update(SelfGoodsColorUpdateDTO selfGoodsColorUpdateDTO);

    ResponseResult delete(SelfGoodsColorDeleteDTO selfGoodsColorDeleteDTO);
}
