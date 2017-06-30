package com.weweb.cloud.framework.jvm;

/**
 * Created by wshen on 6/29/2017.
 */
public class TestClass{
    public static void main(String[] args) {
        System.out.print(SubClass.value);
    }
}
 class SuperClass {
    static {
        System.out.println("SuperClass init");
    }
    public static int value=123;
}
class SubClass extends SuperClass{

    static {
        System.out.println("SubClass init");
    }
}
