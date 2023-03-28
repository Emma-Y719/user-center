package com.idlab.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 * @author Emma
 */
@Data
public class UserLoginRequest implements Serializable {
    public static final long serialVersionUID = 2;

    private String userAccount;
    private String userPassword;

}
