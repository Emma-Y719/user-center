package com.idlab.usercenter.service;
import java.util.Date;

import com.idlab.usercenter.model.domain.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * 用户服务测试
 * @author Emma
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAdduser(){
        User user = new User();
        user.setUsername("");
        user.setUserAccount("");
        user.setAvatarUrl("");
        user.setUserPassword("");
        user.setPhone("");
        user.setEmail("");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        boolean result = userService.save(user);
        Assertions.assertTrue(result);
        System.out.println(user.getId());


    }
}