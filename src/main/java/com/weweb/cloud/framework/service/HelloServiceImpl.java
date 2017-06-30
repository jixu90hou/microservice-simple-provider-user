package com.weweb.cloud.framework.service;

import com.weweb.cloud.framework.annotation.BeanValue;
import com.weweb.cloud.framework.annotation.Inject;

/**
 * Created by jackshen on 2017/6/25.
 */
@BeanValue
public class HelloServiceImpl {
    @Inject
    private BeanServiceImpl beanService;
    public String execute(){
        String result="hello service";
        System.out.println("Hello:"+result);
        beanService.invokeBeanServiceImpl();
        return result;
    }
}
