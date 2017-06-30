package com.weweb.cloud;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jackshen on 2017/6/12.
 */
public class TestArrayList {
    private static ExecutorService executor= Executors.newFixedThreadPool(10);
    //private static Vector<String> list=new Vector<>();
    private static CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<>();

    //  private static Queue list=new LinkedList();

    public static void main(String[] args) throws InterruptedException {

        for(int i=0;i<100;i++){
            final String a=String.valueOf(i);
                list.add(a);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("size is :"+list.size());
       for(int i=0;i<100;i++) {
           final int a=i;
           executor.execute(() -> {
               System.out.println("--------");
               list.remove(a);
           });
       }
       Thread.sleep(1500);
        //new HashMap<>().put()

        /*for(int i=0;i<100;i++){
            String temp=list.get(i);
            if(temp==null){
                System.out.println("报错了："+temp);
            }else{
                System.out.println(temp);
            }
        }*/
        list.forEach(s-> System.out.println(s));
        System.out.println("size:"+list.size());
        //list=new ArrayList<>();
        executor.shutdown();
    }
}
