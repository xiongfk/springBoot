package com.xiongfk.springBooting.designMode.strategy;

import java.math.BigDecimal;

/**
 * 功能描述 TODO
 * 提供调用方法
 * @Author xiongfk
 * @Date 2020/7/2
 * @Version 1.0
 **/
public class StrategyInvoke {

    //调用方法
    public static BigDecimal getResult(BigDecimal money, int type) {
        if (-1 == money.compareTo(new BigDecimal(1000))) {
            return money;
        }

        Strategy strategy = StrategyFactory.getInstance().get(type);
        if (strategy == null){
            throw new IllegalArgumentException("please input right type");
        }
        return strategy.calculateMoney(money);
    }
}
