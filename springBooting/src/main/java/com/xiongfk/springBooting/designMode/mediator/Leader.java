package com.xiongfk.springBooting.designMode.mediator;

/**
 * 功能描述 TODO 具体调停者领导
 * @Author xiongfk
 * @Date 2020/8/5
 * @Version 1.0
 **/
public class Leader implements Mediator{

    @Override
    public void change(String message, OfficeClerk officeClerk, String fromName) {
        System.out.println("经理收到" + fromName + "的需求：" + message);
        System.out.println("经理将" + fromName + "的需求发送给目标职员");
        officeClerk.called(message,fromName);
    }
}
