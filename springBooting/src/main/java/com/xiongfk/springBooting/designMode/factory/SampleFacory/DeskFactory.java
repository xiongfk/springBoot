package com.xiongfk.springBooting.designMode.factory.SampleFacory;

import com.xiongfk.springBooting.designMode.factory.Desk;
import com.xiongfk.springBooting.designMode.factory.PlasticDesk;
import com.xiongfk.springBooting.designMode.factory.WoodenDesk;

/**
 * 功能描述 TODO
 * 简单工厂模式
 * @Author xiongfk
 * @Date 2020/7/30
 * @Version 1.0
 **/
public class DeskFactory {
    public static Desk productDesk(Type type){
        switch (type){
            case WOODEN:
                return new WoodenDesk();
            case PLASTIC:
                return new PlasticDesk();
            default:
                return null;
        }
    }
}
