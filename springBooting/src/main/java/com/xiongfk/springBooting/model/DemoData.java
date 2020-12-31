package com.xiongfk.springBooting.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;

import java.util.Date;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/8/3
 * @Version 1.0
 **/
public class DemoData {
    /**
     * 强制读取第三个 这里不建议 index 和 name 同时用，要么一个对象只用index，要么一个对象只用name去匹配
     */
    @ExcelProperty("数值标题")
    private Double doubleData;
    /**
     * 用名字去匹配，这里需要注意，如果名字重复，会导致只有一个字段读取到数据
     */
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    @DateTimeFormat("yyyy年MM月dd日")
    private Date date;
    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getDoubleData() {
        return doubleData;
    }

    public void setDoubleData(Double doubleData) {
        this.doubleData = doubleData;
    }
}
