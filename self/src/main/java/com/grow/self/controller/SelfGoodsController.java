package com.grow.self.controller;

import com.grow.common.log.LogOutAnnotation;
import com.grow.common.result.ResponseResult;
import com.grow.self.dto.selfGoods.SelfGoodsAddDTO;
import com.grow.self.dto.selfGoods.SelfGoodsDeleteDTO;
import com.grow.self.dto.selfGoods.SelfGoodsListDTO;
import com.grow.self.dto.selfGoods.SelfGoodsUpdateDTO;
import com.grow.self.service.ISelfGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 自营商品表
 *
 * @author XiFYuW
 * @since 2020-08-28
 */
@RestController
@RequestMapping("/selfGoods")
@Api(tags = "自营商品")
public class SelfGoodsController {
    private final ISelfGoodsService iSelfGoodsService;

    public SelfGoodsController(ISelfGoodsService iSelfGoodsService) {
        this.iSelfGoodsService = iSelfGoodsService;
    }

    @ApiOperation(value="自营商品分页查询")
    @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfGoods/list")
    public ResponseResult selfGoodsList(@Valid final SelfGoodsListDTO selfGoodsListDTO){
        return iSelfGoodsService.list(selfGoodsListDTO);
    }

    @ApiOperation(value="自营商品新增")
    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfGoods/add")
    public  ResponseResult selfGoodsAdd(@Valid SelfGoodsAddDTO selfGoodsAddDTO){
        return iSelfGoodsService.add(selfGoodsAddDTO);
    }

    @ApiOperation(value="自营商品修改")
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfGoods/update")
    public ResponseResult selfGoodsUpdate(@Valid SelfGoodsUpdateDTO selfGoodsUpdateDTO){
        return iSelfGoodsService.update(selfGoodsUpdateDTO);
    }

    @ApiOperation(value="自营商品删除")
    @PostMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfGoods/delete")
    public ResponseResult selfGoodsDelete(@Valid SelfGoodsDeleteDTO selfGoodsDeleteDTO){
        return iSelfGoodsService.delete(selfGoodsDeleteDTO);
    }

}

