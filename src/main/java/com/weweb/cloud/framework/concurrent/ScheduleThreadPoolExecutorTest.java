package com.weweb.cloud.framework.concurrent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wshen on 6/27/2017.
 */
public class ScheduleThreadPoolExecutorTest {
    private static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private  static List<String> list=new ArrayList<>();
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
        exec.scheduleAtFixedRate(new Runnable() {
            public void run() {
                String str=format.format(new Date());
                list=new ArrayList<>();
                list.add(str);
                System.out.println(list);
            }
        }, 1000, 5000, TimeUnit.MILLISECONDS);
    }
}
