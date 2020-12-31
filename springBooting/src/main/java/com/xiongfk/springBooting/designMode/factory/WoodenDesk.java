package com.xiongfk.springBooting.designMode.factory;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/7/30
 * @Version 1.0
 **/
public class WoodenDesk implements Desk {

    @Override
    public String getDeskType() {
        String type = "木质桌子";
        return type;
    }
}
