package com.xiongfk.springBooting.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.xiongfk.springBooting.config.CustomStringStringConverter;
import lombok.Data;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/8/3
 * @Version 1.0
 **/
@Data
public class ConverterData {
    /**
     * converter属性定义自己的字符串转换器
     */
    @ExcelProperty(converter = CustomStringStringConverter.class)
    private String string;
    /**
     * 这里用string 去接日期才能格式化
     */
    @DateTimeFormat("yyyy年MM月dd日")
    private String date;
    /**
     * 我想接收百分比的数字
     */
    private String doubleData;
}
