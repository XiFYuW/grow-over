package com.grow.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页码", example = "1")
    @NotNull(message = "页码不能为空")
    private Long page;

    @ApiModelProperty(value = "每页大少", example = "10")
    @NotNull(message = "每页大少不能为空")
    private Long size;
}
