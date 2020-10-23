package com.grow.auth.system.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grow.auth.system.dto.systemMenu.*;
import com.grow.auth.system.entity.SystemMenu;
import com.grow.auth.system.mapper.SystemMenuMapper;
import com.grow.auth.system.service.ISystemMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grow.auth.system.vo.SystemMenuSelectTreeDataVO;
import com.grow.common.exception.BaseRuntimeException;
import com.grow.common.page.PageUtils;
import com.grow.common.result.ResponseResult;
import com.grow.common.result.ResponseResultUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统菜单
 *
 * @author XiFYuW
 * @since 2020-09-04
 */
@Service
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements ISystemMenuService {

    @Override
    public ResponseResult list(SystemMenuListDTO systemMenuListDTO) {
        String systemPath = systemMenuListDTO.getSystemPath();
        String systemMenuName  = systemMenuListDTO.getSystemMenuName();
        Long id = systemMenuListDTO.getId();
        Map<String, Object> data = PageUtils.getDateMap(() -> page(PageUtils.getPage(
                new Page<>(), systemMenuListDTO.getPage(), systemMenuListDTO.getLimit()),
                new QueryWrapper<SystemMenu>()
                        .eq("is_del", 0)
                        .eq(id.compareTo(0L) > 0L,"system_menu_pid", id)
                        .likeRight(!StringUtils.isEmpty(systemPath), "system_path", systemPath)
                        .likeRight(!StringUtils.isEmpty(systemMenuName), "system_menu_name", systemMenuName)
                        .orderByDesc("create_time")));
        return ResponseResultUtils.getResponseResultS("查询成功", data);
    }

    @Override
    public ResponseResult add(SystemMenuAddDTO systemMenuAddDTO) {
        final SystemMenu systemMenu = SystemMenu.builder()
                .createTime(DateUtil.toLocalDateTime(DateUtil.date()))
                .systemMenuId(IdUtil.simpleUUID())
                .systemMenuName(systemMenuAddDTO.getSystemMenuName())
                .systemPath(systemMenuAddDTO.getSystemPath())
                .isDel(0)
                .systemPic(systemMenuAddDTO.getSystemPic())
                .build();
        save(systemMenu);
        return ResponseResultUtils.getResponseResultS("添加成功");
    }

    @Override
    public ResponseResult update(SystemMenuUpdateDTO systemMenuUpdateDTO) {
        final SystemMenu systemMenu = SystemMenu.builder()
                .id(systemMenuUpdateDTO.getId())
                .systemMenuName(systemMenuUpdateDTO.getSystemMenuName())
                .systemPath(systemMenuUpdateDTO.getSystemPath())
                .systemPic(systemMenuUpdateDTO.getSystemPic())
                .updateTime(DateUtil.toLocalDateTime(DateUtil.date()))
                .build();
        updateById(systemMenu);
        return ResponseResultUtils.getResponseResultS("修改成功");
    }

    @Override
    public ResponseResult delete(SystemMenuDeleteDTO systemMenuDeleteDTO) {
        final SystemMenu systemMenu = SystemMenu.builder()
                .id(systemMenuDeleteDTO.getId())
                .isDel(1)
                .build();
        updateById(systemMenu);
        return ResponseResultUtils.getResponseResultS("删除成功");
    }

    @Override
    public ResponseResult selectTreeData(SystemMenuSelectTreeDataDTO systemMenuSelectTreeDataDTO) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("system_menu_pid", systemMenuSelectTreeDataDTO.getSystemMenuPid());
        columnMap.put("is_del", 0);
        List<SystemMenu> systemMenus = listByMap(columnMap);

        List<SystemMenuSelectTreeDataVO> data = systemMenus.stream().map(x -> new SystemMenuSelectTreeDataVO(
                x.getSystemMenuName(),
                x.getId(),
                x.getSystemMenuPid()
        )).collect(Collectors.toList());
        return ResponseResultUtils.getResponseResultS("查询成功", data);
    }

    @Override
    public ResponseResult accredit(SystemMenuAccreditDTO systemMenuAccreditDTO) {
        final SystemMenu systemMenu = SystemMenu.builder()
                .updateTime(DateUtil.toLocalDateTime(DateUtil.date()))
                .systemPermissions(checkSystemPermissions(systemMenuAccreditDTO.getSystemPermissions()))
                .id(systemMenuAccreditDTO.getId())
                .build();
        updateById(systemMenu);
        return ResponseResultUtils.getResponseResultS("授权成功");
    }

    private String checkSystemPermissions(String systemPermissions){
        if (!systemPermissions.contains(":") && systemPermissions.split(":").length < 2) {
            throw new BaseRuntimeException(ResponseResultUtils.getResponseResultF("请按照[***:***]格式上传权限标识"));
        }
        return systemPermissions;
    }
}
