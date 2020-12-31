package com.xiongfk.springBooting.designMode.factory.SampleFacory;

import com.xiongfk.springBooting.designMode.factory.Desk;
import org.apache.ibatis.javassist.runtime.Desc;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/7/30
 * @Version 1.0
 **/
public interface SampleFactory01 {
    Desk productDesk();
}
