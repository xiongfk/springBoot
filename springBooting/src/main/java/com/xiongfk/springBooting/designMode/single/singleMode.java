package com.xiongfk.springBooting.designMode.single;

/**
 * 功能描述 TODO 线程安全,缺点使用synchronized,并且进行双重校验效率较差
 * 单例模式 之 懒汉模式该模式的特点是类加载时没有生成单例，
 * 只有当第一次调用 getInstance 方法时才去创建这个单例
 * @Author xiongfk
 * @Date 2020/5/6
 * @Version 1.0
 **/
public class singleMode {
    //保证 instance 在所有线程中同步
    private static volatile singleMode instance = null;

    //private 避免类在外部被实例化 私有化构造方法
    private singleMode(){}

    //双重校验锁
    public static singleMode getInstance() {
        //第一次校验
        if(instance == null){
            synchronized(singleMode.class){
                //第二次校验
                if(instance == null){
                    instance = new singleMode();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        new Thread(()-> {
            for (int t = 0; t < 100; t++) {
                System.out.println(singleMode.getInstance().hashCode());
            }
        }).start();
    }
}
