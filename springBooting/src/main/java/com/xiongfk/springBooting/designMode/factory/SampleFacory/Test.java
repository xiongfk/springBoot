package com.xiongfk.springBooting.designMode.factory.SampleFacory;

import com.xiongfk.springBooting.designMode.factory.Desk;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/7/30
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        Desk desk = DeskFactory.productDesk(Type.WOODEN);
        System.out.println(desk.getDeskType());
    }
}
