package com.xiongfk.springBooting.base;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述 TODO
 * 记录全局请求响应日志
 * @Author xiongfk
 * @Date 2020/4/30
 * @Version 1.0
 **/
@Component
@Aspect
public class WebLogAspect {
    protected final static Logger logger = LogManager.getLogger();
    /**
     * 定义一个切入点 只拦截controller.
     * 解释下：
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 定义在web包或者子包
     * ~ 第三个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    @Pointcut("execution(* com.example.xiongfk.controller..*.*(..))")
    private void parameterPointCut() {}

    /**
     * 方法执行前，记录请求
     *
     * @param joinPoint
     */
    @Before("parameterPointCut()")
    public void requestLog(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//这个RequestContextHolder是Springmvc提供来获得请求的东西
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        try {
            String queryStr = request.getQueryString();
            if (StringUtils.isNotEmpty(queryStr)) {
                logger.info("请求完整信息 1 : {}{}?{} {} 类信息:[{}]" , request.getRemoteAddr(), request.getRequestURI(), queryStr,request.getMethod(),joinPoint.toString());
            } else {
                logger.info("请求完整地址 2 : {}{} {} 类信息:[{}]" , request.getRemoteAddr(), request.getRequestURI() , request.getMethod(),joinPoint.toString());
            }
        } catch (Exception ex) {
            logger.error("请求信息出现异常", ex);
        }
    }

//    private void printRequestArgs(JoinPoint joinPoint) {
//        logger.info("请求方法:[{}]", joinPoint.toString());
//        try {
//            Object[] reqArgs = joinPoint.getArgs();
//            if (null == reqArgs) {
//                return;
//            }
//            logger.info("请求入参 {} ", JSONObject.toJSONString(reqArgs));
//        } catch (Exception ex) {
//            logger.error("请求入参转换异常", ex);
//        }
//    }

    /**
     * 方法执行后,记录响应
     * @param result 方法执行结果注入对象
     * @return
     */
    @AfterReturning(returning = "result", pointcut = "parameterPointCut()")
    public Object responeLog(Object result) {
        String response = "";
        try {
            response = JSONObject.toJSONString(result);
            logger.info("响应出参: {}", response);
        } catch (Throwable ex) {
            logger.error("响应异常", ex);
        }
        return response;
    }
}