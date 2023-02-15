package com.jason.basepro.base.dto;

import com.jason.basepro.base.entity.User;
import io.swagger.annotations.ApiModel;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户新增DTO")
public class UserAddDTO extends User {

    @Ignore
    private Integer id;

    @Ignore
    private Integer deleted;

    @Ignore
    private Integer createId;

    @Ignore
    private Integer updateId;

    @Ignore
    private LocalDateTime createTime;

    @Ignore
    private LocalDateTime updateTime;
}
