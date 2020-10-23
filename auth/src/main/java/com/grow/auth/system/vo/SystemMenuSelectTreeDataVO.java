package com.grow.auth.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/05 11:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemMenuSelectTreeDataVO implements Serializable {

    private String systemMenuName;

    private Long id;

    private Long systemMenuPid;
}
