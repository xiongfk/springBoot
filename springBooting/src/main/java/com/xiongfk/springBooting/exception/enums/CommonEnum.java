package com.xiongfk.springBooting.exception.enums;

import com.xiongfk.springBooting.exception.service.BaseExceptionService;

/**
 * 枚举类描述 TODO
 * 定义返回内容信息
 * @Author xiongfk
 * @Date 2020/4/30
 * @Version 1.0
 **/
public enum CommonEnum implements BaseExceptionService {
    // 数据操作错误定义
    SUCCESS("200", "成功!"),
    BODY_NOT_MATCH("400","请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503","服务器正忙，请稍后再试!"),
    TOKEN_NOT_EXISTS("100","token不存在"),
    TOKEN_CREAT_FAIL("101","token创建失败"),
    REPEAT_REQUEST("102","重复请求"),
    TOKEN_NOT_EMPTY("103","token不能为空")
    ;

    /** 错误码 */
    private final String resultCode;

    /** 错误描述 */
    private final String resultMsg;

    CommonEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }

    /**
     * 方法描述 TODO 根据key获取value
     *
     * @param //key
     * @return java.lang.String
     * @author xiongfk
     * @date 2019/7/17
     */
    public static String getEnumValueByKey(String key) {
        for (CommonEnum enumItem : CommonEnum.values()) {
            if (key.equals(enumItem.getResultCode())) {
                return enumItem.resultMsg;
            }
        }
        return "";
    }

    /**
     * 方法描述 TODO 根据value值获取key
     *
     * @param value
     * @return java.lang.String
     * @author xiongfk
     * @date 2019/7/17
     */
    public static String getEnumKeyByValue(String value) {
        for (CommonEnum enum_config_type : CommonEnum.values()) {
            if (value.equals(enum_config_type.getResultMsg())) {
                return enum_config_type.resultCode;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String key = "200";
        String value = "成功!";
        System.out.println(CommonEnum.getEnumKeyByValue(value));
        System.out.println(CommonEnum.getEnumValueByKey(key));
    }
}
