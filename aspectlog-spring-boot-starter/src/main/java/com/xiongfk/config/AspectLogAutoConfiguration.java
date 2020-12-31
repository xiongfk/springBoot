package com.xiongfk.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/9/9
 * @Version 1.0
 **/
@Aspect
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@Configuration
@ConditionalOnProperty(prefix = "aspectLog", name = "enable",havingValue = "true", matchIfMissing = true)
public class AspectLogAutoConfiguration implements PriorityOrdered {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Around("@annotation(com.xiongfk.config.AspectLog) ")
    public Object isOpen(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        //执行方法名称
        String taskName = thisJoinPoint.getSignature().toString().substring(
                        thisJoinPoint.getSignature().toString().indexOf(" "),
                        thisJoinPoint.getSignature().toString().indexOf("("));
        taskName = taskName.trim();
        long time = System.currentTimeMillis();
        Object result = thisJoinPoint.proceed();
        logger.info("method:{} run :{} ms", taskName, (System.currentTimeMillis() - time));
        return result;
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
