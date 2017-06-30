package com.weweb.cloud.framework.proxy;

import com.weweb.cloud.framework.service.BeanService;
import com.weweb.cloud.framework.service.BeanServiceImpl;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackshen on 2017/6/25.
 */
public class BeanInvocationHandler implements InvocationHandler{
    //代理对象
    private Object target;

    public BeanInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("======= before execute =======");
        Object result=method.invoke(target,args);
        System.out.println("invoke content:"+result);
        System.out.println("======= after execute =======");
        return result;
    }
    public Object getProxy(){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(),this);
    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
/*        BeanService beanService=new BeanServiceImpl();
        System.err.println("test:"+beanService.say("wangxiaoming"));
        BeanInvocationHandler invocationHandler=new BeanInvocationHandler(beanService);
        BeanService beanProxy= (BeanService) invocationHandler.getProxy();
        beanProxy.say("zhangdaming");*/


        Class beanProxyClazz=Proxy.getProxyClass(BeanService.class.getClassLoader(),BeanService.class);
        Constructor constructor=beanProxyClazz.getConstructor(InvocationHandler.class);
        BeanService beanService2= (BeanService) constructor.newInstance(new BeanInvocationHandler(new BeanServiceImpl()));
        beanService2.say("wangxiaoming");
    }
}
