package com.grow.self.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grow.common.result.ResponseResult;
import com.grow.self.dto.selfGoodsSize.SelfGoodsSizeAddDTO;
import com.grow.self.dto.selfGoodsSize.SelfGoodsSizeDeleteDTO;
import com.grow.self.dto.selfGoodsSize.SelfGoodsSizeListDTO;
import com.grow.self.dto.selfGoodsSize.SelfGoodsSizeUpdateDTO;
import com.grow.self.entity.SelfGoodsSize;

/**
 * 自营商品尺码表
 *
 * @author XiFYuW
 * @since 2020-08-31
 */
public interface ISelfGoodsSizeService extends IService<SelfGoodsSize> {

    ResponseResult list(SelfGoodsSizeListDTO selfGoodsSizeListDTO);

    ResponseResult add(SelfGoodsSizeAddDTO selfGoodsSizeAddDTO);

    ResponseResult update(SelfGoodsSizeUpdateDTO selfGoodsSizeUpdateDTO);

    ResponseResult delete(SelfGoodsSizeDeleteDTO selfGoodsSizeDeleteDTO);

}
