package com.xiongfk.springBooting.controller;//package com.example.xiongfk.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.sinosoft.ebiz.basedate.service.BaseDataOperationService;
//import com.sinosoft.ebiz.basedate.service.BaseDateProductService;
//import com.sinosoft.ebiz.basedate.util.BaseDateRedis;
//import com.sinosoft.ebiz.basedate.util.CustomerRiskamntThread;
//import com.sinosoft.ebiz.basedate.util.EcontLacomThread;
//import com.sinosoft.ebiz.basedate.util.EcontProxyThread;
//import com.sinosoft.ebiz.core.underwriting.generated.model.EbizContCustomerRiskamnt;
//import com.sinosoft.ebiz.data.customer.generated.model.EcontLacom;
//import com.sinosoft.ebiz.data.customer.generated.model.EcontProxy;
//import com.sinosoft.ebiz.data.product.generated.model.EbizRedisLog;
//import com.sinosoft.ebiz.modules.customer.entity.ChannelSynBatchDTO;
//import com.sinosoft.ebiz.modules.customer.entity.EcontProxyDTO;
//import com.sinosoft.sinocloud.platform.common.context.SpringContext;
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.Date;
//
///**
// * 运维及数据同步（传统核心）
// * Created by 庞尔生 on 2018/7/6.
// */
//@Controller
//@RequestMapping("syncdata")
//public class SyncDataController {
//
//    @Autowired
//    private BaseDateProductService baseDateProductService;
//
//    @Autowired
//    private BaseDataOperationService baseDataOperationService;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    private Logger logger = Logger.getLogger(this.getClass());
//
//    @RequestMapping(value = "ebizContCustomerRiskamnt", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> ebizContCustomerRiskamnt(String list) {
//        Map<String, Object> map = new HashMap<>();
//        if (StringUtils.isBlank(list)) {
//            map.put("success", false);
//            map.put("msg", "秘钥错误");
//        }else {
//            //线程
//            CustomerRiskamntThread customerRiskamntThread = new CustomerRiskamntThread();
//            customerRiskamntThread.start();
//            map.put("msg", "数据处理成功");
//            map.put("success", "true");
//        }
//        return map;
//    }
//
//    @RequestMapping(value = "ebizContCustomerRiskamntByTime", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> ebizContCustomerRiskamntByTime(String startDate, String endDate) throws ParseException {
//        Map<String, Object> map = new HashMap<>();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        if (startDate == null || endDate == null) {
//            map.put("success", false);
//            map.put("msg", "开始或结束时间为空");
//            return map;
//        }
//        baseDateProductService.cleanTwoRedis();
//        baseDataOperationService.operationCustomerRiskamnt(sdf.parse(startDate), sdf.parse(endDate));
//        map.put("success", true);
//        return map;
//    }
//
//    @RequestMapping(value = "econtProxyByTime", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> econtProxyByTime(String startDate, String endDate) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Map<String, Object> map = new HashMap<>();
//        if (startDate == null || endDate == null) {
//            map.put("success", false);
//            map.put("msg", "开始或结束时间为空");
//            return map;
//        }
//        baseDateProductService.cleanTwoRedis();
//        baseDataOperationService.operationEcontProxy(sdf.parse(startDate), sdf.parse(endDate));
//        map.put("success", true);
//        return map;
//    }
//
//    @RequestMapping(value = "econtLacomByTime", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> econtLacomByTime(String startDate, String endDate) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Map<String, Object> map = new HashMap<>();
//        if (startDate == null || endDate == null) {
//            map.put("success", false);
//            map.put("msg", "开始或结束时间为空");
//            return map;
//        }
//        baseDateProductService.cleanTwoRedis();
//        baseDataOperationService.operationEcontLacom(sdf.parse(startDate), sdf.parse(endDate));
//        map.put("success", true);
//        return map;
//    }
//
//    @RequestMapping(value = "econtProxy", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> econtProxy(String list) {
//        System.out.println("econtProxy开始");
//        Map<String, Object> map = new HashMap<>();
//        if (StringUtils.isBlank(list)) {
//            map.put("success", false);
//            map.put("msg", "秘钥错误");
//        }else {
//            EcontProxyThread econtProxyThread = new EcontProxyThread();
//            econtProxyThread.start();
//            map.put("msg", "处理数据成功");
//            map.put("success", true);
//        }
//        return map;
//    }
//
//    @RequestMapping(value = "econtLacom", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> econtLacom(String list) {
//        Map<String, Object> map = new HashMap<>();
//        if (StringUtils.isBlank(list)) {
//            map.put("success", false);
//            map.put("msg", "秘钥错误");
//        }else {
//            EcontLacomThread econtLacomThread = new EcontLacomThread();
//            econtLacomThread.start();
//            map.put("msg", "处理数据成功");
//            map.put("success", true);
//        }
//        return map;
//    }
//
//    @RequestMapping(value = "ebizProductRiskCashvalueByTime", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> ebizProductRiskCashvalue(String startDate, String endDate) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Map<String, Object> map = new HashMap<>();
//        if (startDate == null || endDate == null) {
//            map.put("success", false);
//            map.put("msg", "开始或结束时间为空");
//            return map;
//        }
//        baseDateProductService.cleanTwoRedis();
//        baseDataOperationService.operationCashValue(sdf.parse(startDate), sdf.parse(endDate));
//        map.put("success", true);
//        return map;
//    }
//
//
//
//}
