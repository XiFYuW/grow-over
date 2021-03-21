package com.grow.auth.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class SystemMenuMetaVO implements Serializable {
    private String title;

    private String icon;

    private Boolean noCache;
}
