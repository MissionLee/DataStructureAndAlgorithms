package com.lms.basic.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

/**
 * @Author MissionLee
 * @Description
 * @Date Created in 16:37 2018/9/28
 **/
public class ForkJoinTask2 extends RecursiveTask<Long> {
    private static final long MAX = 1000000000L;
    private static final long THRESHOLD = 10000L;
    long st;
    long ed;
    public ForkJoinTask2(long start ,long end) {
        this.st = start;
        this.ed = end;
    }

    @Override
    protected Long compute() {
        /***
         * 如果 start 和 end 差距小于 1w 就自己算， 如果大于1 w 就均分为两个任务进行计算
         * */
        if(ed -st > THRESHOLD){
            long sum = 0;
            for (long i = st ;i<ed ;i++){
                sum+=i;
            }
            return sum;
        }else{
            long mid = st + (ed-st)/2;
            ForkJoinTask2 forkJoinTask21 = new ForkJoinTask2(st,mid);
            forkJoinTask21.fork();
            ForkJoinTask2 forkJoinTask22 = new ForkJoinTask2(mid,ed);
            forkJoinTask22.fork();
            return forkJoinTask21.join() + forkJoinTask22.join();
        }

    }
}
