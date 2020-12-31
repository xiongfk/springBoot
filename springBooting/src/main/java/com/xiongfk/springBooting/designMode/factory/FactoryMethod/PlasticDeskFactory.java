package com.xiongfk.springBooting.designMode.factory.FactoryMethod;

import com.xiongfk.springBooting.designMode.factory.Desk;
import com.xiongfk.springBooting.designMode.factory.PlasticDesk;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/7/30
 * @Version 1.0
 **/
public class PlasticDeskFactory implements DeskFactory{

    @Override
    public Desk productDesk() {
        return new PlasticDesk();
    }
}
