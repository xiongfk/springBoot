package com.xiongfk.springBooting.designMode.factory.FactoryMethod;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/7/30
 * @Version 1.0
 **/
public class TestFactory {
    public static void main(String[] args) {
        DeskFactory deskFactory = new PlasticDeskFactory();
        String deskType = deskFactory.productDesk().getDeskType();
        System.out.println(deskType);

        ChairFactory chairFactory = new PlasticChairFactory();
        String chairType = chairFactory.productChair().getChairType();
        System.out.println(chairType);
    }
}
