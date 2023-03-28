package com.idlab.usercenter.service;

import com.idlab.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author Emma
* @description 针对表【user】的数据库操作Service
* @createDate 2023-03-23 12:05:51
*/
public interface UserService extends IService<User> {
    /**
     *用户注册功能
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 用户ID
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录功能
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param request 请求
     * @return 脱敏的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户注销
     * @param request 请求
     * @return 1-注销成功
     */
    int userLogout(HttpServletRequest request);

    /**
     * 用户查询
     *
     * @param username 用户名称
     * @return 满足条件的用户
     */
    List<User> searchUser(String username);

    /**
     * 用户信息脱敏
     * @param originalUser 用户
     * @return 脱敏后的用户
     */
    User getSafetyUser(User originalUser);
}
