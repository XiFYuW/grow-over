package com.grow.auth.system.vo.systemMenu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BuildSystemMenusVO implements Serializable {
    private String name;

    private String path;

    private Boolean hidden;

    private String redirect;

    private String component;

    private Boolean alwaysShow;

    private BuildSystemMenuMetaVO meta;

    private List<BuildSystemMenusVO> children;
}
