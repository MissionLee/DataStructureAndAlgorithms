package com.lms.learn.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Mission Lee
 * @create: 2019-05-07 15:00
 */
public class LearnSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"进入大厅");
                if(semaphore.availablePermits()>0){
                    System.out.println(Thread.currentThread().getName()+"没有排队-直接获取");
                }else{
                    System.out.println(Thread.currentThread().getName()+"排队");
                }
                try{
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"开始办理");
                    TimeUnit.SECONDS.sleep(random.nextInt(10));
                    System.out.println(Thread.currentThread().getName()+"工作结束");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"序号"+i).start();
        }
    }
}
