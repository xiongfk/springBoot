package com.xiongfk.springBooting.designMode.factory.AbstractFactory;

import com.xiongfk.springBooting.designMode.factory.Chair;
import com.xiongfk.springBooting.designMode.factory.Desk;
import com.xiongfk.springBooting.designMode.factory.PlasticChair;
import com.xiongfk.springBooting.designMode.factory.PlasticDesk;

/**
 * 功能描述 TODO
 * 抽象工程实现
 * @Author xiongfk
 * @Date 2020/7/30
 * @Version 1.0
 **/
public class PlasticFurnitureFactory implements FurnitureFactory {
    @Override
    public Desk productDesk() {
        return new PlasticDesk();
    }

    @Override
    public Chair productChair() {
        return new PlasticChair();
    }
}
