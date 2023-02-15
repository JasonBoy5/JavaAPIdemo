package com.jason.basepro.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.basepro.base.dto.UserAddDTO;
import com.jason.basepro.base.dto.UserDTO;
import com.jason.basepro.base.dto.UserLoginDTO;
import com.jason.basepro.base.entity.User;
import com.jason.basepro.base.mapper.UserMapper;
import com.jason.basepro.base.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.basepro.base.vo.UserVO;
import com.jason.basepro.common.constant.StatusConst;
import com.jason.basepro.common.entity.Result;
import com.jason.basepro.common.filter.TokenService;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jason
 * @since 2023-02-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private TokenService tokenService;

    @Override
    public Object login(UserLoginDTO dto) {
        User user = lambdaQuery().eq(User::getName,dto.getName()).eq(User::getPassword,dto.getPassword()).one();
        if(null == user){
            return Result.fail(HttpStatus.UNAUTHORIZED.value(),"账号或密码错误");
        }else {
            return tokenService.createToken(user);
        }
    }

    @Override
    public Object save(UserAddDTO dto) {
        User user = new User();
        BeanUtils.copyProperties(dto,user);
        return save(user);
    }

    @Override
    public Object page(UserDTO dto) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();

        qw.like(StringUtils.isNotBlank(dto.getName()),User::getName,dto.getName())
                .like(StringUtils.isNotBlank(dto.getPhone()),User::getPhone,dto.getPhone())
                .like(StringUtils.isNotBlank(dto.getMailbox()),User::getMailbox,dto.getMailbox())
                .eq(null != dto.getSex(),User::getSex,dto.getSex())
                .eq(null != dto.getStatus(),User::getStatus,dto.getStatus())
                .eq(User::getDeleted, StatusConst.NO_DELETED);

        Page<User> pageList = page(new Page<>(dto.getPageNum(),dto.getPageSize()),qw);
        pageList.getRecords().forEach(x->x.setPassword(null));
        return pageList;
    }
}
