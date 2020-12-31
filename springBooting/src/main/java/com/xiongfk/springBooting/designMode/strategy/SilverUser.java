package com.xiongfk.springBooting.designMode.strategy;

import java.math.BigDecimal;

/**
 * 功能描述 TODO
 * 银会员
 * @Author xiongfk
 * @Date 2020/7/2
 * @Version 1.0
 **/
public class SilverUser implements Strategy{

    @Override
    public BigDecimal calculateMoney(BigDecimal money) {
        return money.multiply(new BigDecimal(0.9)).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

//    @Override
//    public int getType() {
//        return UserLevel.silverUser;
//    }
}
