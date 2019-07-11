package com.lms.learn.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: Mission Lee
 * @create: 2019-05-08 14:53
 */
public class LearnBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        AtomicInteger integer = new AtomicInteger(0);
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 5; j++) {
                        TimeUnit.SECONDS.sleep(1);
                        String str = Thread.currentThread().getName() + "-" + j;
                        int num = integer.incrementAndGet();
                        System.out.println("加入排队["+num+"]:"+str);
                        queue.put(str);
                        System.out.println("放入队列["+num+"]:"+str);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "生产-" + i).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                while (true){
                    try{
                        TimeUnit.SECONDS.sleep(10);
                        if(queue.isEmpty()){
                            break;
                        }else{
                            System.out.println(Thread.currentThread().getName()+queue.take());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"消费"+i).start();
        }
    }
}
