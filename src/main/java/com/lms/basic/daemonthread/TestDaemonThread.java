package com.lms.basic.daemonthread;

import java.io.IOException;

/**
 * @Author MissionLee
 * @Description
 * @Date Created in 17:52 2018/10/10
 **/
public class TestDaemonThread {
    public static void say(){
        for(int i=0;i<10;i++){
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){

            }
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws IOException {
        Thread thread = new Thread(()->say());
        thread.setDaemon(true);
        thread.start();
        System.in.read();
    }
}
