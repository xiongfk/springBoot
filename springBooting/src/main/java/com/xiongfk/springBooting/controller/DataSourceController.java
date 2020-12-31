package com.xiongfk.springBooting.controller;//package com.example.xiongfk.controller;
//
//import com.alibaba.druid.pool.DruidDataSourceFactory;
//import com.example.xiongfk.config.DynamicRoutingDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 功能描述 TODO
// *
// * @Author xiongfk
// * @Date 2020/5/21
// * @Version 1.0
// **/
//@RestController
//@RequestMapping(value = "/dataSource/")
//public class DataSourceController {
//
//    @Autowired
//    private Environment env;
//
//    @Autowired
//    private DynamicRoutingDataSource dynamicDataSource;
//    /**
//     * 添加数据源示例
//     *
//     * @return
//     */
//    @GetMapping("addDataSource")
//    public Object addDataSource() {
//        // 构建 DataSource 属性,
//        Map<String, String> map = new HashMap<>();
//        map.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, env.getRequiredProperty("spring.datasource.slaverX.driverClassName"));
//        map.put(DruidDataSourceFactory.PROP_URL, env.getRequiredProperty("spring.datasource.slaverX.url").replace("{0}", "spirngboot_slave02"));
//        map.put(DruidDataSourceFactory.PROP_USERNAME, env.getRequiredProperty("spring.datasource.slaverX.username"));
//        map.put(DruidDataSourceFactory.PROP_PASSWORD, env.getRequiredProperty("spring.datasource.slaverX.password"));
//        map.put("database", "spirngboot_slave02");
//        return dynamicDataSource.addDataSource(map);
//    }
//
//    /**
//     * 切换数据源示例
//     *
//     * @return
//     */
//    @GetMapping("/get")
//    public Object get() {
//        Map<String, Object> map = new HashMap<>();
////        User u0 = userService.get("dynamic_db0");
////        User u1 = userService.get("dynamic_db1");
////        User u2 = userService.get("dynamic_db2");
////        map.put("u0", u0);
////        map.put("u1", u1);
////        map.put("u2", u2);
//        return map;
//    }
//}
