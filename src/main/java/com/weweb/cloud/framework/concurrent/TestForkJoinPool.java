package com.weweb.cloud.framework.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by wshen on 6/27/2017.
 */
public class TestForkJoinPool {
    private static final Integer MAX=200;
    static class MyForkJoinTask extends RecursiveTask<Integer>{
        private Integer startValue;
        private Integer endValue;

        public MyForkJoinTask(Integer startValue, Integer endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        protected Integer compute() {
            //如果条件成立，说明任务需要计数的值足够小
            if(endValue-startValue<MAX){
                System.out.println("开始计算的部分：startValue="+startValue+";endValue="+endValue);
                Integer totalValue=0;
                for (int index=this.startValue;index<=this.endValue;index++){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    totalValue+=index;
                }
                return totalValue;
            }else{
                MyForkJoinTask subTask1=new MyForkJoinTask(startValue,(startValue+endValue)/2);
                subTask1.fork();
                MyForkJoinTask subTask2=new MyForkJoinTask((startValue+endValue)/2+1,endValue);
                subTask2.fork();
                return subTask1.join()+subTask2.join();
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long begin=System.currentTimeMillis();
        System.out.println(Runtime.getRuntime().availableProcessors());
        ForkJoinPool pool=new ForkJoinPool(8);
        ForkJoinTask<Integer> taskFuture=pool.submit(new MyForkJoinTask(1,10000));
        Integer result=taskFuture.get();
        System.out.println("result:"+result);
        System.out.println("spend time:"+(System.currentTimeMillis()-begin)/1000);
    }
}
