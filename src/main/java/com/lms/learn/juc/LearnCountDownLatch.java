package com.lms.learn.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: Mission Lee
 * @create: 2019-05-07 20:02
 */
public class LearnCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cdl.countDown();
            }).start();
        }
        System.out.println("===");
        cdl.await();
        Thread.sleep(100);
        System.out.println("全部结束");
    }
}
