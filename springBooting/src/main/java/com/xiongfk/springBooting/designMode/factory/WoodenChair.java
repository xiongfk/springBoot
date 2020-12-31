package com.xiongfk.springBooting.designMode.factory;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/7/30
 * @Version 1.0
 **/
public class WoodenChair implements Chair{

    @Override
    public String getChairType() {
        String type = "木质椅子";
        return type;
    }
}
