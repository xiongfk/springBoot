package com.xiongfk.springBooting.designMode.decorator;

import com.xiongfk.springBooting.base.BaseCommonLog;

/**
 * 功能描述 TODO 房子实现类
 * @Author xiongfk
 * @Date 2020/8/6
 * @Version 1.0
 **/
public class ZhangSanHouseImpl implements House{

    @Override
    public void output() {
        BaseCommonLog.logger.info("this is zhangsan's house");
    }
}
