package com.idlab.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.idlab.usercenter.model.domain.User;
import com.idlab.usercenter.service.UserService;
import com.idlab.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Emma
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-03-23 12:05:51
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




