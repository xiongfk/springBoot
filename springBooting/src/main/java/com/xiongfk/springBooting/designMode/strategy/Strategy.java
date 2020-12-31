package com.xiongfk.springBooting.designMode.strategy;

import java.math.BigDecimal;

/**
 * 功能描述 TODO
 * 封装策略需要实现的方法
 * @Author xiongfk
 * @Date 2020/7/2
 * @Version 1.0
 **/
public interface Strategy {

    //计算会员打折后的金额
    BigDecimal calculateMoney(BigDecimal money);

    //返回会员类型
//    int getType();
}
