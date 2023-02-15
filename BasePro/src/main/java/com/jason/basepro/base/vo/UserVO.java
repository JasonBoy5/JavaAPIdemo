package com.jason.basepro.base.vo;

import com.jason.basepro.base.entity.User;
import io.swagger.annotations.ApiModel;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户VO")
public class UserVO extends User {

    @Ignore
    private String password;
}
