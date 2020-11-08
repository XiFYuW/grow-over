package com.grow.auth.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/26 14:56
 */
@TableName("user_login")
@Data
public class UserLoginSecurity implements Serializable {

    @JsonIgnore
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String userName;

    @JsonIgnore
    private String password;

    @Pattern(regexp = "([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}",message = "格式错误")
    private String email;

    @JsonIgnore
    private Integer isExpired;

    @JsonIgnore
    private Integer isLocked;

    @JsonIgnore
    private Integer isEnabled;

}
