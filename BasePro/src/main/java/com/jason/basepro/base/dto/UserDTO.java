package com.jason.basepro.base.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.jason.basepro.common.entity.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户DTO")
public class UserDTO extends PageDTO {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "性别 0-未知 1-男 2-女")
    private Integer sex;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String mailbox;

    @ApiModelProperty(value = "状态，1启用，0禁用")
    private Integer status;
}
