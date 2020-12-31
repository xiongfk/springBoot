package com.xiongfk.springBooting.model;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * 功能描述 TODO
 * 自定义异常实体
 * @Author xiongfk
 * @Date 2019/10/24
 * @Version 1.0
 **/
@Validated
public class CustomExceptionDTO {
    @NotEmpty(message = "编码不能为空")
    private int code;//异常编码
    @NotEmpty(message = "信息不能为空")
    private String message;//异常信息
    public CustomExceptionDTO(){

    }
    public CustomExceptionDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void main(String[] args) {
        CustomExceptionDTO dto = new CustomExceptionDTO();
        dto.setCode(1);
        System.out.println(dto.getCode());
    }
}
