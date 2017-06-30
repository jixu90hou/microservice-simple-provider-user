package com.weweb.cloud.framework.jvm;

/**
 * Created by wshen on 6/29/2017.
 */
class ConstantClass{
    static {
        System.out.println("ConstantClass init!");
    }
    public static final String HELLWORLD="hello world111111";
}
public class NotInitialization {
    public static void main(String[] args) {
        System.out.println(ConstantClass.HELLWORLD);
    }
}
