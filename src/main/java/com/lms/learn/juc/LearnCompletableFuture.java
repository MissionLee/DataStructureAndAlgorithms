package com.lms.learn.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Mission Lee
 * @create: 2019-05-08 10:22
 */
public class LearnCompletableFuture {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 就位");
                try {
                    String cmd = future.get();
                    System.out.println(Thread.currentThread().getName()+"\t 获得内容："+cmd);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            },"等待者"+i).start();
        }
        TimeUnit.SECONDS.sleep(5);
        future.complete("久等了，一起happy");
    }
}
