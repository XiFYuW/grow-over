package com.grow.auth.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/26 14:56
 */
@TableName("user_login_record")
@Data
@Builder
public class UserLoginRecordSecurity implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String userId;

    private String loginIp;

    private String loginLocation;

    private String loginBrowser;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime loginTime;

    private Integer isDel;
}
