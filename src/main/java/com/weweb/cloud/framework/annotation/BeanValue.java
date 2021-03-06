package com.weweb.cloud.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by jackshen on 2017/6/25.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeanValue {
    String value() default "";
}
