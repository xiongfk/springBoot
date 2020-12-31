package com.xiongfk.springBooting.utils;

import java.util.*;

/**
 * CollectionUtils
 * 功能描述 TODO
 * 集合操作工具类
 * @Author xiongfk
 * @Date 2019/7/14 21:31
 * @Version 1.0
 **/
public class CollectionUtils {

    public static void hashMap(){
        Map<String,Object> hashMap = new HashMap<>();
        String key = "name" ;
        hashMap.put(key,"xiongfk");
        hashMap.get(key);
        hashMap.containsKey(key);
        hashMap.remove(key);
        hashMap.replace(key,"");
    }

    public static void treeMap(){
        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.put("","");
    }

    public static void hashSet(){
        Set<String> hashSet = new HashSet<>();
        hashSet.add("");
    }

    public static void treeSet(){
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("");
    }

    public static void arrayList(){
        List<String> arrayList = new ArrayList<>();
        int index = 0;
        arrayList.add("xiongfk");
        arrayList.remove(index);
        arrayList.set(index,"name");
        arrayList.get(index);
    }

    public static void linkedList(){
        List<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("3");
        linkedList.add("5");
        int index = 1;
        linkedList.remove(index);
        linkedList.set(index,"2");
        linkedList.get(index);
    }

    public static void main(String[] args) {
//        int n = 2;
//        int hash = 2;
//        int t = n & (hash-1);
//        System.out.println(t);
        hashMap();
    }
}
