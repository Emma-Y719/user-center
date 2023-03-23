package com.idlab.usercenter.service;

import com.idlab.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Emma
* @description 针对表【user】的数据库操作Service
* @createDate 2023-03-23 12:05:51
*/
public interface UserService extends IService<User> {
    /**
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 用户ID
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);
}
