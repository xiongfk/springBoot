package com.xiongfk.elasticSearch.model;

import com.xiongfk.elasticSearch.enums.ResponseEnums;

import java.io.Serializable;

public class ResponseBean<T> implements Serializable {



    private int code;
    private String msg;
    private T data;

    public ResponseBean(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public ResponseBean(ResponseEnums enums){
        this.code=enums.getCode();
        this.msg=enums.getMsg();
    }
    public ResponseBean(T data, ResponseEnums enums){
        this.data=data;
        this.code=enums.getCode();
        this.msg=enums.getMsg();
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    }
