package com.xiongfk.springBooting.config.prop;//package com.example.xiongfk.config.prop;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 功能描述 TODO
// * 数据库配置
// * @Author xiongfk
// * @Date 2020/5/21
// * @Version 1.0
// **/
//@Component
//@ConfigurationProperties(prefix="spring.datasource.slaver")
//public class SlaverDbProperties {
//    private String driverClassName;
//
//    private String url;
//
//    private String username;
//
//    private String password;
//
//    public Map<String, Object> getProperties() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("driverClassName", this.getDriverClassName());
//        map.put("url", this.getUrl());
//        map.put("username", this.getUsername());
//        map.put("password", this.getPassword());
//        return map;
//    }
//
//    public String getDriverClassName() {
//        return driverClassName;
//    }
//
//    public void setDriverClassName(String driverClassName) {
//        this.driverClassName = driverClassName;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
