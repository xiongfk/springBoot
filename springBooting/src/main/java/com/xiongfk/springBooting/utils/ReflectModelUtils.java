package com.xiongfk.springBooting.utils;

import com.alibaba.fastjson.JSONObject;
import com.xiongfk.springBooting.model.Account;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReflectModelUtils {
    public static void main(String[] args) {

        String modelName ="Account";
        convertField(modelName);
    }
    private static void convertField (String modelName){
        try {
            Map<String,String> map = new HashMap<>();
            String packageName = "com.xiongfk.springBooting.model.";
            modelName = packageName.concat(modelName);
            Class<?> clazz = Class.forName(modelName);
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                map.put(declaredField.getName(),declaredField.getType().toString());
            }
            System.out.println(JSONObject.toJSONString(map));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
