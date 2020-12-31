package com.xiongfk.elasticSearch.config;

import com.xiongfk.elasticSearch.enums.ResponseEnums;
import com.xiongfk.elasticSearch.model.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/8/28
 * @Version 1.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 处理自定义的业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseBean exceptionHandler(Exception e){
        logger.error("发生业务异常！原因是：{}",e.getMessage());
        return new ResponseBean(ResponseEnums.BAD_REQUEST);
    }
}
