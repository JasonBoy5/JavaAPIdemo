package com.jason.basepro.base.service;

import com.jason.basepro.base.dto.UserAddDTO;
import com.jason.basepro.base.dto.UserDTO;
import com.jason.basepro.base.dto.UserLoginDTO;
import com.jason.basepro.base.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author jason
 * @since 2023-02-08
 */
public interface IUserService extends IService<User> {

    Object login(UserLoginDTO dto);

    Object save(UserAddDTO dto);

    Object page(UserDTO dto);
}
