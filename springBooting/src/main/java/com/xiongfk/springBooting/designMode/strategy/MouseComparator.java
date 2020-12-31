package com.xiongfk.springBooting.designMode.strategy;

public class MouseComparator implements Comparator<Mouse>{

    @Override
    public int compare(Mouse t1, Mouse t2) {
        int result = 0;
        if(t1.weight > t2.weight){
            result = 1;
        }else if(t1.weight < t2.weight){
            result = -1;
        }
        return result;
    }
}
