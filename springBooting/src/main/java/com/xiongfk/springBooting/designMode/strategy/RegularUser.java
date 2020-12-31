package com.xiongfk.springBooting.designMode.strategy;

import java.math.BigDecimal;

/**
 * 功能描述 TODO
 * 普通会员
 * @Author xiongfk
 * @Date 2020/7/2
 * @Version 1.0
 **/
public class RegularUser implements Strategy{

    @Override
    public BigDecimal calculateMoney(BigDecimal money) {
        return money;
    }

//    @Override
//    public int getType() {
//        return UserLevel.regularUser;
//    }
}
