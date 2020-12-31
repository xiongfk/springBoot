package com.xiongfk.springBooting.designMode.strategy;

import java.util.Arrays;

/**
 * 功能描述 TODO
 * 猫的实体类
 * @Author xiongfk
 * @Date 2020/7/2
 * @Version 1.0
 **/
public class Cat{

    int weight;

    public Cat(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Cat{" + "weight=" + weight + '}';
    }

    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        Cat cat [] = {new Cat(3),new Cat(5),new Cat(1)};
        sorter.bubbleSort(cat,new CatComparator());
        System.out.println(Arrays.toString(cat));
    }
}
