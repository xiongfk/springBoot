package com.xiongfk.springBooting.designMode.factory.FactoryMethod;

import com.xiongfk.springBooting.designMode.factory.Chair;
import com.xiongfk.springBooting.designMode.factory.PlasticChair;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/7/30
 * @Version 1.0
 **/
public class PlasticChairFactory implements ChairFactory{

    @Override
    public Chair productChair() {
        return new PlasticChair();
    }
}
