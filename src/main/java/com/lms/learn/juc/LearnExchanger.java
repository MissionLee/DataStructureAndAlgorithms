package com.lms.learn.juc;

import java.util.concurrent.Exchanger;

/**
 * @description:
 * @author: Mission Lee
 * @create: 2019-05-07 23:06
 */
public class LearnExchanger {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger();
        // ！！！！！！！！！！！！  多余两个线程使用 Exchanger 的实际使用场景没有意义
        // ！！！！！！！！！！！！  这个例子是错误的 错误的使用方式
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                while (true){
                    try {
                        String data = exchanger.exchange(null);
                        if(data!=null)
                            System.out.println(Thread.currentThread().getName()+"\t"+data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"消费者"+i).start();
        }
        for (int i = 0; i <3 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 22; j++) {
                    String data = Thread.currentThread().getName()+"-"+j;
                    System.out.println("--------------生产："+data);
                    try {
                        exchanger.exchange(data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"生产者"+i).start();
        }
    }
}
