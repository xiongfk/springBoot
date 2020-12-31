package com.xiongfk.elasticSearch.controller;


import com.xiongfk.elasticSearch.config.InterceptorRequired;
import com.xiongfk.elasticSearch.service.ElasticSearchService;
import com.xiongfk.elasticSearch.enums.ResponseEnums;
import com.xiongfk.elasticSearch.model.ResponseBean;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class ElasticSearchController {

    @Autowired
    private ElasticSearchService elasticSearchService;

    /*
    用户注册-关联账号信息
     */
    @PostMapping(value = "/regRefAccount/save")
    public ResponseBean orderQuery(@RequestBody Map<String, Object> req) {
        try {
            elasticSearchService.saveRegRefAccount(req);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBean(ResponseEnums.FAIL);
        }
        return new ResponseBean(ResponseEnums.SUCCESS);
    }

    /*
    轨迹类-网络预订(酒店，短租房）订单信息
    */
    @PostMapping(value = "/webHotelOrder/save")
    public ResponseBean webHotelOrder(@RequestBody Map<String, Object> req) {
        try {
            elasticSearchService.saveWebHotelOrder(req);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBean(ResponseEnums.FAIL);
        }
        return new ResponseBean(ResponseEnums.SUCCESS);
    }

    /*
     第三方支付交易信息
      */
    @PostMapping(value = "/thirdPartyPayment/save")
    public ResponseBean thirdPartyPayment(@RequestBody Map<String, Object> req) {
        try {
            elasticSearchService.saveThirdPartyPayment(req);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBean(ResponseEnums.FAIL);
        }
        return new ResponseBean(ResponseEnums.SUCCESS);
    }

    /**
     * 方法描述 TODO 用户注册及修改信息
     *
     * @param req
     * @return com.bth.vo.ResponseBean
     * @date 2020/8/10
     */
    @InterceptorRequired
    @PostMapping(value = "/user/registerAndUpd")
    public ResponseBean registerAndUpd(@RequestBody Map<String, Object> req) {
        try {
            String index = "wang_an_reg_register_upd";
            String type = "_doc";
            elasticSearchService.userRegisterAndUpd(req, index, type);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBean(ResponseEnums.FAIL);
        }
        return new ResponseBean(ResponseEnums.SUCCESS);
    }

    /**
     * 方法描述 TODO 登录日志信息
     *
     * @param req
     * @return com.bth.vo.ResponseBean
     * @date 2020/8/10
     */
    @InterceptorRequired
    @PostMapping(value = "/user/loginLogInfo")
    public ResponseBean loginLogInfo(@RequestBody Map<String, Object> req) {
        try {
            String index = "wang_an_reg_login_log";
            String type = "_doc";
            elasticSearchService.loginLogInfo(req, index, type);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBean(ResponseEnums.FAIL);
        }
        return new ResponseBean(ResponseEnums.SUCCESS);
    }

    @RequestMapping(value = "/user/queryLoginInfo", method = RequestMethod.POST)
    public Map<String, Object> queryLoginInfo() {
        String index = "wang_an_reg_login_log";
        String type = "_doc";
        String id = "2X2X23MB_DuucGgcI7ri";
        return elasticSearchService.queryLoginLogInfo(index, type, id);
    }

    @RequestMapping(value = "/user/queryLoginInfos", method = RequestMethod.POST)
    public List<Map<String, Object>> queryLoginInfos(@RequestBody Map<String, Object> req) {
        String index = "wang_an_reg_login_log";

        List<Map<String, Object>> list = new ArrayList<>();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //String[] fields = {"_id", "CREATE_TIME", "USER_INTENRALID", "USER_ACCOUNT"};
        //需要返回和不返回的字段，可以是数组也可以是字符串
        //sourceBuilder.fetchSource(fields, null);
        //设置根据哪个字段进行排序查询
        sourceBuilder.sort(new FieldSortBuilder("CREATE_TIME").order(SortOrder.DESC));
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        //添加查询条件
        MatchQueryBuilder matchQueryBuilder = null;
        for (Map.Entry<String, Object> entry : req.entrySet()) {
            matchQueryBuilder = QueryBuilders.matchQuery(entry.getKey(), entry.getValue());
        }
        builder.must(matchQueryBuilder);
        list = elasticSearchService.queryLoginInfos(index, 1, 10, sourceBuilder, builder);
        return list;
    }
}