package com.idlab.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.idlab.usercenter.model.domain.User;
import com.idlab.usercenter.service.UserService;
import com.idlab.usercenter.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
* @author Emma
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-03-23 12:05:51
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1、校验输入数据
        if (StringUtils.isAllBlank(userAccount, userPassword, checkPassword)) {
            return -1;
        }
        if (userPassword.length() < 8) {
            return -1;
        }
        if (!checkPassword.equals(userPassword)) {
            return -1;
        }
        //账户只包含数字字母下划线
        if (!userAccount.matches("\\w{6,}")) {
            return -1;
        }
        //账户不重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        long count = this.count(queryWrapper);
        if (count > 0) {
            return -1;
        }
        //2、加密密码（重要！）
        final String SALT = "idlab";
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //3、插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1;
        }
        return user.getId();
    }
}




