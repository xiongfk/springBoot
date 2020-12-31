package com.xiongfk.springBooting.designMode.strategy;

import java.util.Arrays;

/**
 * 功能描述 TODO
 * 老鼠实体
 * @Author xiongfk
 * @Date 2020/7/2
 * @Version 1.0
 **/
public class Mouse{

    int weight;

    public Mouse(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Mouse{" + "weight=" + weight + '}';
    }

     public static void main(String[] args) {
        Sorter sorter = new Sorter();
        Mouse mouse [] = {new Mouse(1),new Mouse(3),new Mouse(2)};
        sorter.bubbleSort(mouse,new MouseComparator());
        System.out.println(Arrays.toString(mouse));
    }
}
