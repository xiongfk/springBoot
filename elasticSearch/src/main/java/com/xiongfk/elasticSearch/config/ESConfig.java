package com.xiongfk.elasticSearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESConfig {

    @Value("${elasticsearch.userName}")
    private String userName;
    @Value("${elasticsearch.password}")
    private String password;
    @Value("${elasticsearch.host}")
    private String hostName;
    @Value("${elasticsearch.port}")
    private Integer port;



    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestClientBuilder restClientBuilder=RestClient.builder(new HttpHost(hostName, port));
        RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
        return client;
    }
}
