package com.xiongfk.springBooting.designMode.single;

/**
 * 功能描述 TODO
 * 匿名内部类实现单例线程安全,且是懒汉加载
 * jvm对一个类的初始化会做限制，同一时间只会允许一个线程去初始化一个类，
 * 这样就从虚拟机层面避免了大部分单例实现的问题
 * @Author xiongfk
 * @Date 2020/6/28
 * @Version 1.0
 **/
public class singleModeAnonymity {

    private singleModeAnonymity(){}

    private static class SingletonHolder{
        private static final singleModeAnonymity INSTANCE = new singleModeAnonymity();
    }

    public static singleModeAnonymity getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        new Thread(()-> {
            for (int t = 0; t < 100; t++) {
                System.out.println(singleModeAnonymity.getInstance().hashCode());
            }
        }).start();
    }
}