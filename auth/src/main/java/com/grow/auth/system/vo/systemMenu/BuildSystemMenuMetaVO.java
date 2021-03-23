package com.grow.auth.system.vo.systemMenu;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class BuildSystemMenuMetaVO implements Serializable {
    private String title;

    private String icon;

    private Boolean noCache;
}
