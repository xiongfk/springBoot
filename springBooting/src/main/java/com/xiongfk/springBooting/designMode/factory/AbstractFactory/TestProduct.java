package com.xiongfk.springBooting.designMode.factory.AbstractFactory;

/**
 * 功能描述 TODO
 * 测试类
 * @Author xiongfk
 * @Date 2020/7/30
 * @Version 1.0
 **/
public class TestProduct {
    public static void main(String[] args) {
        FurnitureFactory furnitureFactory = new WoodenFurnitureFactory();
        System.out.println(furnitureFactory.productDesk().getDeskType());
        System.out.println(furnitureFactory.productChair().getChairType());
    }
}
