package com.xiongfk.springBooting.controller;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/2/20
 * @Version 1.0
 **/
public class ConvertDTO {
    private String phone;
    private String name;
    private String cardCode;
    private String arrive_time;
    private String arrive_text;
    private String source;
    private String token;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    public String getArrive_text() {
        return arrive_text;
    }

    public void setArrive_text(String arrive_text) {
        this.arrive_text = arrive_text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
