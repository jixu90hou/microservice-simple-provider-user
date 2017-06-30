package com.weweb.cloud.framework.proxy;


import com.weweb.cloud.framework.service.BeanServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by jackshen on 2017/6/25.
 */

public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer=new Enhancer();
    public Object getProxy(Class clazz){
        //创建子类实现类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
       // System.out.println("=======before execute=======");
        Object result=methodProxy.invokeSuper(o,objects);
       // System.out.println("execute cglib "+result);
       // System.out.println("=======after execute=======");
        return result;
    }
    public static void main(String[] args) {
        CglibProxy proxy=new CglibProxy();
        BeanServiceImpl beanService= (BeanServiceImpl) proxy.getProxy(BeanServiceImpl.class);
        System.out.println("name:"+beanService.say("zhangxiaoming"));
    }
}
