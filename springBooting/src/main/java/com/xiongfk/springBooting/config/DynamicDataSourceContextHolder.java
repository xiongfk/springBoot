package com.xiongfk.springBooting.config;//package com.example.xiongfk.config;
//
///**
// * 功能描述 TODO
// * 初始化数据源
// * @Author xiongfk
// * @Date 2020/5/21
// * @Version 1.0
// **/
//public class DynamicDataSourceContextHolder {
//    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>() {
//        @Override
//        protected String initialValue() {
//            return "master";
//        }
//    };
//
//    /**
//     * To switch DataSource
//     *
//     * @param key the key
//     */
//    public static void setDataSourceKey(String key) {
//        contextHolder.set(key);
//    }
//
//    /**
//     * Get current DataSource
//     *
//     * @return data source key
//     */
//    public static String getDataSourceKey() {
//        return contextHolder.get();
//    }
//
//    /**
//     * To set DataSource as default
//     */
//    public static void clearDataSourceKey() {
//        contextHolder.remove();
//    }
//}
