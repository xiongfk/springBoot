package com.xiongfk.springBooting.designMode.factory.AbstractFactory;

import com.xiongfk.springBooting.designMode.factory.Chair;
import com.xiongfk.springBooting.designMode.factory.Desk;
import com.xiongfk.springBooting.designMode.factory.WoodenChair;
import com.xiongfk.springBooting.designMode.factory.WoodenDesk;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/7/30
 * @Version 1.0
 **/
public class WoodenFurnitureFactory implements FurnitureFactory {
    @Override
    public Desk productDesk() {
        return new WoodenDesk();
    }

    @Override
    public Chair productChair() {
        return new WoodenChair();
    }
}
