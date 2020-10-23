package com.grow.self.controller;

import com.grow.common.log.LogOutAnnotation;
import com.grow.common.result.ResponseResult;
import com.grow.self.dto.selfGoodsSize.SelfGoodsSizeAddDTO;
import com.grow.self.dto.selfGoodsSize.SelfGoodsSizeDeleteDTO;
import com.grow.self.dto.selfGoodsSize.SelfGoodsSizeListDTO;
import com.grow.self.dto.selfGoodsSize.SelfGoodsSizeUpdateDTO;
import com.grow.self.service.ISelfGoodsSizeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 自营商品尺码表
 *
 * @author XiFYuW
 * @since 2020-08-31
 */
@RestController
@RequestMapping("/selfGoodsSize")
@Api(tags = "自营商品尺码")
public class SelfGoodsSizeController {

    private final ISelfGoodsSizeService iSelfGoodsSizeService;

    public SelfGoodsSizeController(ISelfGoodsSizeService iSelfGoodsSizeService) {
        this.iSelfGoodsSizeService = iSelfGoodsSizeService;
    }

    @ApiOperation(value="自营商品尺码分页查询")
    @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfGoodsSize/list")
    public ResponseResult selfGoodsSizeList(@Valid SelfGoodsSizeListDTO selfGoodsSizeListDTO){
        return iSelfGoodsSizeService.list(selfGoodsSizeListDTO);
    }

    @ApiOperation(value="自营商品尺码新增")
    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfGoodsSize/add")
    public ResponseResult selfGoodsSizeAdd(@Valid SelfGoodsSizeAddDTO selfGoodsSizeAddDTO){
        return iSelfGoodsSizeService.add(selfGoodsSizeAddDTO);
    }

    @ApiOperation(value="自营商品尺码修改")
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfGoodsSize/update")
    public ResponseResult selfGoodsSizeUpdate(@Valid SelfGoodsSizeUpdateDTO selfGoodsSizeUpdateDTO){
        return iSelfGoodsSizeService.update(selfGoodsSizeUpdateDTO);
    }

    @ApiOperation(value="自营商品尺码删除")
    @PostMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfGoodsSize/delete")
    public ResponseResult selfGoodsSizeDelete(@Valid SelfGoodsSizeDeleteDTO selfGoodsSizeDeleteDTO){
        return iSelfGoodsSizeService.delete(selfGoodsSizeDeleteDTO);
    }

}

