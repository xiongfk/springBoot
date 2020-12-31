package com.xiongfk.springBooting.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Account
 * 类描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/7/14 10:40
 * @Version 1.0
 **/
public class Account {
    private int id;
    private String name;
    private double money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public static void main(String[] args) {
        String columnName = "codeContent";
    }

    private static List<String> upper(String columnNames[]){
        List<String> lists = new ArrayList<>();
        for (String columnName : columnNames) {
            char[] c = columnName.toCharArray();
            List<String> list = new ArrayList<>();
            String str = "";
            int i1 = 0;
            String newColumn = "";
            for(int i = 0;i < columnName.length();i++){
                if(c[i] >= 'A' && c[i] <= 'Z'){
                    i1 = columnName.indexOf(c[i]);
                    str = columnName.substring(0,i1);
                    list.add(str);
                    str = columnName.substring(i1);
                    list.add(str);
                    String substring = list.toString().substring(1, list.toString().length() - 1);
                    String[] split = substring.split(",");
                    newColumn = split[0].concat("_").concat(split[1].trim().toLowerCase()).trim();
                    lists.add(newColumn);
                }
            }
        }
        return lists;
    }
}