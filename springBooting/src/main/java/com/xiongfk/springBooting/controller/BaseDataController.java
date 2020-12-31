package com.xiongfk.springBooting.controller;

import com.xiongfk.springBooting.service.BaseDateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * BaseDataController
 * 功能描述 TODO 初始化表数据信息
 *
 * @Author xiongfk
 * @Date 2019/7/16
 * @Version 1.0
 **/
@RestController
@RequestMapping("/baseData/")
public class BaseDataController {

    @Autowired
    private BaseDateProductService baseDateProductService;

    @RequestMapping("all")
    public Map<String, Object> all(String key, String isDel) {
        Map<String, Object> result = new HashMap<>();
        long ss = System.currentTimeMillis();
        System.out.println("==========开始时间============" + new Date());
        if ("ok".equals(key)) {
            result = new HashMap<>();
            ebizProductRisk(key, isDel);
            sysDict(key, isDel);
            ebizCommonCode(key, isDel);
            userInfo(key, isDel);
            System.out.println("==========完成耗时============" + (System.currentTimeMillis() - ss) / 1000 + "秒");
            result.put("success", true);
            result.put("msg", "更新成功");
        } else {
            result = new HashMap<>();
            result.put("success", false);
            result.put("msg", "密钥错误");
        }
        return result;
    }

    /*
     * 方法描述 TODO 初始化产品险种信息
     * @author xiongfk
     * @date 2019/7/16
     * @param [key, isDel] key键 OK isDel是否删除 Y是,N否
     * @return int
     */
    @RequestMapping(value = "ebizProductRisk")
    public int ebizProductRisk(String key, String isDel) {
        if (!"ok".equals(key)) {
            return 0;
        }
        baseDateProductService.cleanTwoRedis();
        return baseDateProductService.ebizProductRisk(isDel);
    }

    /**
     * 方法描述 TODO 初始化字典表
     *
     * @param key, isDel key键 OK isDel是否删除 Y是,N否
     * @return int
     * @author xiongfk
     * @date 2019/7/16
     */
    @RequestMapping(value = "sysDict")
    public int sysDict(String key, String isDel) {
        if (!"ok".equals(key)) {
            return 0;
        }
        baseDateProductService.cleanTwoRedis();
        return baseDateProductService.sysDict(isDel);
    }

    /**
     * 方法描述 TODO
     *
     * @param key, isDel key键 OK isDel是否删除 Y是,N否
     * @return int
     * @author xiongfk
     * @date 2019/7/16
     */
    @RequestMapping(value = "ebizCommonCode")
    public int ebizCommonCode(String key, String isDel) {
        if (!"ok".equals(key)) {
            return 0;
        }
        baseDateProductService.cleanTwoRedis();
        return baseDateProductService.ebizCommonCode(isDel);
    }

    /**
     * 方法描述 TODO 初始化用户信息
     *
     * @param key, isDel
     * @return int
     * @author xiongfk
     * @date 2019/7/16
     */
    @RequestMapping(value = "userInfo")
    public int userInfo(String key, String isDel) {
        if (!"ok".equals(key)) {
            return 0;
        }
        baseDateProductService.cleanTwoRedis();
        return baseDateProductService.usreInfo(isDel);
    }

}
