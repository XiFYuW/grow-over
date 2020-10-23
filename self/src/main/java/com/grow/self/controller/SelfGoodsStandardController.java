package com.grow.self.controller;

import com.grow.common.log.LogOutAnnotation;
import com.grow.common.result.ResponseResult;
import com.grow.self.dto.selfGoodsStandard.SelfGoodsStandardAddDTO;
import com.grow.self.dto.selfGoodsStandard.SelfGoodsStandardDeleteDTO;
import com.grow.self.dto.selfGoodsStandard.SelfGoodsStandardListDTO;
import com.grow.self.dto.selfGoodsStandard.SelfGoodsStandardUpdateDTO;
import com.grow.self.service.ISelfGoodsStandardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 自营商品规格表
 *
 * @author XiFYuW
 * @since 2020-08-31
 */
@RestController
@RequestMapping("/selfGoodsStandard")
@Api(tags = "自营商品规格")
public class SelfGoodsStandardController {

    private  final ISelfGoodsStandardService iSelfGoodsStandardService;

    public SelfGoodsStandardController(ISelfGoodsStandardService iSelfGoodsStandardService) {
        this.iSelfGoodsStandardService = iSelfGoodsStandardService;
    }

    @ApiOperation(value="自营商品规格分页查询")
    @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfGoodsStandard/list")
    public ResponseResult selfGoodsStandardList(@Valid SelfGoodsStandardListDTO selfGoodsStandardListDTO){
        return iSelfGoodsStandardService.list(selfGoodsStandardListDTO);
    }

    @ApiOperation(value="自营商品规格新增")
    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfGoodsStandard/add")
    public ResponseResult selfGoodsStandardAdd(@Valid SelfGoodsStandardAddDTO selfGoodsStandardAddDTO){
        return iSelfGoodsStandardService.add(selfGoodsStandardAddDTO);
    }

    @ApiOperation(value="自营商品规格修改")
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfGoodsStandard/update")
    public ResponseResult selfGoodsStandardUpdate(@Valid SelfGoodsStandardUpdateDTO selfGoodsStandardUpdateDTO){
        return iSelfGoodsStandardService.update(selfGoodsStandardUpdateDTO);
    }

    @ApiOperation(value="自营商品规格删除")
    @PostMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfGoodsStandard/delete")
    public ResponseResult selfGoodsStandardDelete(@Valid SelfGoodsStandardDeleteDTO selfGoodsStandardDeleteDTO){
        return iSelfGoodsStandardService.delete(selfGoodsStandardDeleteDTO);
    }
}

