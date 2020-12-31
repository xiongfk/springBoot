package com.xiongfk.elasticSearch.service;

import com.xiongfk.elasticSearch.enums.ResponseEnums;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author niliang
 * @since 2020-06-07
 */
@Service
public class ElasticSearchService {

    private Logger log = LoggerFactory.getLogger(ElasticSearchService.class);

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public int saveRegRefAccount(Map<String,Object> data) throws IOException {

        data.put("CREATE_TIME",System.currentTimeMillis());
        IndexRequest indexRequest = new IndexRequest("wang_an_reg_ref_account1","_doc");

        indexRequest.source(data);
        //indexRequest.opType(DocWriteRequest.OpType.INDEX);
        IndexResponse response=restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        return ResponseEnums.SUCCESS.getCode();

    }

    public int saveWebHotelOrder(Map<String, Object> data) throws IOException{
        data.put("CREATE_TIME",System.currentTimeMillis());
        IndexRequest indexRequest = new IndexRequest("wang_an_web_hotel_order","_doc");

        indexRequest.source(data);
        //indexRequest.opType(DocWriteRequest.OpType.INDEX);
        IndexResponse response=restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        return ResponseEnums.SUCCESS.getCode();
    }

    public int saveThirdPartyPayment(Map<String, Object> data)throws IOException {
        data.put("CREATE_TIME",System.currentTimeMillis());
        IndexRequest indexRequest = new IndexRequest("wang_an_third_party_payment","_doc");

        indexRequest.source(data);
        //indexRequest.opType(DocWriteRequest.OpType.INDEX);
        IndexResponse response=restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        return ResponseEnums.SUCCESS.getCode();
    }

    public int userRegisterAndUpd(Map<String,Object> data,String index,String type) throws IOException {
        data.put("CREATE_TIME",System.currentTimeMillis());
        IndexRequest indexRequest = new IndexRequest(index,type);
        indexRequest.source(data);
        IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        log.info("用户注册修改信息响应 : " + response.toString());
        return ResponseEnums.SUCCESS.getCode();
    }

    public int loginLogInfo(Map<String,Object> data,String index,String type) throws IOException {
        data.put("CREATE_TIME",System.currentTimeMillis());
        IndexRequest indexRequest = new IndexRequest(index,type);
        indexRequest.source(data);
        IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        log.info("登录日志信息响应 : " + response.toString());
        return ResponseEnums.SUCCESS.getCode();
    }

    public Map<String,Object> queryLoginLogInfo(String index,String type,String id){
        GetRequest getRequest = new GetRequest(index,type,id);
        GetResponse getResponse = null;
        Map<String,Object> map = new HashMap<>();
        try {
            getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            if (getResponse.isExists()) {
                map = getResponse.getSourceAsMap();
                log.info(map.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public List<Map<String, Object>> queryLoginInfos(String index, int startPage, int pageSize, SearchSourceBuilder sourceBuilder, QueryBuilder queryBuilder) {
        SearchRequest request = new SearchRequest(index);
        //设置超时时间
        sourceBuilder.timeout(new TimeValue(120, TimeUnit.SECONDS));
        //设置是否按匹配度排序
        sourceBuilder.explain(true);
        //加载查询条件
        log.info("查询条件 : " + queryBuilder.toString());
        sourceBuilder.query(queryBuilder);
        //设置分页
        sourceBuilder.from((startPage - 1) * pageSize).size(pageSize);
        log.info("查询返回条件：" + sourceBuilder.toString());
        request.source(sourceBuilder);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            long totalHits = searchResponse.getHits().getTotalHits();
            log.info("共查出{}条记录", totalHits);
            RestStatus status = searchResponse.status();
            if (status.getStatus() == 200) {
                List<Map<String, Object>> sourceList = new ArrayList<>();
                for (SearchHit searchHit : searchResponse.getHits().getHits()) {
                    Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                    sourceList.add(sourceAsMap);
                }
                return sourceList;
            }
        } catch (IOException e) {
            log.error("条件查询索引{}时出错", index);
        }
        return null;
    }
}
