package com.xiongfk.springBooting.controller;

import com.xiongfk.springBooting.base.BaseCommonLog;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述 TODO
 * 请求报文+文件请求
 * @Author xiongfk
 * @Date 2019/9/16
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/fileUpload/")
public class UploadFileController extends BaseCommonLog {

    @RequestMapping(value = "uploadInfo")
    public String fileUpload(MultipartFile file,String requestXml) {
        String errorResult = "";
        String requestUrl = "http://127.0.0.1:8088/reciveUpload/receiveData/";
        if (file == null) {
            errorResult = "上传文件文件信息不能为空,请重新上传!";
            logger.error(errorResult);
            return errorResult;
        }
        logger.info(file);
        if (StringUtils.isEmpty(requestXml)) {
            errorResult = "文件上传请求信息不能为空,请重新上传!";
            logger.error(errorResult);
            return errorResult;
        }
        Map<String, String> resultMap = new HashMap<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            //创建httppost请求
            HttpPost httpPost = new HttpPost(requestUrl);
            //通过MultipartEntityBuilder.create()方法创建MultipartEntityBuilder
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            //设置字符集
            builder.setCharset(Charset.forName("UTF-8"));
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            //	文件传输http请求头(multipart/form-data)
            builder.addBinaryBody("uploadFile", file.getInputStream(),
                    ContentType.MULTIPART_FORM_DATA,file.getOriginalFilename());// 文件流
            //字节传输http请求头(application/json)
            String mimeType = "application/json";
            //设置传输类型
            ContentType contentType = ContentType.create(mimeType, Charset.forName("UTF-8"));
            //设置请求报文
            builder.addTextBody("requestXml", requestXml, contentType);//字节流

            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            // 执行提交
            HttpResponse response = httpClient.execute(httpPost);
            //响应信息
            HttpEntity responseEntity = response.getEntity();
            resultMap.put("code", String.valueOf(response.getStatusLine().getStatusCode()));
            resultMap.put("data", "");
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity,Charset.forName("UTF-8"));
                resultMap.put("data", result);
            }

            //成功之后结果回调地址
            String resCode = "";
            //结果编码
            int start = result.indexOf("<ResultCode>");
            int end = result.indexOf("</ResultCode>");
            if(end>start && result.length()>(start+12)){
                resCode = result.substring((start+12), end);
            }
            if ("01".equals(resCode)) {
                return "fileUploadSuccess";
            } else {
                return "error";
            }
        } catch (Exception e) {
            resultMap.put("scode", "error");
            resultMap.put("data", "HTTP请求出现异常: " + e.getMessage());
            resultMap.put("msg", "HTTP请求出现异常:连接到文件服务器失败 ");
            Writer w = new StringWriter();
            logger.error(new PrintWriter(w)+"异常信息:"+e.getMessage());
            return "FAIL";
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
