package com.xiongfk.springBooting.designMode.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述 TODO
 * 单例工厂类
 * @Author xiongfk
 * @Date 2020/7/2
 * @Version 1.0
 **/
public class StrategyFactory {
    private Map<Integer,Strategy> map;

    public StrategyFactory() {
        map = new HashMap<>();
        map.put(UserLevel.regularUser,new RegularUser());
        map.put(UserLevel.silverUser,new SilverUser());
        map.put(UserLevel.goldUser,new GoldUser());

//        List<Strategy> strategies = new ArrayList<>();
//        strategies.add(new RegularUser());
//        strategies.add(new SilverUser());
//        strategies.add(new GoldUser());
//
//        map = new HashMap<>();
//        for (Strategy strategy : strategies) {
//            map.put(strategy.getType(), strategy);
//        }

//        map = strategies.stream().collect(Collectors.toMap(Strategy ::getType,strategy -> strategy));
    }

    //匿名内部类 初始化单例工厂
    private static class Holder {
        private static StrategyFactory instance = new StrategyFactory();
    }

    public static StrategyFactory getInstance() {
        return Holder.instance;
    }

    public Strategy get(Integer type) {
        return map.get(type);
    }
}