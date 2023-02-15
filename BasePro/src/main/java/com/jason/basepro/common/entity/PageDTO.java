package com.jason.basepro.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询父类")
public class PageDTO {


    @ApiModelProperty(value = "当前第几页",example = "1")
    private Integer pageNum=1;

    @ApiModelProperty(value = "每页数量",example = "15")
    private Integer pageSize=15;
}
