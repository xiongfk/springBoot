package com.xiongfk.springBooting.customAnnotation;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/5/18
 * @Version 1.0
 **/
public class DemoAnnotation {

    @MyAutoWired
    public String return01(String param){
        System.out.println("==============" + param);
        return param;
    }

    @MyAnnotation()
    public String return02(String param){
        System.out.println("hello world");
        return param;
    }
}
