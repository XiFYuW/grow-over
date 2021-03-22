package com.grow.auth.system.controller;

import com.grow.auth.system.dto.systemMenu.*;
import com.grow.auth.system.service.ISystemMenuService;
import com.grow.common.log.LogOutAnnotation;
import com.grow.common.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统菜单
 *
 * @author XiFYuW
 * @since 2020-09-04
 */
@RestController
@RequestMapping("/web/systemMenu")
@Slf4j
@Api(tags = "系统菜单")
public class SystemMenuController {

    private final ISystemMenuService iSystemMenuService;

    public SystemMenuController(ISystemMenuService iSystemMenuService) {
        this.iSystemMenuService = iSystemMenuService;
    }

    @ApiOperation(value="系统顶级菜单分页查询")
    @GetMapping(value = "/list")
    @LogOutAnnotation(url = "/systemMenu/list")
    public ResponseResult systemMenuList(@Validated SystemMenuListDTO systemMenuListDTO){
        return iSystemMenuService.list(systemMenuListDTO);
    }

    @ApiOperation(value="系统子菜单查询")
    @GetMapping(value = "/childrenList")
    @LogOutAnnotation(url = "/systemMenu/childrenList")
    public ResponseResult childrenList(@Validated SystemMenuChildrenListDTO systemMenuChildrenListDTO){
        return iSystemMenuService.childrenList(systemMenuChildrenListDTO);
    }

    @ApiOperation(value="系统菜单新增")
    @PostMapping(value = "/add")
    @LogOutAnnotation(url = "/systemMenu/add")
    public ResponseResult systemMenuAdd(@RequestBody SystemMenuAddDTO systemMenuAddDTO){
        return iSystemMenuService.add(systemMenuAddDTO);
    }

    @ApiOperation(value="查询菜单:根据ID获取同级与上级数据")
    @PostMapping(value = "/superior")
    @LogOutAnnotation(url = "/systemMenu/superior")
    public ResponseResult superior(@RequestBody List<Long> ids){
        return iSystemMenuService.superior(ids);
    }

    @ApiOperation(value="系统菜单修改")
    @PostMapping(value = "/update")
    @Validated
    @LogOutAnnotation(url = "/systemMenu/update")
    public ResponseResult systemMenuUpdate(@Validated SystemMenuUpdateDTO systemMenuUpdateDTO){
        return iSystemMenuService.update(systemMenuUpdateDTO);
    }

    @ApiOperation(value="系统菜单删除")
    @PostMapping(value = "/delete")
    @Validated
    @LogOutAnnotation(url = "/systemMenu/delete")
    public ResponseResult systemMenuDelete(@Validated SystemMenuDeleteDTO systemMenuDeleteDTO){
        return iSystemMenuService.delete(systemMenuDeleteDTO);
    }

    @ApiOperation(value="系统菜单下拉列表树")
    @PostMapping(value = "/selectTreeData")
    @Validated
    @LogOutAnnotation(url = "/systemMenu/selectTreeData")
    public ResponseResult systemMenuSelectTreeData(@Validated SystemMenuSelectTreeDataDTO systemMenuSelectTreeDataDTO){
        return iSystemMenuService.selectTreeData(systemMenuSelectTreeDataDTO);
    }

    @ApiOperation(value="系统菜单授权")
    @PostMapping(value = "/accredit")
    @LogOutAnnotation(url = "/systemMenu/accredit")
    public ResponseResult systemMenuAccredit(@Validated SystemMenuAccreditDTO systemMenuAccreditDTO){
        return iSystemMenuService.accredit(systemMenuAccreditDTO);
    }

    @ApiOperation(value="登录获取系统菜单")
    @PostMapping(value = "/structure")
    @LogOutAnnotation(url = "/systemMenu/structure")
    public ResponseResult systemMenuStructure(){
        return iSystemMenuService.structure();
    }
}

