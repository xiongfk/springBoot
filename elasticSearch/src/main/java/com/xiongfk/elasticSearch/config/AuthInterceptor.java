package com.xiongfk.elasticSearch.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述 TODO 拦截器
 * @Author xiongfk
 * @Date 2020/8/28
 * @Version 1.0
 **/
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        // 拦截处理代码
        HandlerMethod method = (HandlerMethod) handler;
        InterceptorRequired methodAnnotation = method.getMethodAnnotation(InterceptorRequired.class);
        System.out.println(request.getMethod());
        if (null != methodAnnotation) {
            getOpenApiRequestData(request);
        }
        return true;
    }

    public void getOpenApiRequestData(HttpServletRequest request) throws Exception {
        try {
            int contentLength = request.getContentLength();
            if (contentLength < 1) {
                throw new Exception();
            }
            byte buffer[] = new byte[contentLength];
            for (int i = 0; i < contentLength;) {
                int readlen = request.getInputStream().read(buffer, i, contentLength - i);
                if (readlen == -1) {
                    break;
                }
                i += readlen;
            }

            String charEncoding = request.getCharacterEncoding();
            if (charEncoding == null) {
                charEncoding = "UTF-8";
            }
            String datas = new String(buffer, charEncoding);
            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode jsonNode = mapper.readValue(datas, JsonNode.class);
                if(jsonNode.toString().equals("{}")){
                    throw new Exception();
                }
            }catch (Exception e){
                throw new IOException();
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
