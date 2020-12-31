package com.xiongfk.springBooting.designMode.decorator;

/**
 * 功能描述 TODO
 * @Author xiongfk
 * @Date 2020/8/6
 * @Version 1.0
 **/
public class ClienterTest {
    public static void main(String[] args) {
        House zhangSan = new ZhangSanHouseImpl();
        House decorator = new Decorator(zhangSan);
        decorator.output();
    }
}
