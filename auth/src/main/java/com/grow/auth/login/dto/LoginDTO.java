package com.grow.auth.login.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ApiModel(description  = "登录请求参数")
public class LoginDTO implements Serializable {

    @NotBlank
    @ApiModelProperty(value = "登录账号")
    private String username;

    @NotBlank
    @ApiModelProperty(value = "登录密码")
    private String password;

    private String code;

    private String uuid = "";
}
