package com.lms.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Mission Lee
 * @create: 2019-07-11 22:19
 */
public class LearnRedisson {
    Config config;
    RedissonClient redissonClient;
    public LearnRedisson() {
        this.config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
                .setPassword("Ro998otPass")
                .setDatabase(15);
        this.redissonClient = Redisson.create(this.config);
    }
    Runnable runnableRLockTesterThread = new Runnable() {
        @Override
        public void run() {
            RLock rLock = redissonClient.getLock("rLock");
            try {
                boolean lock = rLock.tryLock(1000L,2500L, TimeUnit.MILLISECONDS);
                if(lock){
                    System.out.println("getLock "+Thread.currentThread().getName()+" | "+System.currentTimeMillis());
                    Thread.sleep(1500L);
                    rLock.unlock();
                }else{
                    System.out.println("Failed lock "+Thread.currentThread().getName()+" | "+System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //rLock.unlock();
            }
        }
    };
    // 分布式重入锁
    public void testRLock(){
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(runnableRLockTesterThread);
            thread.start();
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        LearnRedisson learnRedisson = new LearnRedisson();
        learnRedisson.testRLock();
    }
}
