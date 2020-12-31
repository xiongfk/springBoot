package com.xiongfk.springBooting.designMode.factory;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/7/30
 * @Version 1.0
 **/
public class PlasticDesk implements Desk{

    @Override
    public String getDeskType() {
        String type = "塑料桌子";
        return type;
    }
}
