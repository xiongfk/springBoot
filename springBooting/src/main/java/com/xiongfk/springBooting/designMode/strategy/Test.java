package com.xiongfk.springBooting.designMode.strategy;

import java.math.BigDecimal;

/**
 * 功能描述 TODO
 * 测试类
 * @Author xiongfk
 * @Date 2020/7/2
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        BigDecimal money = new BigDecimal(1100);
        money = StrategyInvoke.getResult(money,2);
        System.out.println(money);
    }
}
