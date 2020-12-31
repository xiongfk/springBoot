package com.xiongfk.springBooting.designMode.mediator;

/**
 * 功能描述 TODO 调停者接口
 * @Author xiongfk
 * @Date 2020/8/5
 * @Version 1.0
 **/
public interface Mediator {
    void change(String message, OfficeClerk officeClerk, String nname);
}
