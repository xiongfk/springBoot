package com.xiongfk.springBooting.enums;

/**
 * Enum_config_type
 * 枚举类描述 TODO
 *
 * @Author gaosh
 * @Date 2019/7/14 18:23
 * @Version 1.0
 **/
public enum ENUM_CONFIG_TYPE {
    REQUEST_CODE("1001", "请求"), RESPONSE_CODE("1002", "响应");
    private final String key;
    private final String value;

    ENUM_CONFIG_TYPE(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String key() {
        return key;
    }

    public String value() {
        return value;
    }

    /**
     * 方法描述 TODO 根据key获取value
     *
     * @param key
     * @return java.lang.String
     * @author xiongfk
     * @date 2019/7/17
     */
    public static String getEnumValueByKey(String key) {
        for (ENUM_CONFIG_TYPE enumItem : ENUM_CONFIG_TYPE.values()) {
            if (key.equals(enumItem.key())) {
                return enumItem.value();
            }
        }
        return "";
    }

    /**
     * 方法描述 TODO 根据value值获取key
     *
     * @param value
     * @return java.lang.String
     * @author xiongfk
     * @date 2019/7/17
     */
    public static String getEnumKeyByValue(String value) {
        for (ENUM_CONFIG_TYPE enum_config_type : ENUM_CONFIG_TYPE.values()) {
            if (value.equals(enum_config_type.value())) {
                return enum_config_type.key();
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String key = "1001";
        String value = "请求";
        System.out.println(ENUM_CONFIG_TYPE.getEnumValueByKey(key));
        System.out.println(ENUM_CONFIG_TYPE.getEnumKeyByValue(value));
    }
}
