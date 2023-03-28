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

    @Test
    public void userRegisterTest() {
        String userAccount = "";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        String vipCode = "123456";
        long result = userService.userRegister(userAccount, userPassword, checkPassword, vipCode);
        Assertions.assertEquals(-1, result);
        userAccount = "ffds_5t43t";
        userPassword = "123456";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword, vipCode);
        Assertions.assertEquals(-1, result);
        userPassword = "12345687";
        result = userService.userRegister(userAccount, userPassword, checkPassword, vipCode);
        Assertions.assertEquals(-1, result);
        userPassword = "12345678";
        userAccount = "emma719";
        result = userService.userRegister(userAccount, userPassword, checkPassword, vipCode);
        Assertions.assertEquals(-1, result);
        userAccount = "ffds_5t43t";
        result = userService.userRegister(userAccount, userPassword, checkPassword, vipCode);
        Assertions.assertTrue(result > 0);
    }

}