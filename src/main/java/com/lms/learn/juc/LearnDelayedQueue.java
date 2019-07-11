package com.lms.learn.juc;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Mission Lee
 * @create: 2019-05-08 16:11
 */
public class LearnDelayedQueue {
    public static class DelayMember<T> implements Delayed{
        private T item ; //存储数据
        private long delay;
        private long expire;

        public DelayMember(T item, long delay,TimeUnit timeUnit) {
            this.item = item;
            this.delay = TimeUnit.MILLISECONDS.convert(delay,timeUnit);
            this.expire = System.currentTimeMillis()+this.delay;
        }

        @Override
        public long getDelay(TimeUnit unit) { // 获取还剩多久过期
            return unit.convert(this.expire-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) { // 用于排序先后顺序
                                           // 我们希望结束早的优先高
            return (int)(this.getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS));
        }

        public T getItem() {
            return item;
        }
        @Override
        public String toString(){
            return item.toString();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayMember<String>> queue = new DelayQueue<>();
        queue.add(new DelayMember<>("张三",10,TimeUnit.SECONDS));
        queue.add(new DelayMember<>("张四",5,TimeUnit.SECONDS));
        queue.add(new DelayMember<>("a",9,TimeUnit.SECONDS));
        queue.add(new DelayMember<>("b",2,TimeUnit.SECONDS));


        while (!queue.isEmpty()){
            // Delayed d =queue.poll();
            Delayed d = queue.take();
            if(d!=null)
            System.out.println(((DelayMember) d).getItem()+"滚蛋");
            else
                System.out.println("继续high");
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
