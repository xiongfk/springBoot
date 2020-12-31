package com.xiongfk.springBooting.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

/**
 * 功能描述 TODO
 * 示例执行器
 * @Author xiongfk
 * @Date 2020/4/7
 * @Version 1.0
 **/
@JobHandler(value="TestHandler")
@Component
public class TestHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        System.out.println("执行了TestHandler一次");
        return SUCCESS;
    }
}
