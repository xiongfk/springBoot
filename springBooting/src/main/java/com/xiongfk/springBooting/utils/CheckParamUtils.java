package com.xiongfk.springBooting.utils;

import com.xiongfk.springBooting.base.BaseCommonLog;
import com.xiongfk.springBooting.customAnnotation.CheckParam;
import com.xiongfk.springBooting.model.Student;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/**
 * 功能描述 TODO
 * 校验字段的非空性
 * @Author xiongfk
 * @Date 2020/5/18
 * @Version 1.0
 **/
public class CheckParamUtils extends BaseCommonLog {
    //为空字段提示信息
    private static String NOT_EMPTY = "不能为空!";

    public static <T> void checkParam(T request){
        if(null != request){
            Class<T> clazz = (Class<T>) request.getClass();
            //只校验带有@CheckParam注解
            if(clazz.isAnnotationPresent(CheckParam.class)){
                Method methods[] = clazz.getMethods();
                if(null != methods){
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(CheckParam.class)) {
                            CheckParam param = method.getAnnotation(CheckParam.class);
                            if(method.getName().startsWith("get")){
                                try {
                                    Object objectValue = method.invoke(request);
                                    if (objectValue == null) {
                                        logger.error(param.msg() + NOT_EMPTY);
                                        throw new Exception(param.msg() + NOT_EMPTY);
                                    }

                                    //如果返回值是字符串,判断字符串是否为空串
                                    if(method.getReturnType() == String.class){
                                        String value = (String) objectValue;
                                        if(StringUtils.isBlank(value)) {
                                            logger.error(param.msg() + NOT_EMPTY);
                                            throw new Exception(param.msg() + NOT_EMPTY);
                                        }
                                    }

                                    //如果返回值是list集合,判断是否为空集合
                                    if (method.getReturnType() == List.class) {
                                        Object obj = method.invoke(request);
                                        List list=(List) obj;
                                        if(list == null || list.isEmpty()){
                                            logger.error(param.msg() + NOT_EMPTY);
                                            throw new Exception(param.msg() + NOT_EMPTY);
                                        }
                                        //如果不为空重新遍历递归调用该方法
                                        if(list != null && !list.isEmpty()){
                                            Iterator iterator = list.iterator();
                                            while(iterator.hasNext()){
                                                Object listObj = iterator.next();
                                                if(listObj.getClass().isAnnotationPresent(CheckParam.class)){
                                                    checkParam(listObj);//递归调用
                                                }
                                            }
                                        }
                                    }
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        //如果返回值是一个类，判断此类是否有@CheckParam注解
                        if(method.getReturnType().isAnnotationPresent(CheckParam.class)){
                            try {
                                Object obj = method.invoke(request);
                                if(obj!=null){
                                    checkParam(obj);//递归调用
                                }else {
                                    CheckParam param = method.getReturnType().getAnnotation(CheckParam.class);
                                    logger.info(param.msg() + NOT_EMPTY);
                                    throw new Exception(param.msg() + NOT_EMPTY);
                                }
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    //测试类
    public static void main(String[] args) {
        Student student = new Student();
        student.setStuName("xiongfk");
        student.setAge(25);
        student.setSex("男");
        CheckParamUtils.checkParam(student);
    }
}
