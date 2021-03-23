package com.grow.auth.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.grow.auth.security.service.JwtPermissionService;
import com.grow.auth.system.dto.systemMenu.*;
import com.grow.auth.system.entity.SystemMenu;
import com.grow.auth.system.entity.SystemRoleUser;
import com.grow.auth.system.mapper.SystemMenuMapper;
import com.grow.auth.system.service.ISystemMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grow.auth.system.struct.SystemMenuStruct;
import com.grow.auth.system.vo.systemMenu.BuildSystemMenuMetaVO;
import com.grow.auth.system.vo.SystemMenuSelectTreeDataVO;
import com.grow.auth.system.vo.systemMenu.BuildSystemMenusVO;
import com.grow.auth.system.vo.systemMenu.SystemMenuVo;
import com.grow.auth.user.entity.UserInfoSecurity;
import com.grow.auth.user.service.UserExecuteService;
import com.grow.common.exception.BaseRuntimeErrHintException;
import com.grow.common.exception.BaseRuntimeException;
import com.grow.common.page.PageUtils;
import com.grow.common.page.ResultData;
import com.grow.common.result.ResponseResult;
import com.grow.common.result.ResponseResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统菜单
 *
 * @author XiFYuW
 * @since 2020-09-04
 */
@Service
@Slf4j
@Transactional
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements ISystemMenuService {

    private final UserExecuteService userExecuteService;
    private final JwtPermissionService jwtPermissionService;
    private final SystemMenuMapper systemMenuMapper;
    private final SystemMenuStruct systemMenuStruct;

    public SystemMenuServiceImpl(UserExecuteService userExecuteService,
                                 JwtPermissionService jwtPermissionService,
                                 SystemMenuMapper systemMenuMapper,
                                 SystemMenuStruct systemMenuStruct) {
        this.userExecuteService = userExecuteService;
        this.jwtPermissionService = jwtPermissionService;
        this.systemMenuMapper = systemMenuMapper;
        this.systemMenuStruct = systemMenuStruct;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseResult list(SystemMenuListDTO systemMenuListDTO) {
//        final Map<String, Object> data = PageUtils.getDateMapBack(() ->
//                systemMenuMapper.selectPage(
//                        PageUtils.getPage(
//                                systemMenuListDTO.getPage(),
//                                systemMenuListDTO.getSize()
//                        ),
//                        new LambdaQueryWrapper<SystemMenu>()
//                                .eq(SystemMenu::getSystemMenuPid, 0)
//                                .eq(SystemMenu::getIsDel, 0)
//                                .orderByAsc(SystemMenu::getSystemMenuSort)
//                ),
//                page -> systemMenuStruct.toSystemMenuVoList(page.getRecords())
//        );
        final ResultData<SystemMenuVo> data1 = PageUtils.getDateResultDataBack(() ->
                        systemMenuMapper.selectPage(
                                PageUtils.getPage(
                                        systemMenuListDTO.getPage(),
                                        systemMenuListDTO.getSize()
                                ),
                                new LambdaQueryWrapper<SystemMenu>()
                                        .eq(SystemMenu::getSystemMenuPid, 0)
                                        .eq(SystemMenu::getIsDel, 0)
                                        .orderByAsc(SystemMenu::getSystemMenuSort)
                        ),
                page -> systemMenuStruct.toSystemMenuVoList(page.getRecords())
        );
        return ResponseResultUtils.getResponseResultS("查询成功", data1);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseResult childrenList(SystemMenuChildrenListDTO systemMenuChildrenListDTO) {
        List<SystemMenu> data = systemMenuMapper.selectList(new LambdaQueryWrapper<SystemMenu>()
                .eq(SystemMenu::getSystemMenuPid, systemMenuChildrenListDTO.getPid())
                .eq(SystemMenu::getIsDel, 0)
                .orderByAsc(SystemMenu::getSystemMenuSort));
        return ResponseResultUtils.getResponseResultS("查询成功", systemMenuStruct.toSystemMenuVoList(data));
    }

    @Override
    public ResponseResult add(SystemMenuAddDTO systemMenuAddDTO) {
        if (systemMenuMapper.selectOne(new LambdaQueryWrapper<SystemMenu>()
                .eq(SystemMenu::getIsDel, 0)
                .eq(SystemMenu::getSystemMenuName, systemMenuAddDTO.getTitle())) != null) {
            throw new BaseRuntimeErrHintException("已存在该菜单名称");
        }
        if (StringUtils.isNotBlank(systemMenuAddDTO.getComponentName()) && systemMenuMapper.selectOne(new LambdaQueryWrapper<SystemMenu>()
                .eq(SystemMenu::getIsDel, 0)
                .eq(SystemMenu::getSystemComponentName, systemMenuAddDTO.getTitle())) != null) {
            throw new BaseRuntimeErrHintException("已存在该组件名称");
        }

        if (systemMenuAddDTO.getIFrame()) {
            String http = "http://", https = "https://";
            if (!(systemMenuAddDTO.getPath().toLowerCase().startsWith(http) || systemMenuAddDTO.getPath().toLowerCase().startsWith(https))) {
                throw new BaseRuntimeErrHintException("外链必须以http://或者https://开头");
            }
        }
        final SystemMenu systemMenu = SystemMenu.builder()
                .createTime(DateUtil.toLocalDateTime(DateUtil.date()))
                .isDel(0)
                .iFrame(systemMenuAddDTO.getIFrame())
                .hidden(systemMenuAddDTO.getHidden())
                .isCache(systemMenuAddDTO.getCache())
                .systemMenuPid(systemMenuAddDTO.getPid())
                .systemComponent(systemMenuAddDTO.getComponent())
                .systemComponentName(systemMenuAddDTO.getComponentName())
                .systemMenuType(systemMenuAddDTO.getType())
                .systemMenuName(systemMenuAddDTO.getTitle())
                .systemMenuSort(systemMenuAddDTO.getMenuSort())
                .systemPath(systemMenuAddDTO.getPath())
                .systemPermissions(systemMenuAddDTO.getPermission())
                .systemPic(systemMenuAddDTO.getIcon())
                .subCount(0)
                .build();
        save(systemMenu);
        // 更新父节点菜单数目
        updateSubCnt(systemMenuAddDTO.getPid());
        return ResponseResultUtils.getResponseResultS("添加成功");
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseResult superior(List<Long> ids) {
        Set<SystemMenu> systemMenuSet = new LinkedHashSet<>();
        if(CollectionUtil.isNotEmpty(ids)){
            for (Long id : ids) {
                SystemMenu systemMenu = this.getBaseMapper().selectById(id);
                systemMenuSet.addAll(getSuperiorChildren(systemMenu, new ArrayList<>()));
            }
            return ResponseResultUtils.getResponseResultDataS(systemMenuStruct.toSystemMenuVoList(this.buildTree(new ArrayList<>(systemMenuSet))));
        }
        SystemMenuChildrenListDTO systemMenuChildrenListDTO = new SystemMenuChildrenListDTO(0);
        return this.childrenList(systemMenuChildrenListDTO);
    }

    private List<SystemMenu> getSuperiorChildren(SystemMenu systemMenu, List<SystemMenu> menus) {
        if(systemMenu.getSystemMenuPid() == 0){
            menus.addAll(this.getBaseMapper().selectList(new LambdaQueryWrapper<SystemMenu>()
                    .eq(SystemMenu::getIsDel, 0)
                    .eq(SystemMenu::getSystemMenuPid, 0)
            ));
            return menus;
        }
        menus.addAll(this.getBaseMapper().selectList(new LambdaQueryWrapper<SystemMenu>()
                .eq(SystemMenu::getIsDel, 0)
                .eq(SystemMenu::getSystemMenuPid, systemMenu.getSystemMenuPid())
        ));
        return getSuperiorChildren(this.getBaseMapper().selectById(systemMenu.getSystemMenuPid()), menus);
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

    @Override
    public ResponseResult structure() {
        final UserInfoSecurity userInfoSecurity = userExecuteService.getUserInfo();
        List<SystemRoleUser> systemRoleUserList = (List<SystemRoleUser>) jwtPermissionService.getSystemRoleUser(userInfoSecurity.getUserId());
        Set<Integer> systemRoleMenuSet = jwtPermissionService.getSystemRoleMenuId(systemRoleUserList);
        List<SystemMenu> menus = systemRoleMenuSet.stream()
                .filter(x -> !x.equals(0))
                .map(x -> systemMenuMapper.selectOne(new LambdaQueryWrapper<SystemMenu>()
                        .eq(SystemMenu::getId, x)
                        .ne(SystemMenu::getSystemMenuType, 2)
                        .eq(SystemMenu::getIsDel, 0))
                )
                .filter(x -> Optional.ofNullable(x).isPresent())
                .collect(Collectors.toList());
        List<SystemMenu> systemMenuTrees = buildTree(menus);
        List<BuildSystemMenusVO> buildSystemMenusVOList = buildMenus(systemMenuTrees);
        return ResponseResultUtils.getResponseResultS("查询成功", buildSystemMenusVOList);
    }

    public List<BuildSystemMenusVO> buildMenus(List<SystemMenu> menuDtos) {
        List<BuildSystemMenusVO> list = new LinkedList<>();
        menuDtos.forEach(menuDTO -> {
            if (menuDTO != null) {
                List<SystemMenu> menuDtoList = menuDTO.getChildren();
                BuildSystemMenusVO menuVo = new BuildSystemMenusVO();
                menuVo.setName(ObjectUtil.isNotEmpty(menuDTO.getSystemComponentName()) ? menuDTO.getSystemComponentName() : menuDTO.getSystemMenuName());
                // 一级目录需要加斜杠，不然会报警告
                menuVo.setPath(menuDTO.getSystemMenuPid() == 0 ? "/" + menuDTO.getSystemPath() : menuDTO.getSystemPath());
                menuVo.setHidden(menuDTO.getHidden());
                // 如果不是外链
                if (!menuDTO.getIFrame()) {
                    if (menuDTO.getSystemMenuPid() == 0) {
                        menuVo.setComponent(StrUtil.isEmpty(menuDTO.getSystemComponent()) ? "Layout" : menuDTO.getSystemComponent());
                    } else if (!StrUtil.isEmpty(menuDTO.getSystemComponent())) {
                        menuVo.setComponent(menuDTO.getSystemComponent());
                    }
                }
                menuVo.setMeta(new BuildSystemMenuMetaVO(menuDTO.getSystemMenuName(), menuDTO.getSystemPic(), !menuDTO.getIsCache()));
                if (menuDtoList != null && menuDtoList.size() != 0) {
                    menuVo.setAlwaysShow(true);
                    menuVo.setRedirect("noredirect");
                    menuVo.setChildren(buildMenus(menuDtoList));
                    // 处理是一级菜单并且没有子菜单的情况
                } else if (menuDTO.getSystemMenuPid() == 0) {
                    BuildSystemMenusVO menuVo1 = new BuildSystemMenusVO();
                    menuVo1.setMeta(menuVo.getMeta());
                    // 非外链
                    if (!menuDTO.getIFrame()) {
                        menuVo1.setPath("index");
                        menuVo1.setName(menuVo.getName());
                        menuVo1.setComponent(menuVo.getComponent());
                    } else {
                        menuVo1.setPath(menuDTO.getSystemPath());
                    }
                    menuVo.setName(null);
                    menuVo.setMeta(null);
                    menuVo.setComponent("Layout");
                    List<BuildSystemMenusVO> list1 = new ArrayList<>();
                    list1.add(menuVo1);
                    menuVo.setChildren(list1);
                }
                list.add(menuVo);
            }
        });
        return list;
    }

    public List<SystemMenu> buildTree(List<SystemMenu> menus) {
        List<SystemMenu> trees = new ArrayList<>();
        Set<Integer> ids = new HashSet<>();
        for (SystemMenu menu : menus) {
            if (menu.getSystemMenuPid() == 0) {
                trees.add(menu);
            }
            for (SystemMenu it : menus) {

                if (menu.getId().equals(it.getSystemMenuPid())) {
                    if (menu.getChildren() == null) {
                        menu.setChildren(new ArrayList<>());
                    }
                    menu.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        if (trees.size() == 0) {
            trees = menus.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        return trees;
    }

    private String checkSystemPermissions(String systemPermissions) {
        if (!systemPermissions.contains(":") && systemPermissions.split(":").length < 2) {
            throw new BaseRuntimeException(ResponseResultUtils.getResponseResultF("请按照[***:***]格式上传权限标识"));
        }
        return systemPermissions;
    }

    private synchronized void updateSubCnt(Integer pid){
        if (pid != 0) {
            SystemMenu systemMenu = systemMenuMapper.selectOne(new LambdaQueryWrapper<SystemMenu>()
                    .select(SystemMenu::getSubCount, SystemMenu::getId)
                    .eq(SystemMenu::getId, pid)
            );
            if (systemMenu != null) {
                systemMenu.setSubCount(systemMenu.getSubCount() + 1);
                systemMenuMapper.updateById(systemMenu);
            }
        }
    }
}
