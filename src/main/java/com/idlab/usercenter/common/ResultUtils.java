package com.idlab.usercenter.common;

import com.idlab.usercenter.exception.BusinessException;

/**
 * 返回结果封装工具
 * @author Emma
 */
public class ResultUtils {
    /**
     * 成功
     * @param data 结果数据
     * @return 封装结果
     * @param <T> 任意类型
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     * @param errorCode 错误码
     * @return 封装结果
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse(errorCode);
    }

    public static BaseResponse error(ErrorCode errorCode, String description) {
        return new BaseResponse(errorCode,description);
    }
    public static BaseResponse error(BusinessException e) {
        return new BaseResponse(e);
    }
}
