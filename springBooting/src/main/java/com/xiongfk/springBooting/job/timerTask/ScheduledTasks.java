package com.xiongfk.springBooting.job.timerTask;

import com.xiongfk.springBooting.base.BaseCommonLog;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2019/10/8
 * @Version 1.0
 **/
@Component
public class ScheduledTasks extends BaseCommonLog {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(cron = "0 20 11 ? * *")
    public void reportCurrentTime() {
        logger.info("The time is now {}", dateFormat.format(new Date()));
    }
}

