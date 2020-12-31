package com.xiongfk.springBooting.utils;

import com.xiongfk.springBooting.base.BaseCommonLog;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * 功能描述 TODO Jackson工具类
 * @Author xiongfk
 * @Date 2020/8/27
 * @Version 1.0
 **/
public class JacksonUtils extends BaseCommonLog {

    //工具类应该屏蔽构造函数
    private JacksonUtils() {}

    /**
    * 方法描述 TODO 验证字符串是否符合json规范
    * @author xiongfk
    * @date 2020/8/27
    * @param str
    * @return boolean
    */
    public static boolean checkJson(String str){
        boolean result = true;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.readValue(str, JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
    * 方法描述 TODO 将对象转为json串
    * @author xiongfk
    * @date 2020/8/27
    * @param object
    * @return java.lang.String
    */
    public static String jsonToStr(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonStr = objectMapper.writeValueAsString(object);
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String();
    }

    /**
    * 方法描述 TODO 将字符串转为指定的java对象
    * @author xiongfk
    * @date 2020/8/27
    * @param str, clazz
    * @return java.lang.Object
    */
    public static Object strToJson(String str,Class clazz){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(str,clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String();
    }

    /**
     * 方法描述 TODO 将字符串转为对象
     * @author xiongfk
     * @date 2020/8/27
     * @param str
     * @return java.lang.Object
     */
    public static Object strToJson(String str){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(str,JsonNode.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String();
    }

    public static void main(String[] args) {
//        User user = new User();
//        user.setId(1L);
//        user.setName("xiongfk");
//        List<Object> list = new ArrayList<>();
//        list.add(user);
//        Map<String,Object> map = new HashMap<>();
//        map.put("id",101);
//        map.put("name","xiongfk");
//        String s = jsonToStr(map);
//        System.out.println(s);

        String sessionStream = "{\"id\":1,\"name\":\"xiongfk\"}";
        JsonNode user = (JsonNode) strToJson(sessionStream);
        System.out.println(user.toString());
    }
}
