package com.xiongfk.springBooting.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiongfk.springBooting.mapper.master.CommMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 庞尔生 on 2018/7/3.
 */
@Component
public class MyBatisUtil<T> {

    @Autowired
    private CommMapper commMapper;

    public String createSql(Map<String, List> map, Class<T> c) {
        Field[] fields = c.getDeclaredFields();
        StringBuffer files = new StringBuffer();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            fieldName = replaceA_Z(fieldName);
            if (i != fields.length - 1) {
                files.append(fieldName + ",");
            } else {
                files.append(fieldName);
            }
        }
        StringBuffer stringBuffer = new StringBuffer("select ");
        stringBuffer.append(files.toString());
        stringBuffer.append(" from ");
        stringBuffer.append(map.get("table").get(0));
        map.remove("table");
        Set<String> keySet = map.keySet();
        stringBuffer.append(" where 1=1 ");
        for (String key : keySet) {
            stringBuffer.append("and " + key + " in ('");
            List list = map.get(key);
            for (int i = 0; i < list.size(); i++) {
                if (i != list.size() - 1) {
                    stringBuffer.append(list.get(i) + "','");
                } else {
                    stringBuffer.append(list.get(i));
                }
            }
            stringBuffer.append("') ");
        }
        return stringBuffer.toString();
    }

    private String replaceA_Z(String fieldName) {
        String reg = "[A-Z]";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(fieldName);
        Set<String> hasSet = new HashSet<>();
        while (matcher.find()) {
            String group = matcher.group();
            if (!hasSet.contains(group)) {
                hasSet.add(group);
                fieldName = fieldName.replace(group, "_" + group);
            }

        }
        return fieldName;
    }

    public List<T> getList(Map<String, List> map, Class<T> c) {
        String sql = createSql(map, c);
        List<HashMap<String, Object>> hashMaps = commMapper.superSelect(sql);
        List<HashMap<String, Object>> hashMaps1 = new ArrayList<>();
        for (HashMap<String, Object> h : hashMaps) {
            HashMap<String, Object> hashMap = new HashMap<>();
            Set<String> keySet = h.keySet();
            for (String key : keySet) {
                Object o = h.get(key);
                key = key.replace("_", "");
                hashMap.put(key, o);
            }
            hashMaps1.add(hashMap);
        }
        String s = JSON.toJSONString(hashMaps1);
        List<T> list = JSONObject.parseArray(s, c);
        return list;
    }

    //把where条件传到redis服务


    public static void main(String[] args) {
//        Map<String, List> map = new HashMap<>();
//        map.put("table", )
//        createSql();
    }
}
