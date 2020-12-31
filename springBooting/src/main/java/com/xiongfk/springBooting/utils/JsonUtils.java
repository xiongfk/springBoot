package com.xiongfk.springBooting.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.mybatis.generator.plugins.MapperAnnotationPlugin;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JsonUtils
 * 功能描述 TODO json工具类
 *
 * @Author xiongfk
 * @Date 2019/7/14 16:57
 * @Version 1.0
 **/
public class JsonUtils {
    /**
     * 方法描述 TODO 对象转字符串
     *
     * @param object
     * @return java.lang.String
     * @author xiongfk
     * @date 2019/7/14
     */
    public static String beanToStr(Object object) {
        if (!StringUtils.isEmpty(object)) {
            String jsonString = JSONObject.toJSONString(object);
            if (!StringUtils.isEmpty(jsonString)) {
                return jsonString;
            }
        }
        return null;
    }

    /**
     * 方法描述 TODO 字符串转对象
     *
     * @param str, clazz
     * @return java.lang.Object
     * @author xiongfk
     * @date 2019/7/15
     */
    public static Object strToBean(String str, Class clazz) {
        if (!StringUtils.isEmpty(str) && null != clazz) {
            Object object = JSONObject.parseObject(str, clazz);
            if (!StringUtils.isEmpty(object)) {
                return object;
            }
        }
        return null;
    }

    /**
     * 方法描述 TODO 将json数组转为list集合
     *
     * @param str, clazz
     * @return java.util.List
     * @author xiongfk
     * @date 2019/7/15
     */
    public static List strToList(String str, Class clazz) {
        if (!StringUtils.isEmpty(str) && null != clazz) {
            List objectList = JSONObject.parseArray(str, clazz);
            if (null != objectList && !objectList.isEmpty()) {
                return objectList;
            }
        }
        return null;
    }

    /**
     * 方法描述 TODO 将list集合转为String字符串
     *
     * @param list
     * @return java.lang.String
     * @author xiongfk
     * @date 2019/7/15
     */
    public static String listToStr(List list) {
        if (null != list && !list.isEmpty()) {
            String str = JSONObject.toJSONString(list);
            if (!StringUtils.isEmpty(str)) {
                return str;
            }
        }
        return null;
    }

    /**
     * 方法描述 TODO 将json数组转为List<Map<String,Object>>集合
     *
     * @param str
     * @return java.util.HashMap
     * @author xiongfk
     * @date 2019/7/15
     */
    public static List<Map<String, Object>> strToMap(String str) {
        if (!StringUtils.isEmpty(str)) {
            JSONArray jsonArray = JSONArray.parseArray(str);
            List<Map<String, Object>> mapList = new ArrayList<>();
            for (Object object : jsonArray) {
                Map map = (Map) object;
                if (null != map && !map.isEmpty()) {
                    mapList.add(map);
                }
            }
            return mapList;
        }
        return null;
    }

    public static void main(String[] args) {
        //字符串转对象测试
//        String str = "{\"id\":\"1\",\"name\":\"PMS\",\"money\":\"11\"}";
//        Object obj  = strToBean(str,Account.class);
//        Account account =(Account)obj;
//        System.out.println(account.getName());
        //对象转字符串测试
//        Account acc = new Account();
//        acc.setId(1);
//        acc.setMoney(2.22);
//        acc.setName("xiongfk1");
//        String beanStr= beanToStr(acc);
//        System.out.println(beanStr);
        // String str = "[{\"id\":\"1\",\"name\":\"PMS\",\"money\":\"11\"},{\"id\":\"1\",\"name\":\"PMS1\",\"money\":\"11\"}]";
//        List<Account> accountList= (List<Account>)JSONObject.parseArray(str,Account.class);
//        String str = "[{\"id\":\"1\",\"name\":\"PMS\",\"money\":\"11\"},{\"id\":\"1\",\"name\":\"PMS1\",\"money\":\"11\"}]";
//        List<Account> accountList =(List<Account>) strToList(str,Account.class);
//        for (Account account : accountList) {
//            System.out.println(account.getName());
//        }
//        String string = JSONObject.toJSONString(accountList);
//        System.out.println(string);
//        List<Account> accountList =(List<Account>) strToList(str,Account.class);
        String str = "[{\"id\":\"1\",\"name\":\"PMS\",\"money\":\"11\"},{\"id\":\"1\",\"name\":\"PMS1\",\"money\":\"11\"}]";
        // Map<String,Object> map = (Map<String,Object>)JSONObject.parse(str);
        JSONArray jsonArray = JSONArray.parseArray(str);
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map map = null;
        for (Object o : jsonArray) {
            map = (Map) o;
            mapList.add(map);
        }
        for (Map<String, Object> stringObjectMap : mapList) {
            String s = stringObjectMap.get("name").toString();
            System.out.println(s);
        }
    }
}
