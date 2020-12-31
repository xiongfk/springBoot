package com.xiongfk.springBooting.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.xiongfk.springBooting.model.DemoData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/8/3
 * @Version 1.0
 **/
public class DemoDataListener extends AnalysisEventListener<DemoData> {
    private final static Logger logger = LogManager.getLogger();
    List<DemoData> list = new ArrayList<DemoData>();

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public DemoDataListener() {}

    /**
     * 这个每一条数据解析都会来调用
     * @param data
     * @param context
     */
    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        logger.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
    }

    /**
     * 所有数据解析完成了 都会来调用
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println(JSON.toJSONString(list));
    }
}
