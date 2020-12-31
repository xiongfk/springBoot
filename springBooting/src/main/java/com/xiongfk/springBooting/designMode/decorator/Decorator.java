package com.xiongfk.springBooting.designMode.decorator;

import com.xiongfk.springBooting.base.BaseCommonLog;

/**
 * 功能描述 TODO 装饰器：Decorator
 * @Author xiongfk
 * @Date 2020/8/6
 * @Version 1.0
 **/
public class Decorator implements House {
    private House house;

    public Decorator(House house){
        this.house = house;
    }

    @Override
    public void output() {
        BaseCommonLog.logger.info("这是针对房子的前段装饰增强");
        house.output();
        BaseCommonLog.logger.info("这是针对房子的前段装饰增强");
    }
}
