package com.grow.self.controller;

import com.grow.common.log.LogOutAnnotation;
import com.grow.common.result.ResponseResult;
import com.grow.self.dto.selfCategory.*;
import com.grow.self.service.ISelfCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 自营商品分类
 *
 * @author XiFYuW
 * @since 2020-08-31
 */
@RestController
@RequestMapping("/selfCategory")
@Slf4j
@Api(tags = "自营商品分类")
public class SelfCategoryController {

    private final ISelfCategoryService iSelfCategoryService;

    public SelfCategoryController(ISelfCategoryService iSelfCategoryService) {
        this.iSelfCategoryService = iSelfCategoryService;
    }

    @ApiOperation(value="自营商品分类分页查询")
    @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfCategory/list")
    public ResponseResult selfCategoryList(@Valid SelfCategoryListDTO selfCategoryListDTO){
        return iSelfCategoryService.list(selfCategoryListDTO);
    }

    @ApiOperation(value="自营商品分类新增")
    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfCategory/add")
    public ResponseResult selfCategoryAdd(@Valid SelfCategoryAddDTO selfCategoryAddDTO){
        return iSelfCategoryService.add(selfCategoryAddDTO);
    }

    @ApiOperation(value="自营商品分类修改")
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfCategory/update")
    public ResponseResult selfCategoryUpdate(@Valid SelfCategoryUpdateDTO selfCategoryUpdateDTO){
        return iSelfCategoryService.update(selfCategoryUpdateDTO);
    }

    @ApiOperation(value="自营商品分类删除")
    @PostMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfCategory/delete")
    public ResponseResult selfCategoryDelete(@Valid SelfCategoryDeleteDTO selfCategoryDeleteDTO){
        return iSelfCategoryService.delete(selfCategoryDeleteDTO);
    }

    @ApiOperation(value="获取下拉选择自营商品分类")
    @PostMapping(value = "/selectData", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/selfCategory/selectData")
    public ResponseResult selfCategorySelectData(@Valid SelfCategoryGetSelectDataDTO selfCategoryGetSelectDataDTO){
        return iSelfCategoryService.getSelectData(selfCategoryGetSelectDataDTO);
    }

}

