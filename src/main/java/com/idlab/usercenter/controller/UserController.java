package com.idlab.usercenter.controller;


import com.idlab.usercenter.common.BaseResponse;
import com.idlab.usercenter.common.ResultUtils;
import com.idlab.usercenter.model.domain.User;
import com.idlab.usercenter.model.request.UserLoginRequest;
import com.idlab.usercenter.model.request.UserRegisterRequest;
import com.idlab.usercenter.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.idlab.usercenter.constant.UserConstant.ADMIN_ROLE;
import static com.idlab.usercenter.constant.UserConstant.USER_LOGIN_STATUS;


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
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String vipCode = userRegisterRequest.getVipCode();
        if (StringUtils.isAllBlank(userAccount, userPassword, checkPassword, vipCode)) {
            return null;
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword, vipCode);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAllBlank(userAccount, userPassword)) {
            return null;
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(USER_LOGIN_STATUS);
        if (currentUser == null) {
            return null;
        }
        //更新user的信息
        User renewedUser = userService.getById(currentUser.getId());
        User safetyUser = userService.getSafetyUser(renewedUser);
        return ResultUtils.success(safetyUser);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        //用户鉴权 仅管理员可查
        if (!isAdmin(request)) {
            return null;
        }
        List<User> users = userService.searchUser(username);
        return ResultUtils.success(users);
    }

    @PostMapping ("/delete")
    public BaseResponse<Boolean> deleteUsers(long id, HttpServletRequest request) {
        //用户鉴权 仅管理员可查
        if (!isAdmin(request)) {
            return null;
        }
        if (id <= 0) {
            return null;
        }
        boolean b = userService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 判断是否为管理员
     * @param request 请求
     * @return 是否为管理员
     */
    private boolean isAdmin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATUS);
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }
}
