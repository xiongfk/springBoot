package com.xiongfk.springBooting.enums;

public enum ENUM_CONVERT_TYPE {
    STRING("string","varchar");
    private final String key;
    private final String value;

    ENUM_CONVERT_TYPE(String key, String value) {
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
        for (ENUM_CONVERT_TYPE enumItem : ENUM_CONVERT_TYPE.values()) {
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
        for (ENUM_CONVERT_TYPE enum_config_type : ENUM_CONVERT_TYPE.values()) {
            if (value.equals(enum_config_type.value())) {
                return enum_config_type.key();
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String key = "1001";
        String value = "请求";
        System.out.println(ENUM_CONVERT_TYPE.getEnumValueByKey(key));
        System.out.println(ENUM_CONVERT_TYPE.getEnumKeyByValue(value));
    }
}
