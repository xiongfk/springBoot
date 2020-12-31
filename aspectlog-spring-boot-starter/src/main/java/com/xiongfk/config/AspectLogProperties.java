package com.xiongfk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/9/9
 * @Version 1.0
 **/
@ConfigurationProperties("aspectLog")
public class AspectLogProperties {

    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

}
