package com.weweb.cloud.framework.proxy;

import com.weweb.cloud.framework.annotation.BeanValue;
import com.weweb.cloud.framework.service.BeanService;

import java.lang.annotation.Annotation;

/**
 * Created by jackshen on 2017/6/25.
 */
public class BeanHandler {
    public static <T> void getBeanUtils(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        //clazz.newInstance();
        if (clazz.isAnnotationPresent(BeanValue.class)) {
            Annotation[] annotations=clazz.getAnnotations();
           for(Annotation annotation:annotations){
               BeanValue beanService= (BeanValue) annotation;
               System.out.println(annotation);
           }
        }
       /* Field[] fields=clazz.getDeclaredFields();
        clazz.newInstance();
        for (Field field:fields){
            BeanValue beanValue=field.getAnnotation(BeanValue.class);
        }*/
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        getBeanUtils(BeanService.class);
    }
}
