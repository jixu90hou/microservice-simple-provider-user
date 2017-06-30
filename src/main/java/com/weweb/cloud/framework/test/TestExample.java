package com.weweb.cloud.framework.test;

import java.lang.reflect.Field;

/**
 * Created by wshen on 6/26/2017.
 */
class Person{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
public class TestExample {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
/*        Class clazz=Person.class;
        Field field=clazz.getDeclaredField("name");
        field.setAccessible(true);
        Person person=new Person();
      //  Object value=field.get(person);
        field.set(person,"zhangcai");
        System.out.println(person.getName());*/
        Class clazz=Person.class;
        Object person=clazz.newInstance();
        Field beanField=clazz.getDeclaredField("name");
        beanField.setAccessible(true);
        System.out.println(beanField.get(person));
        beanField.set(person,"zhangdaming");
        System.out.println(beanField.get(person));
    }
}
/*    Class clazz= Class.forName(objName);
    Object obj = clazz.newInstance();
    Field name=clazz.getDeclaredField("blank0");
            name.setAccessible(true);
                    System.out.println(name.get(obj));
                    name.set(obj, "che");
                    System.out.println(name.get(obj));
                    name.setAccessible(false);  */
