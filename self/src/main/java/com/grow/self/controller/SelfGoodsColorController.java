package com.grow.self.controller;

import com.grow.common.log.LogOutAnnotation;
import com.grow.common.result.ResponseResult;
import com.grow.self.dto.selfGoodsColor.SelfGoodsColorAddDTO;
import com.grow.self.dto.selfGoodsColor.SelfGoodsColorDeleteDTO;
import com.grow.self.dto.selfGoodsColor.SelfGoodsColorListDTO;
import com.grow.self.dto.selfGoodsColor.SelfGoodsColorUpdateDTO;
import com.grow.self.service.ISelfGoodsColorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 自营商品颜色表
 *
 * @author XiFYuW
 * @since 2020-08-31
 */
@RestController
@RequestMapping("/selfGoodsColor")
@Api(tags = "自营商品颜色")
public class SelfGoodsColorController {

    private final ISelfGoodsColorService iSelfGoodsColorService;

    public SelfGoodsColorController(ISelfGoodsColorService iSelfGoodsColorService) {
        this.iSelfGoodsColorService = iSelfGoodsColorService;
    }

    @ApiOperation(value="自营商品颜色分页查询")
    @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfGoodsColor/list")
    public ResponseResult selfGoodsColorList(@Valid SelfGoodsColorListDTO selfGoodsColorListDTO){
        return iSelfGoodsColorService.list(selfGoodsColorListDTO);
    }

    @ApiOperation(value="自营商品颜色新增")
    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfGoodsColor/add")
    public ResponseResult selfGoodsColorAdd(@Valid SelfGoodsColorAddDTO selfGoodsColorAddDTO){
        return iSelfGoodsColorService.add(selfGoodsColorAddDTO);
    }

    @ApiOperation(value="自营商品颜色修改")
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfGoodsColor/update")
    public ResponseResult selfGoodsColorUpdate(@Valid SelfGoodsColorUpdateDTO selfGoodsColorUpdateDTO){
        return iSelfGoodsColorService.update(selfGoodsColorUpdateDTO);
    }

    @ApiOperation(value="自营商品颜色删除")
    @PostMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfGoodsColor/delete")
    public ResponseResult selfGoodsColorDelete(@Valid SelfGoodsColorDeleteDTO selfGoodsColorDeleteDTO){
        return iSelfGoodsColorService.delete(selfGoodsColorDeleteDTO);
    }
}

