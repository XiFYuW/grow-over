package com.grow.auth.system.struct;

import com.grow.auth.system.entity.SystemMenu;
import com.grow.auth.system.vo.systemMenu.SystemMenuVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SystemMenuStruct {

    @Mappings({
            @Mapping(source = "systemMenuType", target = "type"),
            @Mapping(source = "systemPermissions", target = "permission"),
            @Mapping(source = "systemMenuName", target = "title"),
            @Mapping(source = "systemMenuSort", target = "menuSort"),
            @Mapping(source = "systemPath", target = "path"),
            @Mapping(source = "systemComponent", target = "component"),
            @Mapping(source = "systemMenuPid", target = "pid"),
            @Mapping(source = "isCache", target = "cache"),
            @Mapping(source = "systemComponentName", target = "componentName"),
            @Mapping(source = "systemPic", target = "icon"),
    })
    SystemMenuVo toSystemMenuVo(SystemMenu systemMenu);

    List<SystemMenuVo> toSystemMenuVoList(List<SystemMenu> systemMenu);
}
