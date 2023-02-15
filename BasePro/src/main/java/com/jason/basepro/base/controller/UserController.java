package com.jason.basepro.base.controller;


import com.jason.basepro.base.dto.UserAddDTO;
import com.jason.basepro.base.dto.UserDTO;
import com.jason.basepro.base.dto.UserLoginDTO;
import com.jason.basepro.base.entity.User;
import com.jason.basepro.base.service.IUserService;
import com.jason.basepro.base.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jason
 * @since 2023-02-08
 */
@RestController
@RequestMapping("/base/user")
public class UserController {

    @Resource
    private IUserService userService;

    @ApiOperation(value = "用户登陆")
    @PostMapping("login")
    public Object login(@RequestBody @Valid UserLoginDTO dto){
        return userService.login(dto);
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public Object add(@RequestBody @Valid UserAddDTO dto){
        return userService.save(dto);
    }

    @ApiOperation(value = "分页查询",response = User.class)
    @PostMapping("/page")
    public Object getList(@RequestBody @Valid UserDTO dto){
        return userService.page(dto);
    }


}
