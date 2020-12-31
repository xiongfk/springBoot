package com.xiongfk.springBooting.enums;

public enum ResponseEnums {

    SUCCESS(0,"成功"),
    SYSTEM_ERROR(-1,"服务不可用"),
    BAD_REQUEST(-2,"错误的请求参数"),
    NOT_FOUND(-3,"找不到请求路径"),
    METHOD_NOT_ALLOWED(-4,"REQ METHOD NOT ALLOWED"),
    NO_PERMISSION(1001,"NO PERMISSION"),
    RECEIVED(98,"已接收"),
    FAIL(99,"失败");

    private int code;
    private String msg;

    private ResponseEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

}
