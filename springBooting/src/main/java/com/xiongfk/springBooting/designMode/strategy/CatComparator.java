package com.xiongfk.springBooting.designMode.strategy;

/**
 * 功能描述 TODO
 * 猫的实体类
 * @Author xiongfk
 * @Date 2020/7/2
 * @Version 1.0
 **/
public class CatComparator implements Comparator<Cat>{

    @Override
    public int compare(Cat t1, Cat t2) {
        int result = 0;
        if(t1.weight > t2.weight){
            result = 1;
        }else if(t1.weight < t2.weight){
            result = -1;
        }
        return result;
    }
}
