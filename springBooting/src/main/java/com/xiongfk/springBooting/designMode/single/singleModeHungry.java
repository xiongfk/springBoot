package com.xiongfk.springBooting.designMode.single;

/**
 * 功能描述 TODO
 * 线程安全,缺点:无论是否需要使用都会进行初始化
 * @Author xiongfk
 * @Date 2020/6/28
 * @Version 1.0
 **/
public class singleModeHungry {
    private static singleModeHungry singleModeHungry = new singleModeHungry();

    private singleModeHungry(){}

    public static singleModeHungry getInstance(){
        return singleModeHungry;
    }

    public static void main(String[] args) {
        new Thread(()-> {
            for (int t = 0; t < 100; t++) {
                System.out.println(singleModeHungry.getInstance().hashCode());
            }
        }).start();
    }
}
