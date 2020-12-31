package com.xiongfk.springBooting.designMode.factory.AbstractFactory;

import com.xiongfk.springBooting.designMode.factory.Chair;
import com.xiongfk.springBooting.designMode.factory.Desk;

/**
 * FurnitureFactory
 * 接口描述 TODO
 * 抽象工厂
 * @Author xiongfk
 * @Date 2020/7/30
 * @Version 1.0
 **/
public interface FurnitureFactory {
    Desk productDesk();
    Chair productChair();
}
