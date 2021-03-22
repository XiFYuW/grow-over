package com.grow.auth.system.service;

import com.grow.auth.system.dto.systemMenu.*;
import com.grow.auth.system.entity.SystemMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.grow.common.result.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 系统菜单
 *
 * @author XiFYuW
 * @since 2020-09-04
 */
public interface ISystemMenuService extends IService<SystemMenu> {

    ResponseResult list(SystemMenuListDTO systemMenuListDTO);

    ResponseResult childrenList(SystemMenuChildrenListDTO systemMenuChildrenListDTO);

    ResponseResult add(SystemMenuAddDTO systemMenuAddDTO);

    ResponseResult superior(List<Long> ids);

    ResponseResult update(SystemMenuUpdateDTO systemMenuUpdateDTO);

    ResponseResult delete(SystemMenuDeleteDTO systemMenuDeleteDTO);

    ResponseResult selectTreeData(SystemMenuSelectTreeDataDTO systemMenuSelectTreeDataDTO);

    ResponseResult accredit(SystemMenuAccreditDTO systemMenuAccreditDTO);

    ResponseResult structure();
}
