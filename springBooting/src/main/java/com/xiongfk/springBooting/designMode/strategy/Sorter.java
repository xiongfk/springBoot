package com.xiongfk.springBooting.designMode.strategy;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/7/2
 * @Version 1.0
 **/
public class Sorter<T> {
    /**
     * 冒泡排序
     * @param array
     * @return
     */
    public void bubbleSort(T [] array,Comparator<T> comparator) {
        //外层循环控制循环次数
        for (int i = 0; i < array.length-1; i++){
            int minPos = i;
            //内层循环控制元素两两交换次数
            for (int j = i+1; j < array.length; j++){
                //比较相邻元素,进行位置调换
                minPos = comparator.compare(array[j],array[minPos]) == -1 ? j : minPos;
            }
            swap(array,i,minPos);
        }
    }

    public void swap(T [] array,int i,int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
