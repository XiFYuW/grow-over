package com.grow.auth.system.controller;

import com.grow.auth.system.dto.systemMenu.*;
import com.grow.auth.system.service.ISystemMenuService;
import com.grow.common.log.LogOutAnnotation;
import com.grow.common.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 系统菜单
 *
 * @author XiFYuW
 * @since 2020-09-04
 */
@RestController
@RequestMapping("/systemMenu")
@Slf4j
@Api(tags = "系统菜单")
public class SystemMenuController {

    private final ISystemMenuService iSystemMenuService;

    public SystemMenuController(ISystemMenuService iSystemMenuService) {
        this.iSystemMenuService = iSystemMenuService;
    }

    @ApiOperation(value="系统菜单分页查询")
    @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/systemMenu/list")
    public ResponseResult systemMenuList(@Valid SystemMenuListDTO systemMenuListDTO){
        return iSystemMenuService.list(systemMenuListDTO);
    }

    @ApiOperation(value="系统菜单新增")
    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/systemMenu/add")
    public ResponseResult systemMenuAdd(@Valid SystemMenuAddDTO systemMenuAddDTO){
        return iSystemMenuService.add(systemMenuAddDTO);
    }

    @ApiOperation(value="系统菜单修改")
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/systemMenu/update")
    public ResponseResult systemMenuUpdate(@Valid SystemMenuUpdateDTO systemMenuUpdateDTO){
        return iSystemMenuService.update(systemMenuUpdateDTO);
    }

    @ApiOperation(value="系统菜单删除")
    @PostMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/systemMenu/delete")
    public ResponseResult systemMenuDelete(@Valid SystemMenuDeleteDTO systemMenuDeleteDTO){
        return iSystemMenuService.delete(systemMenuDeleteDTO);
    }

    @ApiOperation(value="系统菜单下拉列表树")
    @PostMapping(value = "/selectTreeData", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/systemMenu/selectTreeData")
    public ResponseResult systemMenuSelectTreeData(@Valid SystemMenuSelectTreeDataDTO systemMenuSelectTreeDataDTO){
        return iSystemMenuService.selectTreeData(systemMenuSelectTreeDataDTO);
    }

    @ApiOperation(value="系统菜单授权")
    @PostMapping(value = "/accredit", produces = "application/json;charset=UTF-8")
    @Validated
    @LogOutAnnotation(url = "/systemMenu/accredit")
    public ResponseResult systemMenuAccredit(@Valid SystemMenuAccreditDTO systemMenuAccreditDTO){
        return iSystemMenuService.accredit(systemMenuAccreditDTO);
    }
}

