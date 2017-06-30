package com.weweb.cloud.framework.service;

import com.weweb.cloud.framework.annotation.BeanValue;
import com.weweb.cloud.framework.annotation.Inject;

/**
 * Created by jackshen on 2017/6/25.
 */
@BeanValue
public class BeanServiceImpl implements BeanService{
    @Inject
    public HelloServiceImpl helloService;
    @Override
    public String say(String name) {
        return "hello "+name;
    }
    public void print(){
        System.out.println("BeanServiceImpl print execute ");
        helloService.execute();
    }
    public void invokeBeanServiceImpl(){
        System.out.println("======BeanServiceImpl======");
    }
}
