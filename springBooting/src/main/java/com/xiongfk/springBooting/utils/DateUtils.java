package com.xiongfk.springBooting.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateUtils
 * 功能描述 TODO 时间操作工作类
 *
 * @Author xiongfk
 * @Date 2019/7/8 13:56
 * @Version 1.0
 **/
public class DateUtils {
    //日志输出
    private Logger logger = LoggerFactory.getLogger(DateUtils.class);
    //日期格式
    public static String FORMAT_YMD = "yyyy-MM-dd";
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    public static String FORMATYMDHMS = "yyyyMMddHHmmss";
    public static String FORMAT_YYMMDD = "yyyyMMdd";

    /**
     * 方法描述 TODO 获取当前时间戳
     *
     * @param
     * @return java.lang.Long
     * @author xiongfk
     * @date 2019/7/8
     */
    public static Long getMillis() {
        Date date = new Date();
        long l = date.getTime();
        System.out.println("当前时间戳为:" + l);
        return l;
    }

    /**
     * 方法描述 TODO 将时间戳转为时间
     *
     * @param timeStamp
     * @return java.lang.String
     * @author xiongfk
     * @date 2019/7/8
     */
    public static String millisToDate(Long timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_YMDHMS);
        Date date = new Date(timeStamp);
        String res = simpleDateFormat.format(date);
        System.out.println(res);
        return res;
    }

    /**
     * 方法描述 TODO 将时间戳转换为时间
     *
     * @param timeStamp
     * @return java.lang.String
     * @author xiongfk
     * @date 2019/7/8
     */
    public static String stampToDate(String timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_YMDHMS);
        long lt = new Long(timeStamp);
        Date date = new Date(lt);
        String res = simpleDateFormat.format(date);
        System.out.println(res);
        return res;
    }

    /**
     * 方法描述 TODO 格式化当前时间
     *
     * @param dateType current(年月日时分秒),date(年月日)
     * @return java.lang.String
     * @author xiongfk
     * @date 2019/7/16
     */
    public static String formatDate(String dateType) {
        SimpleDateFormat sdf = null;
        switch (dateType) {
            case "current":
                sdf = new SimpleDateFormat(FORMAT_YMDHMS);
                break;
            case "date":
                sdf = new SimpleDateFormat(FORMAT_YMD);
                break;
            default:
                break;
        }
        String currentDate = sdf.format(new Date());
        return currentDate;
    }

    /**
     * 方法描述 TODO java校验String是否为指定的日期格式
     *
     * @param str
     * @return boolean
     * @author xiongfk
     * @date 2019/7/22
     */
    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_YMDHM);
        try {
            // 设置lenient为false
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        } catch (Exception e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 方法描述 TODO 年月日时分秒格式字符串转日期类型(yyyy-MM-dd HH:mm:ss)
     *
     * @param str
     * @return java.lang.String
     * @author xiongfk
     * @date 2019/7/22
     */
    public static String strToDate(String str) {
        String year = str.substring(0, 4);
        String month = str.substring(4, 6);
        String day = str.substring(6, 8);
        String hour = str.substring(8, 10);
        String minute = str.substring(10, 12);
        String second = str.substring(12, 14);
        str = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        return str;
    }

    /**
     * 方法描述 TODO 年有日格式的字符串转日期类型(yyyy-MM-dd)
     *
     * @param str
     * @return java.lang.String
     * @author xiongfk
     * @date 2019/7/22
     */
    public static String toDate(String str) {
        String year = str.substring(0, 4);
        String month = str.substring(4, 6);
        String day = str.substring(6, 8);
        str = year + "-" + month + "-" + day;
        System.out.println(str);
        return str;
    }

    public static void main(String[] args) {
        System.out.println(stampToDate("1562565524814"));
        System.out.println(formatDate("current"));
    }
}
