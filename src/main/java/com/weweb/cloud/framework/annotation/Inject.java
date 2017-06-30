package com.weweb.cloud.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by jackshen on 2017/6/25.
 */
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inject {
    String value() default "";
}
