package com.idlab.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 * @author Emma
 */
@Data//lombok生成get set方法
public class UserRegisterRequest implements Serializable {
    public static final long serialVersionUID = 1;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
