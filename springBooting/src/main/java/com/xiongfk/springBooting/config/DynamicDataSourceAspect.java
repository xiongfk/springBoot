package com.xiongfk.springBooting.config;//package com.example.xiongfk.config;
//
//import com.example.xiongfk.base.BaseCommonLog;
//import org.apache.commons.lang.StringUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//
///**
// * 功能描述 TODO
// *
// * @Author xiongfk
// * @Date 2020/5/21
// * @Version 1.0
// **/
//public class DynamicDataSourceAspect extends BaseCommonLog {
//    /**
//     * 定义切点,
//     * 	service包 以及其子包下 第一个参数为String 的方法作为切点
//     */
//    @Pointcut("execution( * com.example.xiongfk.service.*.*(String, ..))")
//    public void serviceAspect() {}
//
//    /**
//     * 选择数据源
//     *
//     * @param point
//     */
//    @Before("serviceAspect()")
//    public void switchDataSource(JoinPoint point) {
//        Object[] params = point.getArgs();
//        String param = (String) params[0];
//
//        if(StringUtils.isBlank(param)) return;
//
//        if(DynamicRoutingDataSource.isExistDataSource(param) && !param.equals(DynamicDataSourceContextHolder.getDataSourceKey()) ) {
//            DynamicDataSourceContextHolder.setDataSourceKey(param);
//        }
//
//        logger.info("Switch DataSource to [{}] in Method [{}]",
//                DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
//    }
//
//    /**
//     * 重置数据源
//     *
//     * @param point
//     */
//    @After("serviceAspect()")
//    public void restoreDataSource(JoinPoint point) {
//        DynamicDataSourceContextHolder.clearDataSourceKey();
//        logger.info("Restore DataSource to [{}] in Method [{}]",
//                DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
//    }
//}
