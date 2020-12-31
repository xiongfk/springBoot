package com.xiongfk.springBooting.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.xiongfk.springBooting.model.ConverterData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/8/4
 * @Version 1.0
 **/
public class ConverterDataListener extends AnalysisEventListener<ConverterData> {
    private final static Logger logger = LogManager.getLogger();
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<ConverterData> list = new ArrayList<ConverterData>();

    @Override
    public void invoke(ConverterData data, AnalysisContext context) {
        logger.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        logger.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        logger.info("{}条数据，开始存储数据库！", list.size());
        logger.info("存储数据库成功！");
    }
}
