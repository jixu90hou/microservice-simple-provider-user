package com.weweb.cloud.framework.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;

import com.weweb.cloud.framework.annotation.BeanValue;
import com.weweb.cloud.framework.annotation.Inject;
import com.weweb.cloud.framework.proxy.CglibProxy;

/**
 * Created by jackshen on 2017/6/25.
 */

public class TestBean {
    private BeanServiceImpl proxy;

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
       /* BeanService beanService=new BeanServiceImpl();
        BeanInvocationHandler invocationHandler=new BeanInvocationHandler(beanService);
        BeanService proxy= (BeanService) invocationHandler.getProxy();

        String content=beanService.say("zhangsan");
        System.out.println(content);
        System.out.println(proxy.say("lisi"));*/
        String packageName = "com.weweb.cloud.framework.service";
        List<Class<?>> classList = getClassList(packageName, true);
        Map<String,Object> newInstanceMap=new HashMap<>();
        for (Class clazz : classList) {
            if (clazz.isAnnotationPresent(BeanValue.class)) {
                CglibProxy proxy = new CglibProxy();
                // BeanServiceImpl beanService= (BeanServiceImpl) proxy.getProxy(clazz);
                Object newClazz = proxy.getProxy(clazz);
                newInstanceMap.put(clazz.getName(),newClazz);
            }
        }

        newInstanceMap.entrySet().forEach(s-> System.err.println(s.getKey()+"\t"+s.getValue()));

        for (Class clazz : classList) {
            for (Field beanField:clazz.getDeclaredFields()){
                if(beanField.isAnnotationPresent(Inject.class)){
                    Type type=beanField.getAnnotatedType().getType();
                    String typeName=type.getTypeName();
                    Object typeValue=newInstanceMap.get(typeName);
                    //helloService instance
                    if(typeValue!=null){
                        // typeValue=
                        //Object obj=clazz.newInstance();
                        Object obj=newInstanceMap.get(clazz.getName());
                        //BeanServiceImpl instance
                        beanField.setAccessible(true);
                        beanField.set(obj,typeValue);
                    }
                    System.out.println("typeName:"+typeName);
                }
            }
        }
        //调用BeanServiceImpl print的方法
        System.out.println("*****************************************execute reflect result***********************************************");
        Object object=newInstanceMap.get("com.weweb.cloud.framework.service.BeanServiceImpl");
        BeanServiceImpl beanService= (BeanServiceImpl) object;
        beanService.print();

    }

    public static List<Class<?>> getClassList(String packageName, boolean isRecursive) throws IOException, ClassNotFoundException {
        List<Class<?>> classList = new ArrayList<>();
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader()
                .getResources(packageName.replaceAll("\\.", "/"));
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            if (url != null) {
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String packagePath = url.getPath();
                    addClassList(classList, packagePath, packageName, isRecursive);
                }
            }
        }
        return classList;

    }

    private static void addClassByInterface(List<Class<?>> classList, String packagePath, String packageName, Class<?> interfaceClass) {
        try {
            File[] files = getClassFiles(packagePath);
            if (files != null) {
                for (File file : files) {
                    String fileName = file.getName();
                    if (file.isFile()) {
                        String className = getClassName(packageName, fileName);
                        Class<?> cls = Class.forName(className);
                        if (interfaceClass.isAssignableFrom(cls) && !interfaceClass.equals(cls)) {
                            classList.add(cls);
                        }
                    } else {
                        String subPackagePath = getSubPackagePath(packagePath, fileName);
                        String subPackageName = getSubPackageName(packageName, fileName);
                        addClassByInterface(classList, subPackagePath, subPackageName, interfaceClass);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getDependName(String dependName){
        if(!BeanUtils.isEmpty(dependName)){
            String[] temps=dependName.split("\\.");
            String className=temps[temps.length-1];
            return String.valueOf(className.charAt(0)).toLowerCase()+className.substring(1,className.length());
        }
        return dependName;
    }
    private static void addClassList(List<Class<?>> classList, String packagePath, String packageName, boolean isRecursive) throws ClassNotFoundException {
        File[] files = getClassFiles(packagePath);
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                if (file.isFile()) {
                    String className = getClassName(packageName, fileName);
                    classList.add(Class.forName(className));
                } else {
                    if (isRecursive) {
                        String subPackagePath = getSubPackagePath(packagePath, fileName);
                        String subPackageName = getSubPackageName(packageName, fileName);
                        addClassList(classList, subPackagePath, subPackageName, isRecursive);
                    }
                }
            }
        }
    }

    private static String getSubPackagePath(String packagePath, String filePath) {
        String subPackagePath = filePath;
        if (!BeanUtils.isEmpty(packagePath)) {
            subPackagePath = packagePath + "/" + subPackagePath;
        }
        return subPackagePath;
    }

    private static String getSubPackageName(String packageName, String filePath) {
        String subPackageName = filePath;
        if (!BeanUtils.isEmpty(packageName)) {
            subPackageName = packageName + "." + subPackageName;
        }
        return subPackageName;
    }

    private static String getClassName(String packageName, String fileName) {
        String className = fileName.substring(0, fileName.lastIndexOf("."));
        if (!BeanUtils.isEmpty(packageName)) {
            className = packageName + "." + className;
        }
        return className;
    }

    private static File[] getClassFiles(String packagePath) {
        return new File(packagePath).listFiles(file -> (file.isFile() && file.getName().endsWith(".class"))
                || file.isDirectory());
    }
}
