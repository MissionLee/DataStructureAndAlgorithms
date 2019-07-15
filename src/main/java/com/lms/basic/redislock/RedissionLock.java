package com.lms.basic.redislock;

import org.redisson.config.Config;

/**
 * @description:
 * @author: Mission Lee
 * @create: 2019-07-11 17:04
 */
public class RedissionLock {
    Config config;
    public void initConfig(String address){
        this.config = new Config();
        config.useSingleServer();

    }

}
