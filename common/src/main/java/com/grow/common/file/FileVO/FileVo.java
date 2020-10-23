package com.grow.common.file.FileVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/01 12:07
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "上传文件返回数据")
public class FileVo implements Serializable {

    @ApiModelProperty(value = "访问地址")
    private String url;

    @ApiModelProperty(value = "时间戳", example = "0")
    private Long currentTimeMillis;

    @ApiModelProperty(value = "保存至服务器目录")
    private String title;

    @ApiModelProperty(value = "源文件名")
    private String sourceTitle;
}
