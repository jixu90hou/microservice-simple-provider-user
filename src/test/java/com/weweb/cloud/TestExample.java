package com.weweb.cloud;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jackshen on 2017/6/10.
 */
public class TestExample {
    private static Map<String, String> map = new HashMap<>();
    private static ExecutorService executor = Executors.newFixedThreadPool(10);
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void print() throws InterruptedException {
        //init

        System.out.println("map begin:" + map);
        for (int i = 0; i < 100; i++) {
            final int a = i;
            executor.execute(() -> {
                map.put(String.valueOf(a),"zhang-"+a);
            });
        }
        Thread.sleep(1500);
        System.out.println("begin----------");
        for (int i=0;i<100;i++){
            String key=String.valueOf(i);
           String value= map.get(key);
           if(value==null){
               System.err.println(key+"======>"+value);
            }
        }
        System.out.println("finished");
        map=new HashMap<>();
        //map.entrySet().stream().sorted().forEach(s -> System.out.println(s));
    }

    public static void main(String[] args) throws InterruptedException {
        for (int  i=0;i<0;i++){
            print();
        }
        //executor.shutdown();
        Map<String,String> map=new HashMap<>();
        map.put("a","11");
    }
}
