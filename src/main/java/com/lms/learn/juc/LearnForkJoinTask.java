package com.lms.learn.juc;

import java.util.concurrent.RecursiveTask;

/**
 * @description:
 * @author: Mission Lee
 * @create: 2019-05-09 19:41
 */
public class LearnForkJoinTask {
    // 有返回值的： RecursiveTask
    public static class SumTask extends RecursiveTask<Integer>{
        private int start;
        private int end;
        public SumTask(int start ,int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            if(end - start<100){
                for (int x = sum;x<=end;x++){
                    sum+=x;
                }
                return sum;
            }else {
                int middle = (start+end)/2;
                SumTask left = new SumTask(start,middle);
                SumTask right = new SumTask(middle+1,end);
                left.fork();
                right.fork();
                sum = left.join()+right.join();
                return sum;
            }
        }
    }
}
