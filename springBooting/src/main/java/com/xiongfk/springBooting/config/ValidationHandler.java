package com.xiongfk.springBooting.config;

import com.xiongfk.springBooting.base.BaseCommonLog;
import com.xiongfk.springBooting.customAnnotation.Validation;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * 功能描述 TODO
 * 仅处理传入object中的Validate注解
 * @Author xiongfk
 * @Date 2019/10/24
 * @Version 1.0
 **/
public class ValidationHandler extends BaseCommonLog {
    // 方法重载
    public static void validateParams(Object obj) throws Exception {
        validateParams(obj,null,true);
    }

    public static void validateParams(Object obj,boolean accordFlag) throws Exception {
        validateParams(obj,null,accordFlag);
    }

    /**
     * 校验参数不为空
     * @param obj 需要校验的对象
     * @param excludeParams 需要排除校验的属性名集合
     * @param accordFlag 根据属性依赖校验
     */
    public static void validateParams(Object obj, Set<String> excludeParams, boolean accordFlag) throws Exception {
        logger.info("validateParams校验日志记录");
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                // 当前属性在需要排除校验的集合中时，跳过当前字段校验。
                if(excludeParams!=null&&excludeParams.contains(field.getName())){
                    continue;
                }
                Validation annotation = (Validation)field.getAnnotation(Validation.class);
                logger.info("=====----"+annotation+"---> "+field.getName());
                //注解存在时，则说明当前字段需要校验
                if(annotation!=null){
                    // 是否根据依据筛选为空属性
                    if(accordFlag){
                        // 根据某字段判别是否为空
                        if(StringUtils.isNotEmpty(annotation.accordField())){
                            Field accordField = obj.getClass().getDeclaredField(annotation.accordField());
                            accordField.setAccessible(true);
                            Object accordValue = accordField.get(obj);
                            // 为空或不等于注解预设值则跳过当前属性的校验
                            if(accordValue==null||!accordValue.toString().equals(annotation.accordValue())){
                                continue;
                            }
                        }
                    }
                    // 属性值不能为空
                    if(value==null){
                        throw new Exception(field.getName());
                    }
                    checkTypeEnumValue(annotation,field,value,annotation.isPositive());
                }
            } catch (IllegalAccessException e) {
                logger.error("xxxx日志记录:"+e.getMessage());
                throw new Exception(field.getName());
            } catch (Exception e) {
                logger.info("xxxx日志记录");
                throw new Exception(field.getName());
            }
        }
    }

    /**
     * 校验注解配置的属性值
     * @param annotation
     * @param field
     * @param value
     * @param isPositive
     */
    private static void checkTypeEnumValue(Validation annotation, Field field, Object value,boolean isPositive) throws Exception {
        // 暂值列举几种数据处理,剩余类型处理类似
        switch (annotation.value()){
            case TYPE_STRING:
                if(StringUtils.isEmpty(value.toString())||"null".equals(value.toString().toLowerCase())){
                    throw new Exception(field.getName());
                }
                break;
            case TYPE_LONG:
                if(0L==(Long)value){
                    throw new Exception(field.getName());
                }
                if(isPositive&&(Long)value<0){
                    throw new Exception(field.getName());
                }
                break;
        }
    }
}
