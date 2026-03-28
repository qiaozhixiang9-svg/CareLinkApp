package com.carelink.app.data.remote.dto;

/** 统一服务端响应包装 */
public class BaseResponse<T> {
    private int code;
    private String message;
    private T data;

    public boolean isSuccess() { return code == 200; }
    public int getCode() { return code; }
    public String getMessage() { return message; }
    public T getData() { return data; }
}
