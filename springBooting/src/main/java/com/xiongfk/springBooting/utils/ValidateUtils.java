package com.xiongfk.springBooting.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 *  非空,长度等校验
 * 【类或接口功能描述】
 * @author xiongfk
 * @date 2018年5月3日 上午11:32:48 
 * @version V1.0
 */
public class ValidateUtils{
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    
    public static <T> List<Object> validate(T t,Class<?>... gorups) throws Exception {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t,gorups);
        List<Object> messageList = new ArrayList<Object>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessage());
        }
        if(!messageList.isEmpty()){
            throw new Exception(messageList.get(0).toString());
        }
        return messageList;
    }
}