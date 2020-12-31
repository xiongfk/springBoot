package com.xiongfk.springBooting.utils;


import com.xiongfk.springBooting.base.BaseCommonLog;
import org.apache.commons.fileupload.FileItem;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 类描述 TODO 报文+文件接收类
 * @author xiongfk
 * @date 2019/9/18
 * @param
 * @return
 */
@Controller
@RequestMapping(value = "/reciveUpload/")
public class ReciveUpload extends BaseCommonLog {

    @RequestMapping(value = "receiveData")
    @ResponseBody
    public void receiveData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        multipartRequest.setCharacterEncoding("utf-8");
        String requestXml = request.getParameter("requestXml");
        logger.info("请求报文:\n" + requestXml );
//		multipartRequest.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
        MultipartFile fItem1 = multipartRequest.getFile("uploadFile");

        logger.info(fItem1.getName());
        logger.info(fItem1.getOriginalFilename());
        logger.info(fItem1.getInputStream());

        OutputStream os = null;
        InputStream inputStream = null;
        String fileName = null;
        try {
            inputStream = fItem1.getInputStream();
            fileName = fItem1.getOriginalFilename();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        try {
            String path = "C:/AH";
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            PrintWriter out = null;
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            out.print("<?xml version='1.0' encoding='utf-8'?><EbizRes><EbizHead><SerialNo>132</SerialNo><TransType>111</TransType><SourceCode>WX001</SourceCode><BusinessNo>123</BusinessNo><ResultCode>01</ResultCode><ResultDesc>处理成功</ResultDesc></EbizHead></EbizRes>");
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
//        //通过MultipartEntityBuilder.create()方法创建MultipartEntityBuilder
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        //设置字符集
//        builder.setCharset(Charset.forName("UTF-8"));
//        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//        //	文件传输http请求头(multipart/form-data)
//        //	文件传输http请求头(multipart/form-data)
//        builder.addBinaryBody("uploadFile", fItems.getInputStream(),
//                ContentType.MULTIPART_FORM_DATA,fItems.getOriginalFilename());// 文件流
//
//		CommonsMultipartFile cf = (CommonsMultipartFile)fItem1;
//
////		Map<String,MultipartFile> fileMap = multipartRequest.getFileMap();
//
//		File directory = null;
//		PrintWriter out = null;
//        response.setContentType("text/html;charset=UTF-8");
//        Map<Object,Object> map = new HashMap<Object,Object>();
//		String fName = "";
//        Object fValue = null;
//        FileItem fItem = cf.getFileItem();
//        fName = fItem.getFieldName();
//        fValue = fItem.getInputStream();
//        map.put(fName, fValue);
//        String name = fItem.getName();
//        if(name != null && !("".equals(name))) {
//            name = name.substring(name.lastIndexOf(File.separator) + 1);
//            directory = new File(configUrl);
//            directory.mkdirs();
//            String filePath = (configUrl+"/new")+name;
//            map.put(fName + "FilePath", filePath);
//            InputStream is = fItem.getInputStream();
//            FileOutputStream fos = new FileOutputStream(filePath);
//            byte[] buffer = new byte[1024];
//            while (is.read(buffer) > 0) {
//                fos.write(buffer, 0, buffer.length);
//            }
//            fos.flush();
//            fos.close();
//            map.put(fName + "FileName", name);
//        }
//
//        // 数据处理
//        try {
//            out = response.getWriter();
//            out.print("<?xml version='1.0' encoding='utf-8'?><EbizRes><EbizHead><SerialNo>132</SerialNo><TransType>111</TransType><SourceCode>WX001</SourceCode><BusinessNo>123</BusinessNo><ResultCode>01</ResultCode><ResultDesc>处理成功</ResultDesc></EbizHead></EbizRes>");
//        } catch (IOException e) {
//            logger.error(e.getMessage());
//        } finally {
//            out.close();
//        }
            }
//	public static boolean isExcel2003(String filePath)  {  
//        return filePath.matches("^.+\\.(?i)(xls)$");  
//    }
