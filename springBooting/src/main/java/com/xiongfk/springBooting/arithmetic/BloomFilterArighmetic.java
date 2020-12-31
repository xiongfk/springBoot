package com.xiongfk.springBooting.arithmetic;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 功能描述 TODO
 * 布隆过滤器
 * @Author xiongfk
 * @Date 2020/6/30
 * @Version 1.0
 **/
public class BloomFilterArighmetic {
    private static int size = 1000000;//预计要插入多少数据

    private static double fpp = 0.01;//期望的误判率

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);

    public static void main(String[] args) {
//        //插入数据
//        for (int i = 0; i < 1000000; i++) {
//            bloomFilter.put(i);
//        }
//        int count = 0;
//        for (int i = 1000000; i < 2000000; i++) {
//            if (bloomFilter.mightContain(i)) {
//                count++;
//                System.out.println(i + "误判了");
//            }
//        }
        bloomFilter.put(1);
        bloomFilter.put(2);
        bloomFilter.put(3);
        bloomFilter.put(4);

        System.out.println("是否误判 : " + bloomFilter.mightContain(5));
    }
}
