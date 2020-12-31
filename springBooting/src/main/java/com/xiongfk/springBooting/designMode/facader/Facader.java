package com.xiongfk.springBooting.designMode.facader;

/**
 * 功能描述 TODO
 * 外观类
 * @Author xiongfk
 * @Date 2020/8/5
 * @Version 1.0
 **/
public class Facader {
    private SubMethod01 subMethod01 = new SubMethod01();
    private SubMethod02 subMethod02 = new SubMethod02();
    private SubMethod03 subMethod03 = new SubMethod03();

    public void facMethod1(){
        subMethod01.method();
        subMethod02.method();
    }

    public void facMethod2(){
        subMethod01.method();
        subMethod02.method();
        subMethod03.method();
    }
}
