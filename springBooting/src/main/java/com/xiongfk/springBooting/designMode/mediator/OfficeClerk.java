package com.xiongfk.springBooting.designMode.mediator;

import com.xiongfk.springBooting.base.BaseCommonLog;

/**
 * 功能描述 TODO 职工抽象类
 * @Author xiongfk
 * @Date 2020/8/5
 * @Version 1.0
 **/
public abstract class OfficeClerk extends BaseCommonLog {
    String name;
    private Mediator mediator;

    public OfficeClerk(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    //被调停者调用的方法
    public void called(String message,String from) {
        logger.info(name + "接收到来自"+ from + "的需求：" + message);
    }

    //调用调停者
    public void call(String message,OfficeClerk officeClerk,String from){
        logger.info(from + "发起需求："+ message);
        mediator.change(message,officeClerk,from);
    }
}
