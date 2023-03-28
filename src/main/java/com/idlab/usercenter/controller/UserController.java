package com.idlab.usercenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idlab.usercenter.model.domain.User;
import com.idlab.usercenter.model.request.UserLoginRequest;
import com.idlab.usercenter.model.request.UserRegisterRequest;
import com.idlab.usercenter.service.UserService;
import java.util.Collections;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 用户接口
 * @author Emma
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAllBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAllBlank(userAccount, userPassword)) {
            return null;
        }
        return userService.userLogin(userAccount, userPassword, request);
    }
    @GetMapping("/search")
    public List<User> searchUsers(String username) {
        //用户鉴权 仅管理员可查
        if (StringUtils.isNotBlank(username)) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("username", username);
            return userService.list(queryWrapper);
        }
        return Collections.emptyList();
    }
    @GetMapping("/delete")
    public boolean deleteUsers(long id) {
        if (id <= 0) {
            return false;
        }
        return userService.removeById(id);
    }
}
